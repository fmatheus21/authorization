package com.fmatheus.app;

import com.fmatheus.app.hexagonal.infra.adapter.input.enumerable.MessagesEnum;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.handler.MessageResponseHandler;

import java.time.LocalDateTime;

public class MessageResponseHandlerMock {

    public static MessageResponseHandler loadMessageResponseHandlerSuccessCreate() {
        MessagesEnum messagesEnum = MessagesEnum.SUCCESS_CREATE;
        return MessageResponseHandler.builder()
                .timestamp(LocalDateTime.now())
                .message("Registro criado com sucesso.")
                .statusCode(messagesEnum.getHttpSttus().value())
                .statusDescription(messagesEnum.getHttpSttus().getReasonPhrase())
                .cause(messagesEnum.getMessage())
                .build();
    }

    public static MessageResponseHandler loadMessageResponseHandlerSuccessUpdate() {
        MessagesEnum messagesEnum = MessagesEnum.SUCCESS_UPDATE;
        return MessageResponseHandler.builder()
                .timestamp(LocalDateTime.now())
                .message("Registro atualizado com sucesso.")
                .statusCode(messagesEnum.getHttpSttus().value())
                .statusDescription(messagesEnum.getHttpSttus().getReasonPhrase())
                .cause(messagesEnum.getMessage())
                .build();
    }

    public static MessageResponseHandler loadMessageResponseHandlerSuccessDelete() {
        MessagesEnum messagesEnum = MessagesEnum.SUCCESS_DELETE;
        return MessageResponseHandler.builder()
                .timestamp(LocalDateTime.now())
                .message("Registro excluído com sucesso.")
                .statusCode(messagesEnum.getHttpSttus().value())
                .statusDescription(messagesEnum.getHttpSttus().getReasonPhrase())
                .cause(messagesEnum.getMessage())
                .build();
    }
}
