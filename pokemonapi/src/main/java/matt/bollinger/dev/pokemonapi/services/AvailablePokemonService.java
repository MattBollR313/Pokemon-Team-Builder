package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;
import matt.bollinger.dev.pokemonapi.common.NameConverter;

@Service
public class AvailablePokemonService {
    
    private static final String url = "https://pokeapi.co/api/v2/pokedex/";

    @Autowired
    private WebClient builder;
    
    public List<String> availablePokemon(String pokemonGame) throws Exception {
        String apiUrl = url + pokemonGame;
		
        Pokedex pokemonExample = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(Pokedex.class).block();

        List<PokemonEntry> pokemonEntries = pokemonExample.getPokemon_entries();
        List<String> pokemonNames = new ArrayList<>();
        for (PokemonEntry entry: pokemonEntries) {
            String entryName = entry.getPokemon_species().getName();
            pokemonNames.add(NameConverter.convertName(entryName));
        }

        return pokemonNames;
    }
    
}
