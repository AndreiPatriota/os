package com.drei.os.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final Long serialVersionUID = 1L;

    private List<FieldMessage> listadeErros = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public ValidationError() {
        super();
    }

    public List<FieldMessage> getListadeErros() {
        return this.listadeErros;
    }

    public void setErros(String nomeErro, String mensagemErro) {
        this.listadeErros.add(new FieldMessage(nomeErro, mensagemErro));
    }

}
