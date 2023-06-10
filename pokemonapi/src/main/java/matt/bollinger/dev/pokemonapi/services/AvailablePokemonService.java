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

    private String convertGameName(String pokemonGame) {
        switch (pokemonGame) {
            case "Kanto (Gen 1/3)":
                return "kanto";
            case "Johto (Gen 2)":
                return "original-johto";
            case "Hoenn (Gen 3)":
                return "hoenn";
            case "Sinnoh (DP Gen 4)":
                return "original-sinnoh";
            case "Sinnoh (Plat Gen 4)":
                return "extended-sinnoh";
            case "Johto (Gen 4)":
                return "updated-johto";
            case "Unova (BW Gen 5)":
                return "original-unova";
            case "Unova (B2W2 Gen 5)":
                return "updated-unova";
            case "Kalos (Gen 6)":
                return "kalos-central";
            case "Hoenn (Gen 6)":
                return "updated-hoenn"; 
            case "Alola (SM Gen 7)":
                return "original-alola";
            case "Alola (USUM Gen 7)":
                return "updated-alola";
            case "Kanto (Let's Go)":
                return "letsgo-kanto";
            case "Galar (Gen 8)":
                return "galar";
            case "Hisui (Gen 9)":
                return "hisui";
            case "Paldea (Gen 9)":
                return "paldea";
            default:
                return "";
        }
    }
}
