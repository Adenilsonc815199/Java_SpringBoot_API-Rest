package br.com.iteris.universidade.DesafioListaTarefas.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class TarefaCreateRequest {
    private String titulo;
    private String descricao;
    private int prioridade;
}
