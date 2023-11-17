package consumir.api.marvel.services;


public interface MarvelService {
	
	String getCharacter() throws Exception;
	
	String getPersonaje(String idPersonaje)throws Exception;

}
