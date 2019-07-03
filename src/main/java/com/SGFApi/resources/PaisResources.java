package com.SGFApi.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SGFApi.domain.Pais;
import com.SGFApi.dto.PaisDTO;
import com.SGFApi.services.PaisService;

@RestController
@CrossOrigin
public class PaisResources {

	static final String mapping = "/pais";

	@Autowired
	private PaisService paisService;

	/**
	 * Função para listar todos os países cadastrados
	 * 
	 * @return Lista com todos os paises que estão no banco de dados
	 */
	@GetMapping("/V1" + mapping)
	public List<Pais> listarV1() {
		return paisService.listarV1();
	}

	@GetMapping("/V2" + mapping)
	@ResponseBody
	public List<PaisDTO> listarV2() {

		return paisService.listarV2();
	}

	/**
	 * Insere um Pais no banco de dados
	 * 
	 * @param pais País a ser inserido no banco de dados
	 */
	@PostMapping(mapping)
	public ResponseEntity<Void> cadastrar(@Valid @RequestBody Pais pais) {

		pais = paisService.salvar(pais);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pais.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	/**
	 * Busca um país específico no banco de dados
	 * 
	 * @param id ID do país a ser pesquisado
	 * @return Caso encontre o país, retorna o mesmo. Caso não encontre, retorna um
	 *         erro 404
	 */
	@GetMapping(mapping + "/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Pais pais = paisService.buscar(id);
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(pais);
	}

	/**
	 * Deleta um pais no banco de dados
	 * 
	 * @param id ID do país a ser deletado
	 */
	@DeleteMapping(mapping + "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		paisService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Atualiza um pais no banco de dados
	 * 
	 * @param pais Dados do país para atualizar
	 * @param id   ID do país para atualizar
	 */
	@PutMapping(mapping + "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Pais pais, @PathVariable Long id) {
		pais.setId(id);
		paisService.atualizar(pais);
		return ResponseEntity.noContent().build();
	}

}
