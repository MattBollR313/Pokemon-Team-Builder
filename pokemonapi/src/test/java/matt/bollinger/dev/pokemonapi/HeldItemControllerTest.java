package matt.bollinger.dev.pokemonapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import matt.bollinger.dev.pokemonapi.controllers.HeldItemController;
import matt.bollinger.dev.pokemonapi.services.HeldItemService;

@WebMvcTest(HeldItemController.class)
public class HeldItemControllerTest {
    
    private static final String endPoint = "/api/helditem";

    @Autowired
	private MockMvc mockMvc;
	@MockBean 
	private HeldItemService service;

    @Test
	public void testProperListResponse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint).contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testProperDescriptionResponse() throws Exception {
		String itemDescription = "Fighting-Type moves from holder do 20% more damage.";
		Mockito.when(service.getHeldItemDescription("black-belt")).thenReturn(itemDescription);
		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/black-belt").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

    @Test
	public void testBadArgumentRequest() throws Exception {
        Mockito.when(service.getHeldItemDescription("999999")).thenThrow(Exception.class);

		mockMvc.perform(MockMvcRequestBuilders.get(endPoint + "/999999").contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
