package br.com.iteris.universidade.DesafioListaTarefas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTarefa;
    private String titulo;
    private String descricao;
    private boolean concluido;
    private int prioridade;
    private String caminho;

    public Tarefa(String titulo, String descricao, int prioridade, String caminho) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.caminho = caminho;
    }
}
