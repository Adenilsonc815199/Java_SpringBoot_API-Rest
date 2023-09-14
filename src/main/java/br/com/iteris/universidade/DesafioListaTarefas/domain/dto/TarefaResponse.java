package br.com.iteris.universidade.DesafioListaTarefas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TarefaResponse {
    private int idTarefa;
    private String titulo;
    private String descricao;
    private boolean concluido;
    private int prioridade;
    private String caminho;

}
