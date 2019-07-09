package com.SGFApi.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.SGFApi.domain.entity.Estado;
import com.SGFApi.domain.entity.Pais;
import com.SGFApi.enums.Status;
import com.SGFApi.repository.EstadoRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EstadoServiceIntegrationTest {

	@TestConfiguration
	static class EstadoServiceIntegrationTestContextConfiguration {
		@Bean
		public EstadoService estadoService() {
			return new EstadoService();
		}
	}

	@Autowired
	private EstadoService estadoService;

	@MockBean
	private PaisService paisService;

	@MockBean
	private EstadoRepository estadoRepository;

	@Before
	public void setUp() {
		Estado parana = new Estado();
		Pais brasil = paisService.salvarV1(new Pais("Brasil"));
		parana.setNome("Paraná");
		parana.setStatus(Status.Ativo);
		parana.setPais(brasil);
		Mockito.when(estadoRepository.findByNome(parana.getNome())).thenReturn(parana);
	}

	@Test
	public void whenValidName_thenEstadoShouldBeFound() {
		String nome = "Paraná";
		Estado encontrado = estadoService.buscaEstadoPeloNome(nome);

		assertThat(encontrado.getNome()).isEqualTo(nome);
	}
}
