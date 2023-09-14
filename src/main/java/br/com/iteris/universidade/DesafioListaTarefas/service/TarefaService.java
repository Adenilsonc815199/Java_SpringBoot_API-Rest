package br.com.iteris.universidade.DesafioListaTarefas.service;

import br.com.iteris.universidade.DesafioListaTarefas.domain.dto.*;
import br.com.iteris.universidade.DesafioListaTarefas.domain.entity.Tarefa;
import br.com.iteris.universidade.DesafioListaTarefas.exceptions.TarefaNaoEncontradaException;
import br.com.iteris.universidade.DesafioListaTarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {
        private final TarefaRepository repository;

        public TarefaService(final TarefaRepository repository){
            this.repository = repository;
        }

        //Lista todos resultados
        public List<TarefaResponse> listar(){
            var resultado = repository.listarTodos();

            return resultado.stream().map(tarefa -> new TarefaResponse(
                    tarefa.getIdTarefa(),
                    tarefa.getTitulo(),
                    tarefa.getDescricao(),
                    tarefa.isConcluido(),
                    tarefa.getPrioridade(),
                    tarefa.getCaminho()
            )).collect((Collectors.toList()));
        }

        //Lista por ID
        public TarefaResponse buscarPorId(Integer id_tarefa){
            var tarefaEncontrada = repository.findById(id_tarefa);
            if(tarefaEncontrada.isEmpty()){
                throw new TarefaNaoEncontradaException();
            }

            var tarefaSalva = tarefaEncontrada.get();
            return new TarefaResponse(
                    tarefaSalva.getIdTarefa(),
                    tarefaSalva.getTitulo(),
                    tarefaSalva.getDescricao(),
                    tarefaSalva.isConcluido(),
                    tarefaSalva.getPrioridade(),
                    tarefaSalva.getCaminho()
            );
        }

        public TarefaResponse criarTarefa(TarefaCreateRequest tarefaCreateRequest){
            var novaTarefa = new Tarefa();
            novaTarefa.setTitulo(tarefaCreateRequest.getTitulo());
            novaTarefa.setDescricao(tarefaCreateRequest.getDescricao());
            novaTarefa.setPrioridade(tarefaCreateRequest.getPrioridade());

            var tarefaSalva = repository.save(novaTarefa);

            return new TarefaResponse(
                    tarefaSalva.getIdTarefa(),
                    tarefaSalva.getTitulo(),
                    tarefaSalva.getDescricao(),
                    tarefaSalva.isConcluido(),
                    tarefaSalva.getPrioridade(),
                    tarefaSalva.getCaminho()
            );
        }

    public TarefaResponse criarTarefaComImagem(TarefaCreateRequestWithImg tarefaCreateRequest, String caminho){
        var novaTarefa = new Tarefa();
        novaTarefa.setTitulo(tarefaCreateRequest.getTitulo());
        novaTarefa.setDescricao(tarefaCreateRequest.getDescricao());
        novaTarefa.setPrioridade(tarefaCreateRequest.getPrioridade());
        novaTarefa.setCaminho(caminho);

        var tarefaSalva = repository.save(novaTarefa);

        return new TarefaResponse(
                tarefaSalva.getIdTarefa(),
                tarefaSalva.getTitulo(),
                tarefaSalva.getDescricao(),
                tarefaSalva.isConcluido(),
                tarefaSalva.getPrioridade(),
                tarefaSalva.getCaminho()
        );
    }
        public TarefaResponse deletarTarefa(Integer idTarefa){
            var tarefaEncontrada = repository.findById(idTarefa);

            if(tarefaEncontrada.isEmpty()){
                throw new TarefaNaoEncontradaException();
            }

            var tarefa = tarefaEncontrada.get();
            repository.delete(tarefa);
            return new TarefaResponse(
                    tarefa.getIdTarefa(),
                    tarefa.getTitulo(),
                    tarefa.getDescricao(),
                    tarefa.isConcluido(),
                    tarefa.getPrioridade(),
                    tarefa.getCaminho()
            );
        }


    public TarefaResponse atualizarTarefa(Integer idTarefa, TarefaUpdate tarefaUpdate){
        var tarefaEncontrada = repository.findById(idTarefa);

        if(tarefaEncontrada.isEmpty()){
            throw new TarefaNaoEncontradaException();
        }

        var tarefa = tarefaEncontrada.get();
        tarefa.setTitulo(tarefaUpdate.getTitulo());
        tarefa.setDescricao(tarefaUpdate.getDescricao());
        tarefa.setPrioridade(tarefaUpdate.getPrioridade());
        var tarefaSalva = repository.save(tarefa);

        return new TarefaResponse(
                tarefaSalva.getIdTarefa(),
                tarefaSalva.getTitulo(),
                tarefaSalva.getDescricao(),
                tarefaSalva.isConcluido(),
                tarefaSalva.getPrioridade(),
                tarefaSalva.getCaminho()
        );
    }

    public TarefaResponse atualizarStatusTarefa(Integer idTarefa, TarefaUpdateStatus updateStatus){
            var tarefaEncontrada = repository.findById(idTarefa);

            if(tarefaEncontrada.isEmpty()){
                throw new TarefaNaoEncontradaException();
            }

            var tarefa = tarefaEncontrada.get();
            tarefa.setConcluido(updateStatus.isStatus());

            var tarefaSalva = repository.save(tarefa);

            return new TarefaResponse(
                    tarefaSalva.getIdTarefa(),
                    tarefaSalva.getTitulo(),
                    tarefaSalva.getDescricao(),
                    tarefaSalva.isConcluido(),
                    tarefaSalva.getPrioridade(),
                    tarefaSalva.getCaminho()
            );
        }

        public TarefaResponse atualizarPrioridadeTarefa(Integer idTarefa, TarefaUpdatePrioridade updatePrioridade){
        var tarefaEncontrada = repository.findById(idTarefa);

        if(tarefaEncontrada.isEmpty()){
            throw new TarefaNaoEncontradaException();
        }

        var tarefa = tarefaEncontrada.get();
        tarefa.setPrioridade(updatePrioridade.getPrioridade());

        var tarefaSalva = repository.save(tarefa);

        return new TarefaResponse(
                tarefaSalva.getIdTarefa(),
                tarefaSalva.getTitulo(),
                tarefaSalva.getDescricao(),
                tarefaSalva.isConcluido(),
                tarefaSalva.getPrioridade(),
                tarefaSalva.getCaminho()
            );
        }

}
