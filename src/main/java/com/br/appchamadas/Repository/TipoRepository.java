package com.br.appchamadas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.appchamadas.Model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {

}
