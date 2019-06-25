package com.SGFApi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.SGFApi.domain.Pais;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PaisRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private PaisRepository paisRepository;

	@Test
	public void whenFindByNome_thenReturnPais() {
		Pais pais = new Pais("Brasil");
		testEntityManager.persist(pais);
		testEntityManager.flush();

		Pais encontrado = paisRepository.findByNome(pais.getNome());

		assertThat(encontrado.getNome()).isEqualTo(pais.getNome());
	}

}
