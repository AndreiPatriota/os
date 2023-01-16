package com.drei.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable {
    private static final Long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore 
    private List<OrdemdeServico> listaDeOrdemdeServicos = new ArrayList<OrdemdeServico>();

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<OrdemdeServico> getListaDeOrdemdeServicos() {
        return listaDeOrdemdeServicos;
    }

    public void setListaDeOrdemdeServicos(List<OrdemdeServico> listaDeOrdemdeServicos) {
        this.listaDeOrdemdeServicos = listaDeOrdemdeServicos;
    }
}
