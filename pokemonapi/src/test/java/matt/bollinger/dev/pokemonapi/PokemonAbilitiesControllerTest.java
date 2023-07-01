package matt.bollinger.dev.pokemonapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import matt.bollinger.dev.pokemonapi.controllers.PokemonAbilitiesController;
import matt.bollinger.dev.pokemonapi.services.PokemonAbilitiesService;

@WebMvcTest(PokemonAbilitiesController.class)
public class PokemonAbilitiesControllerTest {
    
    private static final String endPoint = "/api/abilities";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private PokemonAbilitiesService service;

    @Test
	public void testProperListResponse() throws Exception {
		List<List<String>> abilities = new ArrayList<>(2);
		abilities.add(Arrays.asList("torrent", "Strengthens water moves to inflict 1.5\u00D7 damage at 1/3 max HP or less."));
		abilities.add(Arrays.asList("defiant", "Raises Attack two stages upon having any stat lowered."));
		Mockito.when(service.getPokemonAbilities("empoleon")).thenReturn(abilities);
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
        Mockito.when(service.getPokemonAbilities("123")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
