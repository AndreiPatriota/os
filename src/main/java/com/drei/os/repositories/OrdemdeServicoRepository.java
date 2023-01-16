package com.drei.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.drei.os.domain.OrdemdeServico;

@Repository
public interface OrdemdeServicoRepository extends JpaRepository<OrdemdeServico, Integer> {

}
