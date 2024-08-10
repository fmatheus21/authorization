package com.fmatheus.app.hexagonal.infra.adapter.input.exception.message;


import com.fmatheus.app.hexagonal.infra.adapter.input.enumerable.MessagesEnum;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.BadRequestException;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.ForbiddenException;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.PasswordNotMatchException;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.handler.MessageResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class MessageResponse {

    private final MessageSource messageSource;

    private MessageResponseHandler messageResponse(MessagesEnum messagesEnum) {
        return new MessageResponseHandler(messagesEnum, messagesEnum.getHttpSttus().getReasonPhrase(),
                this.messageSource.getMessage(messagesEnum.getMessage(), null, LocaleContextHolder.getLocale()));
    }

    public MessageResponseHandler messageSuccessUpdate() {
        return this.messageResponse(MessagesEnum.SUCCESS_UPDATE);
    }

    public MessageResponseHandler messageSuccessCreate() {
        return this.messageResponse(MessagesEnum.SUCCESS_CREATE);
    }

    public MessageResponseHandler messageSuccessDelete() {
        return this.messageResponse(MessagesEnum.SUCCESS_DELETE);
    }

    public BadRequestException errorExistDocument() {
        return new BadRequestException(MessagesEnum.ERROR_EXIST_DOCUMENT);
    }

    public BadRequestException errorExistEmail() {
        return new BadRequestException(MessagesEnum.ERROR_EXIST_EMAIL);
    }

    public BadRequestException errorExistPhone() {
        return new BadRequestException(MessagesEnum.ERROR_EXIST_PHONE);
    }

    public BadRequestException errorRecordNotExist() {
        return new BadRequestException(MessagesEnum.ERROR_RECORD_NOT_EXIST);
    }

    public BadRequestException errorUserdNotExist() {
        return new BadRequestException(MessagesEnum.ERROR_USER_NOT_EXIST);
    }

    public BadRequestException errorExistRecord() {
        return new BadRequestException(MessagesEnum.ERROR_EXIST_RECORD);
    }

    public ForbiddenException errorForbidden() {
        return new ForbiddenException();
    }

    public DataIntegrityViolationException errorDataIntegrityViolationException() {
        return new DataIntegrityViolationException(MessagesEnum.ERROR_DATA_INTEGRITY_VIOLATION.getMessage());
    }

    public PasswordNotMatchException errorPasswordNotMatchException() {
        return new PasswordNotMatchException();
    }
}
