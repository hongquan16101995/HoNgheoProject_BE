package com.codegym.hongheo.core.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class NotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    private final String errorKey;

    public NotFoundException(String errorKey) {
        this(null, errorKey);
    }

    public NotFoundException(String defaultMessage, String errorKey) {
        this(DEFAULT_TYPE, defaultMessage, errorKey);
    }

    public NotFoundException(URI type, String defaultMessage, String errorKey) {
        super(type, defaultMessage, Status.NOT_FOUND, null, null, null, getAlertParameters(defaultMessage));
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, Object> getAlertParameters(String defaultMessage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", defaultMessage);
        return parameters;
    }

}