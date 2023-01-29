package com.drei.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore 
    private List<Ordem> listadeOrdensdeServicos = new ArrayList<Ordem>();

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<Ordem> getListadeOrdensdeServicos() {
        return listadeOrdensdeServicos;
    }

    public void setListadeOrdensdeServicos(List<Ordem> listaDeOrdemdeServicos) {
        this.listadeOrdensdeServicos = listaDeOrdemdeServicos;
    }

    public Boolean temOrdemdeServico() {
        return this.listadeOrdensdeServicos.size() > 0;
    }
}
