package com.fmatheus.app.hexagonal.infra.adapter.input.exception;



import com.fmatheus.app.hexagonal.infra.adapter.input.enumerable.MessagesEnum;

import java.io.Serial;

/**
 * Ao lancar esta excecao, a mesma sera capturada pela classe ApiExceptionHandler onde sera tratada.
 *
 * @author Fernando Matheus
 */
public class PasswordNotMatchException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PasswordNotMatchException() {
        super(MessagesEnum.ERROR_PASSWORD_NOT_MATCH.getMessage());
    }

}
