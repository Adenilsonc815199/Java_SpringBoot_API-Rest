package br.com.iteris.universidade.DesafioListaTarefas.exceptions;

public class TarefaNaoEncontradaException extends RuntimeException {
    public TarefaNaoEncontradaException(){super("Tarefa não encontrada");}
}
