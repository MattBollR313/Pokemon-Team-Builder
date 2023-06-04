package matt.bollinger.dev.pokemonapi.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class SamplePokemonService {
    
    private static final String url = "https://pokeapi.co/api/v2/pokedex/kanto";

    @Autowired
    private WebClient builder;
    
    public List<String> sixPokemon() {
		
        Pokedex pokemonExample = builder.get().uri(url).retrieve().bodyToMono(Pokedex.class).block();

        List<PokemonEntry> pokemonEntries = pokemonExample.getPokemon_entries();
        String[] firstSixPokemon = new String[6];
        for (int i = 0; i < 6; i++) {
            firstSixPokemon[i] = pokemonEntries.get(i).getPokemon_species().getName();
            firstSixPokemon[i] = firstSixPokemon[i].substring(0, 1).toUpperCase() + firstSixPokemon[i].substring(1);
        }
        List<String> listFirstSixPokemon = Arrays.asList(firstSixPokemon);

        return listFirstSixPokemon;
    }
}
