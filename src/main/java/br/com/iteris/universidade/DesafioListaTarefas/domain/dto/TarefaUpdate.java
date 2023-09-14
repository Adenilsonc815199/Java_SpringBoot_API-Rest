package br.com.iteris.universidade.DesafioListaTarefas.domain.dto;

import lombok.Data;

@Data
public class TarefaUpdate {
    private String titulo;
    private String descricao;
    private int prioridade;
}
