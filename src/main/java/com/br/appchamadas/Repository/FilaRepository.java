package com.br.appchamadas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.appchamadas.Model.Fila;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {

}
