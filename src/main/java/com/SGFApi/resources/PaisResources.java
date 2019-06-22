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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SGFApi.domain.Pais;
import com.SGFApi.services.PaisService;

@RestController
@RequestMapping("/pais")
public class PaisResources {

	@Autowired
	private PaisService paisService;

	/**
	 * Função para listar todos os países cadastrados
	 * 
	 * @return Lista com todos os paises que estão no banco de dados
	 */
	@CrossOrigin
	@GetMapping
	public List<Pais> listar() {
		return paisService.listar();
	}

	/**
	 * Insere um Pais no banco de dados
	 * 
	 * @param pais País a ser inserido no banco de dados
	 */
	@PostMapping
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
	@GetMapping("/{id}")
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
	@DeleteMapping("/{id}")
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
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Pais pais, @PathVariable Long id) {
		pais.setId(id);
		paisService.atualizar(pais);
		return ResponseEntity.noContent().build();
	}

}
