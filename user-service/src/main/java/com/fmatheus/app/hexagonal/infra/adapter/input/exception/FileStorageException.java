package com.fmatheus.app.hexagonal.infra.adapter.input.exception;


import com.fmatheus.app.hexagonal.infra.adapter.input.enumerable.MessagesEnum;

/**
 * Ao lancar esta excecao, a mesma sera capturada pela classe ApiExceptionHandler onde sera tratada.
 *
 * @author Fernando Matheus
 */
public class FileStorageException extends RuntimeException {

    public FileStorageException(MessagesEnum messagesEnum) {
        super(messagesEnum.getMessage());
    }

    public FileStorageException(MessagesEnum messagesEnum, Throwable cause) {
        super(messagesEnum.getMessage(), cause);
    }

}
