package com.SGFApi.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.SGFApi.domain.Estado;
import com.SGFApi.domain.Pais;
import com.SGFApi.enums.Status;
import com.SGFApi.services.EstadoService;
import com.SGFApi.services.PaisService;

@RunWith(SpringRunner.class)
@WebMvcTest(EstadoResources.class)
public class EstadoResourceIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EstadoService service;

	@MockBean
	private PaisService paisService;

	@Test
	@WithMockUser(username = "anderson", password = "anderson", roles = "USER")
	public void givenEstado_whenListar_thenReturnJsonArray() throws Exception {
		Estado estado = new Estado();
		Pais brasil = paisService.salvar(new Pais("Brasil"));
		estado.setNome("Paraná");
		estado.setStatus(Status.Ativo);
		estado.setPais(brasil);

		List<Estado> todosEstados = Arrays.asList(estado);

		given(service.listar()).willReturn(todosEstados);

		mvc.perform(get("/estado").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].nome", is(estado.getNome())));
	}

}
