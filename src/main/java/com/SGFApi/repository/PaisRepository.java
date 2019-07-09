package com.SGFApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGFApi.domain.entity.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {

	public Pais findByNome(String nome);

}
