package com.SGFApi.dto;

import com.SGFApi.domain.Pais;
import com.SGFApi.enums.Status;

public class EstadoDTO {

	private Long id;
	private String nome;
	private Pais pais;
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
