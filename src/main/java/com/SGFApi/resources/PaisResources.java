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

import com.SGFApi.domain.dto.PaisDTO;
import com.SGFApi.domain.entity.Pais;
import com.SGFApi.services.PaisService;

@RestController
@CrossOrigin
public class PaisResources {

	private static final String PATH = "/pais";

	@Autowired
	private PaisService paisService;

	/**
	 * Função para listar todos os países cadastrados
	 * 
	 * @return Lista com todos os paises que estão no banco de dados
	 */
	@GetMapping("/V1" + PATH)
	public List<Pais> listarV1() {
		return paisService.listarV1();
	}

	/**
	 * Função para listar todos os países cadastrados. Nesta versão, passou a
	 * retornar DTOs ao invés da própria entity.
	 * 
	 * @return Lista com todos os paises que estão no banco de dados
	 */
	@GetMapping("/V2" + PATH)
	@ResponseBody
	public List<PaisDTO> listarV2() {

		return paisService.listarV2();
	}

	/**
	 * Insere um Pais no banco de dados
	 * 
	 * @param pais País a ser inserido no banco de dados
	 */
	@PostMapping("/V1" + PATH)
	public ResponseEntity<Void> cadastrarV1(@Valid @RequestBody Pais pais) {

		pais = paisService.salvarV1(pais);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pais.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	/**
	 * Insere um Pais no banco de dados. Nesta versão, passou a receber um DTO ao
	 * invés da própria entity.
	 * 
	 * @param paisDTO DTO do País a ser inserido no banco de dados
	 */
	@PostMapping("/V2" + PATH)
	public ResponseEntity<Void> cadastrarV2(@Valid @RequestBody PaisDTO paisDTO) {

		Pais pais = paisService.salvarV2(paisDTO);
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
	@GetMapping("/V1" + PATH + "/{id}")
	public ResponseEntity<?> buscarV1(@PathVariable Long id) {
		Pais pais = paisService.buscarV1(id);
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(pais);
	}

	/**
	 * Busca um país específico no banco de dados. Nesta versão, vão retornar um
	 * DTO.
	 * 
	 * @param id ID do país a ser pesquisado
	 * @return Caso encontre o país, retorna o DTO do mesmo. Caso não encontre,
	 *         retorna um erro 404
	 */
	@GetMapping("/V2" + PATH + "/{id}")
	public ResponseEntity<?> buscarV2(@PathVariable Long id) {
		PaisDTO paisDTO = paisService.buscarV2(id);
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(paisDTO);
	}

	/**
	 * Deleta um pais no banco de dados
	 * 
	 * @param id ID do país a ser deletado
	 */
	@DeleteMapping("/V1" + PATH + "/{id}")
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
	@PutMapping("/V1" + PATH + "/{id}")
	public ResponseEntity<Void> atualizarV1(@RequestBody Pais pais, @PathVariable Long id) {
		pais.setId(id);
		paisService.atualizarV1(pais);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Atualiza um pais no banco de dados. Nesta versão, espera um DTO para
	 * atualizar.
	 * 
	 * @param paisDTO Dados do país para atualizar
	 * @param id      ID do país para atualizar
	 */
	@PutMapping("/V2" + PATH + "/{id}")
	public ResponseEntity<Void> atualizarV2(@RequestBody PaisDTO paisDTO, @PathVariable Long id) {
		paisDTO.setId(id);
		paisService.atualizarV2(paisDTO);
		return ResponseEntity.noContent().build();
	}

}
