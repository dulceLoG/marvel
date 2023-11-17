package consumir.api.marvel;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import consumir.api.marvel.repository.BitacoraRepository;
import consumir.api.marvel.services.MarvelService;

import org.springframework.http.MediaType;

@WebMvcTest
class MarvelApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  MarvelService marvelService;
	
	@MockBean
	private  BitacoraRepository bitacoraRepository;

	
	@Test
	void contextLoads() {
	}
	
	
	@Test
	void testMarvelCharacters() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/marvel/getCharacters").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	void testMarvelCharactersById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/marvel/getPersonaje?id=1017100").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	void testMarvelCharactersSave() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/marvel/getCharacters").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(marvelService).getCharacter();
	}
	
	@Test
	void testMarvelPersonajes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/marvel/getPersonaje?id=1017100").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(marvelService).getPersonaje("1017100");
	}
}
