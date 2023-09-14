package br.com.iteris.universidade.DesafioListaTarefas.domain.dto;

import lombok.Data;

@Data
public class TarefaCreateRequestWithImg {
    private String titulo;
    private String descricao;
    private int prioridade;
    private String caminho;
}
