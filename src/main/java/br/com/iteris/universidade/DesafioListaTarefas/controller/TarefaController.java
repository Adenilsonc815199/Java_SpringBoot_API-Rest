package br.com.iteris.universidade.DesafioListaTarefas.controller;

import br.com.iteris.universidade.DesafioListaTarefas.domain.dto.*;
import br.com.iteris.universidade.DesafioListaTarefas.domain.entity.Tarefa;
import br.com.iteris.universidade.DesafioListaTarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class TarefaController {
    @Autowired
    private final TarefaService service;

    public TarefaController(final TarefaService service) {
        this.service = service;
    }
    @CrossOrigin
    @GetMapping(value="api/tasks")
    public ResponseEntity<List<TarefaResponse>>listar(){
        var listaDeTarefas = service.listar();
        return ResponseEntity.ok(listaDeTarefas);
    }
    @CrossOrigin
    @GetMapping(value="api/tasks/{id}")
    public ResponseEntity<TarefaResponse>listarPorId(@PathVariable Integer id){
        var tarefaResponse = service.buscarPorId(id);
        return ResponseEntity.ok(tarefaResponse);
    }
    @CrossOrigin
    @PostMapping(value = "api/tasks")
    public ResponseEntity<TarefaResponse>criarTarefa(@RequestBody @Valid TarefaCreateRequest tarefa) {
        var tarefaResponse = service.criarTarefa(tarefa);
        return ResponseEntity.ok(tarefaResponse);
    }
    @CrossOrigin
    @PutMapping(value = "api/tasks/{id}/status")
    public ResponseEntity<TarefaResponse> atualizarTarefaStatus(
            @PathVariable Integer id,
            @RequestBody @Valid TarefaUpdateStatus tarefaUpdateRequest) {
        var tarefa = service.atualizarStatusTarefa(id, tarefaUpdateRequest);
        return ResponseEntity.ok(tarefa);
    }
    @CrossOrigin
    @PutMapping(value = "api/tasks/{id}/prioridade")
    public ResponseEntity<TarefaResponse> atualizarTarefaPrioridade(
            @PathVariable Integer id,
            @RequestBody @Valid TarefaUpdatePrioridade tarefaUpdateRequest) {
        var tarefa = service.atualizarPrioridadeTarefa(id, tarefaUpdateRequest);
        return ResponseEntity.ok(tarefa);
    }
    @CrossOrigin
    @PutMapping(value = "api/tasks/{id}")
    public ResponseEntity<TarefaResponse> atualizarTarefa(
            @PathVariable Integer id,
            @RequestBody @Valid TarefaUpdate tarefaRequest) {
        var tarefa = service.atualizarTarefa(id, tarefaRequest);
        return ResponseEntity.ok(tarefa);
    }
    @CrossOrigin
    @DeleteMapping(value = "api/tasks/{id}")
    public ResponseEntity<TarefaResponse> deletarTarefa(@PathVariable Integer id) {
        var tarefa = service.deletarTarefa(id);
        return ResponseEntity.ok(tarefa);
    }
    @CrossOrigin
    @PostMapping(value="api/tasks/img", produces = {"application/json"})
    public ResponseEntity<TarefaResponse> receiveData(TarefaCreateRequestWithImg tarefa,
                                              MultipartFile foto){

        String path = "C:/Users/adenilson.santos/OneDrive - Iteris Consultoria e Software/Área de Trabalho/Cursos Java/";
        var caminho = path + foto.getOriginalFilename();
        try {
            Files.copy(foto.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            var tarefaResponse = service.criarTarefaComImagem(tarefa, caminho);
            return new ResponseEntity<TarefaResponse>(HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<TarefaResponse>(HttpStatus.BAD_REQUEST);
        }
    }
}
