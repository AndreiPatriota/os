package com.drei.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.drei.os.domain.Tecnico;

import jakarta.validation.constraints.NotEmpty;

public class TecnicoDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo NOME não fornecido")
    private String nome;

    @CPF
    @NotEmpty(message = "Campo CPF não fornecido")
    private String cpf;

    @NotEmpty(message = "Campo TELEFONE não fornecido")
    private String telefone;

    public TecnicoDTO(Tecnico umTecnico) {
        this.id = umTecnico.getId();
        this.nome = umTecnico.getNome();
        this.cpf = umTecnico.getCpf();
        this.telefone = umTecnico.getTelefone();
    }

    public TecnicoDTO() {
    }

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
