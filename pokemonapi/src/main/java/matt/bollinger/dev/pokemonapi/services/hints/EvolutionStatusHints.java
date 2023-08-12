package matt.bollinger.dev.pokemonapi.services.hints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class EvolutionStatusHints {
    
    public static Map<String, Integer> getEvolutionStatusHints(List<List<String>> evolutionStatuses) {
        List<String> notFullyEvolvedPokemon = new ArrayList<>();
        for (List<String> evolutionStatus: evolutionStatuses) {
            if (evolutionStatus.get(1).equals("Not Final Stage"))
                notFullyEvolvedPokemon.add(evolutionStatus.get(0));
        }
        Map<String, Integer> evolutionStatusHints = new HashMap<>();
        if (!notFullyEvolvedPokemon.isEmpty()) {
            String hint = notFullyEvolvedPokemon.toString().substring(1, notFullyEvolvedPokemon.toString().length() - 1);
            if (notFullyEvolvedPokemon.size() > 1)
                evolutionStatusHints.put(hint + " are not fully evolved. Consider swapping the Pokemon for their final evolutions", 5);
            else
                evolutionStatusHints.put(hint + " is not fully evolved. Consider swapping the Pokemon for its final evolution", 4);
        }
        return evolutionStatusHints;
    }

}
