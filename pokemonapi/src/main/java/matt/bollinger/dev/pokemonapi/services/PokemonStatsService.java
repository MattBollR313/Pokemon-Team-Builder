package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class PokemonStatsService {
    
    private static final String url = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    private WebClient builder;
    
    public List<Integer> getPokemonStats(String pokemonName) throws Exception {
        String apiUrl = url + pokemonName;
		
        PokemonInfo pokemonExample = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokemonInfo.class).block();

        List<StatsList> pokemonStats = pokemonExample.getStats();
        List<Integer> statValues = new ArrayList<>();
        for (StatsList entry: pokemonStats) {
            statValues.add(entry.getBase_stat());
        }

        return statValues;
    }
    
}