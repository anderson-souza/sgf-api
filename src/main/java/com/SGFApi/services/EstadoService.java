package com.SGFApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SGFApi.domain.Estado;
import com.SGFApi.repository.EstadoRepository;
import com.SGFApi.services.exception.EstadoNaoEncontradoException;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	PaisService paisService;

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	public Estado buscar(Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		if (estado.isEmpty()) {
			throw new EstadoNaoEncontradoException();
		}
		return estado.get();
	}

	public void salvar(Estado estado) {
		estado.setId(null);
		paisService.buscar(estado.getPais().getId());
		estadoRepository.save(estado);
	}

	public void deletar(Long id) {
		verificaExistencia(id);
		estadoRepository.deleteById(id);
	}

	public void atualizar(Estado estado, Long id) {
		estado.setId(id);
		verificaExistencia(id);
		estadoRepository.save(estado);
	}

	private void verificaExistencia(Long id) {
		buscar(id);
	}

	public Estado buscaEstadoPeloNome(String nome) {
		return estadoRepository.findByNome(nome);
	}

}
