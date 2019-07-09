package com.SGFApi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.SGFApi.domain.dto.PaisDTO;
import com.SGFApi.domain.entity.Pais;
import com.SGFApi.repository.PaisRepository;
import com.SGFApi.services.exception.PaisNaoEncontradoException;

@Service
public class PaisService {

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Pais> listarV1() {
		return paisRepository.findAll();
	}

	public List<PaisDTO> listarV2() {

		List<Pais> paises = paisRepository.findAll();
		List<PaisDTO> paisesDTO = new ArrayList<>();

		paises.forEach(pais -> paisesDTO.add(converterParaDTO(pais)));

		return paisesDTO;
	}

	public Pais buscarV1(Long id) {
		Optional<Pais> pais = paisRepository.findById(id);

		if (pais.isEmpty()) {
			throw new PaisNaoEncontradoException("País não foi encontrado");
		}

		return pais.get();
	}

	public PaisDTO buscarV2(Long id) {
		Optional<Pais> pais = paisRepository.findById(id);

		if (pais.isEmpty()) {
			throw new PaisNaoEncontradoException("País não foi encontrado");
		}

		return converterParaDTO(pais.get());
	}

	public Pais salvarV1(Pais pais) {
		pais.setId(null); // Utilizado para garantir que será salvo um novo país
		return paisRepository.save(pais);
	}

	public Pais salvarV2(PaisDTO paisDTO) {
		paisDTO.setId(null);
		Pais pais = new Pais();
		try {
			pais = converterParaEntidade(paisDTO);
		} catch (Exception e) {
			e.getMessage();
		}

		return paisRepository.save(pais);
	}

	public void deletar(Long id) {
		try {
			paisRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PaisNaoEncontradoException("Livro não pode ser encontrado");
		}

	}

	public void atualizarV1(Pais pais) {
		verificarExistencia(pais);
		paisRepository.save(pais);
	}

	public void atualizarV2(PaisDTO paisDTO) {
		Pais pais = new Pais();
		try {
			pais = converterParaEntidade(paisDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		verificarExistencia(pais);
		paisRepository.save(pais);
	}

	private void verificarExistencia(Pais pais) {
		buscarV1(pais.getId());
	}

	public Pais buscaPaisPeloNome(String nome) {
		return paisRepository.findByNome(nome);
	}

	public PaisDTO converterParaDTO(Pais pais) {
		return modelMapper.map(pais, PaisDTO.class);
	}

	public Pais converterParaEntidade(PaisDTO paisDTO) throws Exception {
		Pais pais = modelMapper.map(paisDTO, Pais.class);

		return pais;
	}

}
