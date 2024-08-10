package com.fmatheus.app.hexagonal.infra.adapter.input.exception.handler;


import com.fmatheus.app.hexagonal.infra.adapter.input.enumerable.MessagesEnum;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.BadRequestException;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.ForbiddenException;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.PasswordNotMatchException;
import com.fmatheus.app.hexagonal.infra.adapter.input.exception.UserInactiveException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Classe que captura as excecoes da api. A anotacao @ControllerAdvice significa
 * que esta classe ira monitorar todas as execoes da api.
 *
 * @author Fernando Matheus
 */
@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    private String message;

    private String cause;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        this.message = ex.getMessage();
        var error = this.errorMessageResponse(this.cause, this.message);
        Optional<MessagesEnum> optional = this.returnEnum(this.message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), optional.isPresent() ? optional.get().getHttpSttus() : MessagesEnum.ERROR_NOT_READABLE.getHttpSttus(), request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var erros = this.createErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, status, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        this.message = ex.getMessage();
        var error = this.errorMessageResponse(this.cause, this.message);
        Optional<MessagesEnum> optional = this.returnEnum(this.message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), optional.isPresent() ? optional.get().getHttpSttus() : MessagesEnum.ERROR_BAD_REQUEST.getHttpSttus(), request);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<Object> handleForbiddenException(RuntimeException ex, WebRequest request) {
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        this.message = ex.getMessage();
        var error = this.errorMessageResponse(this.cause, this.message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), MessagesEnum.ERROR_NOT_PERMISSION.getHttpSttus(), request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PasswordNotMatchException.class})
    public ResponseEntity<Object> handlePasswordNotMatchException(RuntimeException ex, WebRequest request) {
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        this.message = ex.getMessage();
        var error = this.errorMessageResponse(this.cause, this.message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), MessagesEnum.ERROR_PASSWORD_NOT_MATCH.getHttpSttus(), request);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(RuntimeException ex, WebRequest request) {
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        this.message = ex.getMessage();
        var error = this.errorMessageResponse(this.cause, this.message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), MessagesEnum.ERROR_DATA_INTEGRITY_VIOLATION.getHttpSttus(), request);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        this.message = ex.getMessage();
        var error = this.errorMessageResponse(this.cause, this.message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), MessagesEnum.ERROR_NOT_FOUND.getHttpSttus(), request);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UserInactiveException.class})
    public ResponseEntity<Object> handleUserInactiveException(RuntimeException ex, WebRequest request) {
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        this.message = ex.getMessage();
        var error = this.errorMessageResponse(this.cause, this.message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), MessagesEnum.ERROR_NOT_UNAUTHORIZED.getHttpSttus(), request);
    }

    private MessageResponseHandler createErros(BindingResult result) {
        this.cause = "Argumentos Inválidos";
        var messegeResponse = this.errorMessageResponse(this.cause, MessagesEnum.ERROR_BAD_REQUEST.getMessage());
        List<FieldsResponseHandler> fields = new ArrayList<>();
        result.getFieldErrors().forEach(fieldErro -> {
            this.message = this.messageSource.getMessage(fieldErro, LocaleContextHolder.getLocale());
            var field = FieldsResponseHandler.builder()
                    .field(fieldErro.getField())
                    .message(this.message)
                    .build();
            fields.add(field);
        });
        messegeResponse.setFields(fields);
        return messegeResponse;
    }

    private MessageResponseHandler errorMessageResponse(String cause, String message) {
        Optional<MessagesEnum> optional = this.returnEnum(message);
        var newMessage = this.messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
        return optional.map(messegeEnum -> new MessageResponseHandler(messegeEnum, cause, newMessage)).orElseGet(
                () -> new MessageResponseHandler(MessagesEnum.ERROR_BAD_REQUEST, cause, newMessage));
    }

    private Optional<MessagesEnum> returnEnum(String message) {
        return Arrays.stream(MessagesEnum.values()).filter(filter -> filter.getMessage().equalsIgnoreCase(message)).findFirst();
    }


}