package com.SGFApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SGFApi.domain.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	public Estado findByNome(String nome);

}
