package com.drei.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.drei.os.domain.Ordem;

@Repository
public interface OrdemRepository extends JpaRepository<Ordem, Integer> {

}
