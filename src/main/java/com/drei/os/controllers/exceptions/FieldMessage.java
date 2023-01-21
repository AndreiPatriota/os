package com.drei.os.controllers.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String campoErro;
    private String messagemErro;

    public FieldMessage(String campoErro, String messagemErro) {
        this.campoErro = campoErro;
        this.messagemErro = messagemErro;
    }

    public FieldMessage() {
    }

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getCampoErro() {
        return this.campoErro;
    }

    public void setCampoErro(String campoErro) {
        this.campoErro = campoErro;
    }

    public String getMessagemErro() {
        return messagemErro;
    }

    public void setMessagemErro(String mensagemErro) {
        this.messagemErro = mensagemErro;
    }

}
