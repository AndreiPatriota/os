package com.drei.os.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.drei.os.domain.Cliente;
import com.drei.os.domain.Ordem;
import com.drei.os.domain.Tecnico;
import com.drei.os.domain.enums.Prioridade;
import com.drei.os.domain.enums.Status;
import com.drei.os.repositories.ClienteRepository;
import com.drei.os.repositories.OrdemRepository;
import com.drei.os.repositories.TecnicoRepository;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private OrdemRepository ordemdeRepository;

    public void instanciaDB() {
        // instancia objetos
        var tecnico1 = new Tecnico(null,
                "Bjarne",
                "190.390.314-95",
                "(84)22222-2222");
        
        var tecnico2 = new Tecnico(null,
                "Linus",
                "314.865.724-13",
                "(83)11111-1111");

        var umCliente = new Cliente(null,
                "Kitty",
                "191.463.864-67",
                "(87) 99922-1173");

        var umaOrdem = new Ordem(null,
                Prioridade.ALTA, "Fazer pra ontem", Status.ANDAMENTO,
                tecnico1, umCliente);

        // liga a ordem ao tecnico/cliente
        tecnico1.getListaDeOrdemdeServicos().add(umaOrdem);
        umCliente.getListadeOrdensdeServicos().add(umaOrdem);

        // salva os registros na BD
        clienteRepository.saveAll(Arrays.asList(umCliente));
        tecnicoRepository.saveAll(Arrays.asList(tecnico1,tecnico2));
        ordemdeRepository.saveAll(Arrays.asList(umaOrdem));

    }
}
