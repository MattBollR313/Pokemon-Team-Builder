package matt.bollinger.dev.pokemonapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matt.bollinger.dev.pokemonapi.controllers.SamplePokemonController;
import matt.bollinger.dev.pokemonapi.services.SamplePokemonService;

@WebMvcTest(SamplePokemonController.class)
public class SamplePokemonControllerTest {

	private static final String endPoint = "/api/samplepokemon";

	@Autowired
	private MockMvc mockMvc;
	@MockBean 
	private SamplePokemonService service;

	@Test
	public void testProperListResponse() throws Exception {
		String[] pokemonNames = {"Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard"};
		List<String> correctPokemon = new ArrayList<String>(Arrays.asList(pokemonNames));
		
		Mockito.when(service.sixPokemon()).thenReturn(correctPokemon);
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint).contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
