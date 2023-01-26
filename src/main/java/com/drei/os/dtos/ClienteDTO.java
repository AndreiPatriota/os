package com.drei.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.drei.os.domain.Cliente;

import jakarta.validation.constraints.NotEmpty;

public class ClienteDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo NOME não fornecido")
    private String nome;

    @CPF
    @NotEmpty(message = "Campo CPF não fornecido")
    private String cpf;

    @NotEmpty(message = "Campo TELEFONE não fornecido")
    private String telefone;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente inCliente) {
        this.id = inCliente.getId();
        this.nome = inCliente.getNome();
        this.cpf = inCliente.getCpf();
        this.telefone = inCliente.getTelefone();
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
