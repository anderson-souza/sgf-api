package com.SGFApi;

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

import com.SGFApi.domain.Pais;
import com.SGFApi.enums.Status;
import com.SGFApi.repository.PaisRepository;
import com.SGFApi.services.PaisService;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PaisServiceIntegrationTest {

	@TestConfiguration
	static class PaisServiceIntegrationTestContextConfiguration {
		@Bean
		public PaisService paisService() {
			return new PaisService();
		}
	}

	@Autowired
	private PaisService paisService;

	@MockBean
	private PaisRepository paisRepository;

	@Before
	public void setUp() {
		Pais brasil = new Pais();
		brasil.setNome("Brasil");
		brasil.setStatus(Status.Ativo);
		Mockito.when(paisRepository.findByNome(brasil.getNome())).thenReturn(brasil);
	}

	@Test
	public void whenValidName_thenPaisShouldBeFound() {
		String nome = "Brasil";
		Pais encontrado = paisService.buscaPaisPeloNome(nome);

		assertThat(encontrado.getNome()).isEqualTo(nome);
	}
}
