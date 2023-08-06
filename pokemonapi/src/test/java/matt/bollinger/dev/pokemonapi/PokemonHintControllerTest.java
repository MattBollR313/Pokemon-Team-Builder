package matt.bollinger.dev.pokemonapi;

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

import matt.bollinger.dev.pokemonapi.controllers.PokemonHintController;
import matt.bollinger.dev.pokemonapi.services.PokemonHintService;

@WebMvcTest(PokemonHintController.class)
public class PokemonHintControllerTest {
    
    private static final String endPoint = "/api/hints";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private PokemonHintService service;

	@Test
	public void testMissingHeaderRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

    @Test
	public void testEmptyRequest() throws Exception {
		String[] allHints = {"Six Pokemon have not been added to your team. Consider filling out the remaining team"};

		Mockito.when(service.getHints(List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))).thenReturn(Arrays.asList(allHints));

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-6", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testNotHighestEvolutionRequest() throws Exception {
		String[] allHints = {"Clefairy is not fully evolved. Consider swapping the Pokemon for its final evolution", "Six Pokemon have not been added to your team. Consider filling out the remaining team"};

		Mockito.when(service.getHints(
			List.of("Clefairy", "100", "100", "100", "100", "100", "100", "Fairy", "", "Not Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))).thenReturn(Arrays.asList(allHints));

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", 
				List.of("Clefairy", "100", "100", "100", "100", "100", "100", "Fairy", "", "Not Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-6", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testMultipleNotHighestEvolutionRequest() throws Exception {
		String[] allHints = {"Clefairy, Magikarp are not fully evolved. Consider swapping the Pokemon for its final evolution", "Six Pokemon have not been added to your team. Consider filling out the remaining team"};

		Mockito.when(service.getHints(
			List.of("Clefairy", "100", "100", "100", "100", "100", "100", "Fairy", "", "Not Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("Magikarp", "100", "100", "100", "100", "100", "100", "Water", "", "Not Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))).thenReturn(Arrays.asList(allHints));

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", 
				List.of("Clefairy", "100", "100", "100", "100", "100", "100", "Fairy", "", "Not Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", 
				List.of("Magikarp", "100", "100", "100", "100", "100", "100", "Water", "", "Not Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-6", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDuplicateTypesRequest() throws Exception {
		String[] allHints = {"Steel is duplicately covered by your team. Consider changing your team lineup to cover a more diverse set of types", "Six Pokemon have not been added to your team. Consider filling out the remaining team"};

		Mockito.when(service.getHints(
			List.of("Empoleon", "100", "100", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("Steelix", "100", "100", "100", "100", "100", "100", "Steel", "", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))).thenReturn(Arrays.asList(allHints));

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", 
				List.of("Empoleon", "100", "100", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", 
				List.of("Steelix", "100", "100", "100", "100", "100", "100", "Steel", "", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-6", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testMultipleDuplicateTypesRequest() throws Exception {
		String[] allHints = {"Water, Steel are duplicately covered by your team. Consider changing your team lineup to cover a more diverse set of types", "Six Pokemon have not been added to your team. Consider filling out the remaining team"};

		Mockito.when(service.getHints(
			List.of("Empoleon", "100", "100", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("Steelix", "100", "100", "100", "100", "100", "100", "Steel", "", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("Gyarados", "100", "100", "100", "100", "100", "100", "Water", "Flying", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))).thenReturn(Arrays.asList(allHints));

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", 
				List.of("Empoleon", "100", "100", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", 
				List.of("Steelix", "100", "100", "100", "100", "100", "100", "Steel", "", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", 
				List.of("Gyarados", "100", "100", "100", "100", "100", "100", "Water", "Flying", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-6", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testOneBaseStatUnderBaselineRequest() throws Exception {
		String[] allHints = {"HP does not have any Pokemon above the 90 baseline. Consider picking a Pokemon with a higher HP", "Six Pokemon have not been added to your team. Consider filling out the remaining team"};

		Mockito.when(service.getHints(
			List.of("Empoleon", "89", "100", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))).thenReturn(Arrays.asList(allHints));

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", 
				List.of("Empoleon", "89", "100", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-6", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testMultipleBaseStatsUnderBaselineRequest() throws Exception {
		String[] allHints = {"HP, Attack do not have any Pokemon above the 90 baseline. Consider picking Pokemon with higher statistics", "Six Pokemon have not been added to your team. Consider filling out the remaining team"};

		Mockito.when(service.getHints(
			List.of("Empoleon", "89", "10", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""), 
			List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))).thenReturn(Arrays.asList(allHints));

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint)
			.header("pokemon-1", 
				List.of("Empoleon", "89", "10", "100", "100", "100", "100", "Water", "Steel", "Final Stage", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-2", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-3", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-4", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-5", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.header("pokemon-6", List.of("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
			.contentType("application/json")).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
