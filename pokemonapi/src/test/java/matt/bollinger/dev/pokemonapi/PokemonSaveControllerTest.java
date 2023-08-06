package matt.bollinger.dev.pokemonapi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import matt.bollinger.dev.pokemonapi.controllers.PokemonSaveController;
import matt.bollinger.dev.pokemonapi.services.PokemonSaveService;

@WebMvcTest(PokemonSaveController.class)
public class PokemonSaveControllerTest {
    
    private static final String endPoint = "/api/pokemonteam";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private PokemonSaveService service;

	@Test
	public void testAllTeamsRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/allteams").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testSingleTeamRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/getTeam")
			.header("team-name", "Fifth Team")
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testSingleTeamNoHeaderRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/getTeam")
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testSaveExistingGameRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(endPoint + "/save")
			.header("team-name", "Second Team")
			.header("chosen-game", "Hoenn (Gen 3)")
			.header("pokemon-1", List.of("Mudkip", "123", "123"))
			.header("pokemon-2", List.of("Mudkip", "123", "123"))
			.header("pokemon-3", List.of("Mudkip", "123", "123"))
			.header("pokemon-4", List.of("Mudkip", "123", "123"))
			.header("pokemon-5", List.of("Mudkip", "123", "123"))
			.header("pokemon-6", List.of("Mudkip", "123", "123"))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testSaveNewGameRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(endPoint + "/save")
			.header("team-name", "New Team")
			.header("chosen-game", "Hoenn (Gen 3)")
			.header("pokemon-1", List.of("Mudkip", "123", "123"))
			.header("pokemon-2", List.of("Mudkip", "123", "123"))
			.header("pokemon-3", List.of("Mudkip", "123", "123"))
			.header("pokemon-4", List.of("Mudkip", "123", "123"))
			.header("pokemon-5", List.of("Mudkip", "123", "123"))
			.header("pokemon-6", List.of("Mudkip", "123", "123"))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testSaveNoTeamNameRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(endPoint + "/save")
			.header("team-name", "")
			.header("chosen-game", "Hoenn (Gen 3)")
			.header("pokemon-1", List.of("Mudkip", "123", "123"))
			.header("pokemon-2", List.of("Mudkip", "123", "123"))
			.header("pokemon-3", List.of("Mudkip", "123", "123"))
			.header("pokemon-4", List.of("Mudkip", "123", "123"))
			.header("pokemon-5", List.of("Mudkip", "123", "123"))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testSaveMissingHeaderRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(endPoint + "/save")
			.header("team-name", "New Team")
			.header("chosen-game", "Hoenn (Gen 3)")
			.header("pokemon-1", List.of("Mudkip", "123", "123"))
			.header("pokemon-2", List.of("Mudkip", "123", "123"))
			.header("pokemon-3", List.of("Mudkip", "123", "123"))
			.header("pokemon-4", List.of("Mudkip", "123", "123"))
			.header("pokemon-5", List.of("Mudkip", "123", "123"))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
