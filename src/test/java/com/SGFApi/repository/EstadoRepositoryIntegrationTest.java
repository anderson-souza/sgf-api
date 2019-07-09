package com.SGFApi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.SGFApi.domain.entity.Estado;
import com.SGFApi.domain.entity.Pais;
import com.SGFApi.enums.Status;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class EstadoRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private PaisRepository paisRespository;

	@Test
	public void whenFindByNome_thenReturnEstado() {
		Estado estado = new Estado();
		estado.setNome("Paraná");
		Pais brasil = paisRespository.save(new Pais("Brasil"));
		estado.setPais(brasil);
		estado.setStatus(Status.Ativo);
		testEntityManager.persist(estado);
		testEntityManager.flush();

		Estado encontrado = estadoRepository.findByNome(estado.getNome());

		assertThat(encontrado.getNome()).isEqualTo(estado.getNome());
	}

}
