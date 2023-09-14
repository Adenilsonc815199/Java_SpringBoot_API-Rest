package br.com.iteris.universidade.DesafioListaTarefas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TarefaInvalidaException extends RuntimeException{
    public TarefaInvalidaException(String message) {
        super(message);
    }
}
