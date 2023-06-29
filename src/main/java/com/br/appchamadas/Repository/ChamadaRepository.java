package com.br.appchamadas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.appchamadas.Model.Chamada;

@Repository
public interface ChamadaRepository extends JpaRepository<Chamada, Long> {

}
