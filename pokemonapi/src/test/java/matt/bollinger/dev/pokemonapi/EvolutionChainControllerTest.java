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

import matt.bollinger.dev.pokemonapi.controllers.EvolutionChainController;
import matt.bollinger.dev.pokemonapi.services.EvolutionChainService;

@WebMvcTest(EvolutionChainController.class)
public class EvolutionChainControllerTest {
    
    private static final String endPoint = "/api/evolution";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private EvolutionChainService service;

    @Test
	public void testProperListResponse() throws Exception {
		Mockito.when(service.getEvolutionStatus("empoleon")).thenReturn(Arrays.asList(true));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/empoleon").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.when(service.getEvolutionStatus("clefairy")).thenReturn(Arrays.asList(false));
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/clefairy").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testNoArgumentRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint).contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

    @Test
	public void testBadArgumentRequest() throws Exception {
        Mockito.when(service.getEvolutionStatus("123")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/123").contentType("application.json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
