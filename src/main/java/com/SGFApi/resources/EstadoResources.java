package com.SGFApi.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SGFApi.domain.Estado;
import com.SGFApi.dto.EstadoDTO;
import com.SGFApi.services.EstadoService;

@RestController
public class EstadoResources {

	static final String mapping = "/estado";

	@Autowired
	EstadoService estadoService;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping(path = "/V1" + mapping, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Estado> listarv1() {
		return estadoService.listar();
	}

	@GetMapping(path = "/V2" + mapping, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<EstadoDTO> listarv2() {
		List<Estado> estados = estadoService.listar();
		List<EstadoDTO> estadosDTO = new ArrayList<>();
		for (Estado estado : estados) {
			estadosDTO.add(modelMapper.map(estado, EstadoDTO.class));
		}
		return estadosDTO;
	}

	@GetMapping(mapping + "/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {
		Estado estado = estadoService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(estado);
	}

	@PostMapping(mapping)
	public ResponseEntity<Void> cadastrar(@RequestBody Estado estado) {
		estadoService.salvar(estado);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estado.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(mapping + "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		estadoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(mapping + "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Estado estado, @PathVariable Long id) {
		estadoService.atualizar(estado, id);
		return ResponseEntity.noContent().build();
	}

}
