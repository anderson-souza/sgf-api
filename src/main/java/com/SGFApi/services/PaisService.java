package com.SGFApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.SGFApi.domain.Pais;
import com.SGFApi.repository.PaisRepository;
import com.SGFApi.services.exception.PaisNaoEncontradoException;

@Service
public class PaisService {

	@Autowired
	private PaisRepository paisRepository;

	public List<Pais> listar() {
		return paisRepository.findAll();
	}

	public Pais buscar(Long id) {
		Optional<Pais> pais = paisRepository.findById(id);

		if (pais.isEmpty()) {
			throw new PaisNaoEncontradoException("País não foi encontrado");
		}

		return pais.get();
	}

	public Pais salvar(Pais pais) {
		pais.setId(null); // Utilizado para garantir que será salvo um novo país
		return paisRepository.save(pais);
	}

	public void deletar(Long id) {
		try {
			paisRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PaisNaoEncontradoException("Livro não pode ser encontrado");
		}

	}

	public void atualizar(Pais pais) {
		verificarExistencia(pais);
		paisRepository.save(pais);
	}

	private void verificarExistencia(Pais pais) {
		buscar(pais.getId());
	}

	public Pais buscaPaisPeloNome(String nome) {
		return paisRepository.findByNome(nome);
	}

}
