package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class PokedexService {
    
    private static final String url = "https://pokeapi.co/api/v2/pokedex/?limit=40";

    @Autowired
    private WebClient builder;
    
    public List<String> allPokedex() throws Exception {
		
        PokedexList pokedexList = builder.get().uri(url).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokedexList.class).block();

        if (pokedexList.getResults().size() < 32)
            return new ArrayList<String>();
        
        List<PokedexEntry> pokedexEntries = getOnlyAppropriateEntries(pokedexList);
        List<String> pokedexNames = getCorrectNames(pokedexEntries);

        return pokedexNames;
    }

    private List<PokedexEntry> getOnlyAppropriateEntries(PokedexList pokedexList) {
        List<PokedexEntry> pokedexEntries = pokedexList.getResults();
        pokedexEntries.remove(0);
        pokedexEntries.remove(8);
        pokedexEntries.remove(9);
        pokedexEntries.remove(9);
        pokedexEntries.remove(11);
        pokedexEntries.remove(11);
        pokedexEntries.remove(11);
        pokedexEntries.remove(11);
        pokedexEntries.remove(12);
        pokedexEntries.remove(12);
        pokedexEntries.remove(12);
        pokedexEntries.remove(12);
        pokedexEntries.remove(14);
        pokedexEntries.remove(14);
        pokedexEntries.remove(16);
        pokedexEntries.remove(16);
        return pokedexEntries;
    }

    private List<String> getCorrectNames(List<PokedexEntry> pokedexEntries) {
        List<String> pokedexNames = new ArrayList<>();
        for (PokedexEntry entry: pokedexEntries) {
            pokedexNames.add(entry.getName());
        }
        pokedexNames.set(0, "Kanto (Gen 1/3)");
        pokedexNames.set(1, "Johto (Gen 2)");
        pokedexNames.set(2, "Hoenn (Gen 3)");
        pokedexNames.set(3, "Sinnoh (DP Gen 4)");
        pokedexNames.set(4, "Sinnoh (Plat Gen 4)");
        pokedexNames.set(5, "Johto (Gen 4)");
        pokedexNames.set(6, "Unova (BW Gen 5)");
        pokedexNames.set(7, "Unova (B2W2 Gen 5)");
        pokedexNames.set(8, "Kalos (Gen 6)");
        pokedexNames.set(9, "Hoenn (Gen 6)");
        pokedexNames.set(10, "Alola (SM Gen 7)");
        pokedexNames.set(11, "Alola (USUM Gen 7)");
        pokedexNames.set(12, "Kanto (Let's Go)");
        pokedexNames.set(13, "Galar (Gen 8)");
        pokedexNames.set(14, "Hisui (Gen 9)");
        pokedexNames.set(15, "Paldea (Gen 9)");
        return pokedexNames;
    }
}
