package matt.bollinger.dev.pokemonapi;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import matt.bollinger.dev.pokemonapi.controllers.PokemonTypesController;
import matt.bollinger.dev.pokemonapi.services.PokemonTypesService;

@WebMvcTest(PokemonTypesController.class)
public class PokemonTypesControllerTest {
    
    private static final String endPoint = "/api/types";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private PokemonTypesService service;

    @Test
	public void testOneArgumentRequest() throws Exception {
		Mockito.when(service.getPokemonTypes("clefairy")).thenReturn(Arrays.asList("Fairy"));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/clefairy").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testTwoArgumentRequest() throws Exception {
		Mockito.when(service.getPokemonTypes("clefairy", "johto")).thenReturn(Arrays.asList("Normal"));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/johto/clefairy").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testTwoArgumentRequestWithNewGen() throws Exception {
		Mockito.when(service.getPokemonTypes("clefairy", "galar")).thenReturn(Arrays.asList("Fairy"));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/galar/clefairy").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testNoArgumentRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint).contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testBadArgumentRequest() throws Exception {
		Mockito.when(service.getPokemonTypes("123")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

    @Test
	public void testBadFirstArgumentRequest() throws Exception {
        Mockito.when(service.getPokemonTypes("clefairy", "123")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/123/clefairy").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testBadSecondArgumentRequest() throws Exception {
        Mockito.when(service.getPokemonTypes("123", "johto")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/johto/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
