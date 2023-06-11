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

import matt.bollinger.dev.pokemonapi.controllers.PokedexController;
import matt.bollinger.dev.pokemonapi.services.PokedexService;

@WebMvcTest(PokedexController.class)
public class PokedexControllerTest {

	private static final String endPoint = "/api/pokedex";

	@Autowired
	private MockMvc mockMvc;
	@MockBean 
	private PokedexService service;

	@Test
	public void testProperListResponse() throws Exception {
		String[] pokemonGameNames = {"Kanto (Gen 1/3)","Johto (Gen 2)","Hoenn (Gen 3)","Sinnoh (DP Gen 4)","Sinnoh (Plat Gen 4)","Johto (Gen 4)","Unova (BW Gen 5)","Unova (B2W2 Gen 5)","Kalos (Gen 6)","Hoenn (Gen 6)","Alola (SM Gen 7)","Alola (USUM Gen 7)","Kanto (Let's Go)","Galar (Gen 8)","Hisui (Gen 9)","Paldea (Gen 9)"};
        List<String> correctPokemonGames = new ArrayList<String>(Arrays.asList(pokemonGameNames));
		
		Mockito.when(service.allPokedex()).thenReturn(correctPokemonGames);
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint).contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}