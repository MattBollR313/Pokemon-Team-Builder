package matt.bollinger.dev.pokemonapi.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class EvolutionChainService {
    
    private static final String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    private WebClient builder;
    
    public List<Boolean> getEvolutionStatus(String pokemonName) throws Exception {
        String apiUrl = pokemonUrl + pokemonName;
        PokemonInfo pokemon = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokemonInfo.class).block();

        String speciesUrl = pokemon.getSpecies().getUrl();
        PokemonSpeciesInfo pokemonSpecies = builder.get().uri(speciesUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokemonSpeciesInfo.class).block();

        String evolutionUrl = pokemonSpecies.getEvolution_chain().getUrl();
        EvolutionChainInfo evolutionChain = builder.get().uri(evolutionUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(EvolutionChainInfo.class).block();

        ChainInfo currentChain = evolutionChain.getChain();
        List<ChainInfo> chainList = currentChain.getEvolves_to();
        if (currentChain.getSpecies().getName().equals(pokemonName) && !chainList.isEmpty())
            return Arrays.asList(false);
        
        for (ChainInfo chainEntry: chainList) {
            if (chainEntry.getSpecies().getName().equals(pokemonName) && !chainEntry.getEvolves_to().isEmpty())
                return Arrays.asList(false);
        }

        return Arrays.asList(true);
    }
    
}