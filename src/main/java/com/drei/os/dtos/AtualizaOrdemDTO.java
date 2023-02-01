package com.drei.os.dtos;

import java.io.Serializable;

import com.drei.os.domain.Ordem;

import jakarta.validation.constraints.NotNull;

public class AtualizaOrdemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer prioridade;
    @NotNull(message = "Campo STATUS não foi fornecido")
    private Integer status;

    private String observacoes;
    private Integer idTecnico;
    private Integer idCliente;

    public AtualizaOrdemDTO(Ordem inOrdem) {
        this.prioridade = inOrdem.getPrioridade().getCodigo();
        this.observacoes = inOrdem.getObservacoes();
        this.status = inOrdem.getStatus().getCodigo();
        this.idTecnico = inOrdem.getTecnico().getId();
        this.idCliente = inOrdem.getCliente().getId();
    }

    public AtualizaOrdemDTO() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

}
