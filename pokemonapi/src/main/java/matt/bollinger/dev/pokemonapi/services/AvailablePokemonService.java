package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class AvailablePokemonService {
    
    private static final String url = "https://pokeapi.co/api/v2/pokedex/";

    @Autowired
    private WebClient builder;
    
    public List<String> availablePokemon(String pokemonGame) {
        // String correctGameName = convertGameName(pokemonGame);
        String correctGameName = pokemonGame;
        String apiUrl = url + correctGameName;
		
        Pokedex pokemonExample = builder.get().uri(apiUrl).retrieve().bodyToMono(Pokedex.class).block();

        List<PokemonEntry> pokemonEntries = pokemonExample.getPokemon_entries();
        List<String> pokemonNames = new ArrayList<>();
        for (PokemonEntry entry: pokemonEntries) {
            String entryName = entry.getPokemon_species().getName();
            pokemonNames.add(entryName.substring(0, 1).toUpperCase() + entryName.substring(1));
        }

        return pokemonNames;
    }
    
}
