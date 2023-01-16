package com.drei.os.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.drei.os.domain.Cliente;
import com.drei.os.domain.OrdemdeServico;
import com.drei.os.domain.Tecnico;
import com.drei.os.domain.enums.Prioridade;
import com.drei.os.domain.enums.Status;
import com.drei.os.repositories.ClienteRepository;
import com.drei.os.repositories.OrdemdeServicoRepository;
import com.drei.os.repositories.TecnicoRepository;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private OrdemdeServicoRepository ordemdeServicoRepository;

    public void instanciaDB() {
        // instancia objetos
        var umTecnico = new Tecnico(null,
                "Andrei",
                "068.237.974-36",
                "(99)99999-9999");

        var umCliente = new Cliente(null,
                "Kitty",
                "191.463.864-67",
                "(87) 99922-1173");

        var umaOrdem = new OrdemdeServico(null,
                Prioridade.ALTA, "Fazer pra ontem", Status.ANDAMENTO,
                umTecnico, umCliente);

        // liga a ordem ao tecnico/cliente
        umTecnico.getListaDeOrdemdeServicos().add(umaOrdem);
        umCliente.getListaDeOrdemdeServicos().add(umaOrdem);

        // salva os registros na BD
        clienteRepository.saveAll(Arrays.asList(umCliente));
        tecnicoRepository.saveAll(Arrays.asList(umTecnico));
        ordemdeServicoRepository.saveAll(Arrays.asList(umaOrdem));

    }
}
