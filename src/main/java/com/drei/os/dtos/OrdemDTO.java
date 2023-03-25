package com.drei.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.drei.os.domain.Ordem;
import com.drei.os.domain.enums.Prioridade;
import com.drei.os.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrdemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    
    private Integer prioridade;
    private Integer status;

    @NotEmpty(message = "Campo OBSERVAÇÕES não foi fornecido")
    private String observacoes;
    @NotNull(message = "Campo TÉCNICO não foi fornecido")
    private Integer idTecnico;
    @NotNull(message = "Campo CLIENTE não foi fornecido")
    private Integer idCliente;

    public OrdemDTO(Ordem inOrdem) {
        this.id = inOrdem.getId();
        this.dataAbertura = inOrdem.getDataAbertura();
        this.dataFechamento = inOrdem.getDataFechamento();
        this.prioridade = inOrdem.getPrioridade().getCodigo();
        this.observacoes = inOrdem.getObservacoes();
        this.status = inOrdem.getStatus().getCodigo();
        this.idTecnico = inOrdem.getTecnico().getId();
        this.idCliente = inOrdem.getCliente().getId();
    }

    public OrdemDTO() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return Prioridade.toEnum(this.prioridade);
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

    public Status getStatus() {
        return Status.toEnum(this.status);
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
