package com.drei.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    @OneToMany(mappedBy = "tecnico")
    @JsonIgnore
    private List<OrdemdeServico> listaDeOrdemdeServicos = new ArrayList<OrdemdeServico>();

    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<OrdemdeServico> getListaDeOrdemdeServicos() {
        return listaDeOrdemdeServicos;
    }

    public void setListaDeOrdemdeServicos(List<OrdemdeServico> listaDeOrdemdeServicos) {
        this.listaDeOrdemdeServicos = listaDeOrdemdeServicos;
    }
    
}
