package consumir.api.marvel.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consumir.api.marvel.entity.Bitacora;
import consumir.api.marvel.repository.BitacoraRepository;

@Service
public class MarvelServiceImpl implements MarvelService{
	@Autowired
	BitacoraRepository bitacoraRepository;
	
	@Override
	public String getCharacter() throws Exception {
		Bitacora bitacora = new Bitacora();
		bitacora.setHoraConsulta(LocalTime.now());
		bitacoraRepository.save(bitacora);
		return this.consumirApiMarvel("http://gateway.marvel.com/v1/public/characters");
	}

	@Override
	public String getPersonaje(String idPersonaje) throws Exception {
		Bitacora bitacora = new Bitacora();
		bitacora.setHoraConsulta(LocalTime.now());
		bitacoraRepository.save(bitacora);
		return this.consumirApiMarvel("http://gateway.marvel.com/v1/public/characters/"+idPersonaje);
	}

	private String consumirApiMarvel(String urlMarvel) throws Exception {
		String hash = "1" + "50eb637a6ea01251e6668ca47fa43223e8bb8451" + "4cb04fbfb37e630898dedc6a1c836c20";
		StringBuilder response = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = hash.getBytes();
			byte[] digest = md.digest(bytes);
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b));
			}
			//String urlMarvel = "http://gateway.marvel.com/v1/public/characters";
			urlMarvel = urlMarvel + "?ts=" + 1 + "&apikey="
					+ "4cb04fbfb37e630898dedc6a1c836c20"+ "&hash=" + sb.toString();

			URL url = new URL(urlMarvel);

			//consume servicio
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 	response.toString();
	}

}
