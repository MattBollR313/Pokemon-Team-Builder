package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class PokemonTypesService {
    
    private static final String url = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    private WebClient builder;
    
    public List<String> getPokemonTypes(String pokemonName) throws Exception {
        String apiUrl = url + pokemonName;
		
        PokemonInfo pokemon = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokemonInfo.class).block();

        List<TypeList> pokemonTypes = pokemon.getTypes();
        return getTypeNames(pokemonTypes);
    }

    public List<String> getPokemonTypes(String pokemonName, String pokemonGame) throws Exception {
        String apiUrl = url + pokemonName;
		
        PokemonInfo pokemon = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokemonInfo.class).block();

        List<TypeList> pokemonTypes = pokemon.getTypes();
        List<PastTypeList> pastTypes = pokemon.getPast_types();

        if (pastTypes != null && pastTypes.size() != 0) {
            Integer pokemonGameGeneration = getGeneration(pokemonGame);
            if (pokemonGameGeneration.equals(null))
                throw new Exception();
            PastTypeList pastType = pastTypes.get(pastTypes.size() - 1);
            String generationUrl = pastType.getGeneration().getUrl();
            if (Character.getNumericValue(generationUrl.charAt(generationUrl.length() - 2)) >= pokemonGameGeneration) {
                pokemonTypes = pastType.getTypes();
                return getTypeNames(pokemonTypes);
            }
        }

        return getTypeNames(pokemonTypes);
    }

    private Integer getGeneration(String pokemonGame) {
        switch (pokemonGame) {
            case "kanto":
            case "hoenn":
                return 3;
            case "original-johto":
                return 2;
            case "original-sinnoh":
            case "extended-sinnoh":
            case "updated-johto":
                return 4;
            case "original-unova":
            case "updated-unova":
                return 5;
            case "kalos-central":
            case "updated-hoenn":
                return 6;
            case "original-alola":
            case "updated-alola":
                return 7;
            case "letsgo-kanto":
            case "galar":
                return 8;
            case "hisui":
            case "paldea":
                return 9;
            default:
                return null;
        }
    }

    private List<String> getTypeNames(List<TypeList> pokemonTypes) {
        List<String> typeNames = new ArrayList<>();

        for (TypeList entry: pokemonTypes) {
            String typeEntry = entry.getType().getName();
            typeNames.add(typeEntry.substring(0, 1).toUpperCase() + typeEntry.substring(1));
        }

        return typeNames;
    }
    
}