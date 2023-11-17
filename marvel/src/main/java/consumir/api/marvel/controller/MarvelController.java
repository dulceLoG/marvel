package consumir.api.marvel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import consumir.api.marvel.services.MarvelService;

@Controller
@RequestMapping(value="/marvel")
public class MarvelController {
	
	@Autowired
	MarvelService marvelService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/getCharacters")
	public ResponseEntity<String> getCharacters() throws Exception{
		return new ResponseEntity<>(marvelService.getCharacter(),HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/getPersonaje")
	public ResponseEntity<String> getCharactersById(@RequestParam(value="id")String idPersonaje) throws Exception{
		
		return new ResponseEntity<>(marvelService.getPersonaje(idPersonaje),HttpStatus.OK);
	}
}
