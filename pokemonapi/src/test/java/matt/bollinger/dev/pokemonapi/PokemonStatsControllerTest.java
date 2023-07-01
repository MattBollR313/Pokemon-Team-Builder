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

import matt.bollinger.dev.pokemonapi.controllers.PokemonStatsController;
import matt.bollinger.dev.pokemonapi.services.PokemonStatsService;

@WebMvcTest(PokemonStatsController.class)
public class PokemonStatsControllerTest {
    
    private static final String endPoint = "/api/stats";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private PokemonStatsService service;

    @Test
	public void testProperListResponse() throws Exception {
		Mockito.when(service.getPokemonStats("empoleon")).thenReturn(Arrays.asList(84, 86, 88, 111, 101, 60));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/empoleon").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testNoArgumentRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint).contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

    @Test
	public void testBadArgumentRequest() throws Exception {
        Mockito.when(service.getPokemonStats("123")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
