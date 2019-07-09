package com.SGFApi.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.SGFApi.enums.Status;

@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "O nome da cidade não pode ser vazio ou nulo")
	@Size(min = 2, max = 100, message = "O nome da cidade deve ser entre 2 e 100 caracteres")
	private String nome;

	@NotNull(message = "O estado informado não pode ser nulo")
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	@NotNull(message = "O status da Cidade não pode ser nulo")
	private Status status;

	public Cidade(long id,
			@NotEmpty(message = "O nome da cidade não pode ser vazio ou nulo") @Size(min = 2, max = 100, message = "O nome da cidade deve ser entre 2 e 100 caracteres") String nome,
			@NotNull(message = "O estado informado não pode ser nulo") Estado estado,
			@NotNull(message = "O status da Cidade não pode ser nulo") Status status) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
