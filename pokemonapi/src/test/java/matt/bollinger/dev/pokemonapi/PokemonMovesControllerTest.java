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

import matt.bollinger.dev.pokemonapi.controllers.PokemonMovesController;
import matt.bollinger.dev.pokemonapi.services.PokemonMovesService;

@WebMvcTest(PokemonMovesController.class)
public class PokemonMovesControllerTest {
    
    private static final String endPoint = "/api/moves";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private PokemonMovesService service;

    @Test
	public void testOneArgumentRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/empoleon").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testTwoArgumentRequest() throws Exception {
		String[] moveDetails = {"Inflicts regular damage and can hit Dive users.","Water","95","15","100"};

		Mockito.when(service.getPokemonMoveInfo("surf", "original-sinnoh")).thenReturn(Arrays.asList(moveDetails));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/surf/original-sinnoh").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testTwoArgumentRequestWithNewGen() throws Exception {
		String[] moveDetails = {"Inflicts regular damage and can hit Dive users.","Water","90","15","100"};

		Mockito.when(service.getPokemonMoveInfo("surf", "kalos-central")).thenReturn(Arrays.asList(moveDetails));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/surf/kalos-central").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testNoArgumentRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint).contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testBadArgumentRequest() throws Exception {
		Mockito.when(service.getPokemonMoves("99999")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/99999").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testBadFirstArgumentRequest() throws Exception {
        Mockito.when(service.getPokemonMoveInfo("99999", "original-sinnoh")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/99999/original-sinnoh").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testBadSecondArgumentRequest() throws Exception {
        Mockito.when(service.getPokemonMoveInfo("surf", "123")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/surf/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
