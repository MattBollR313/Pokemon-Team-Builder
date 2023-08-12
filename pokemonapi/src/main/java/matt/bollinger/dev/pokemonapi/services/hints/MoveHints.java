package matt.bollinger.dev.pokemonapi.services.hints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MoveHints {
    
    public static Map<String, Integer> getMoveSetHints(List<List<String>> moveSetDetails) {
        Map<String, Integer> moveSetHints = new HashMap<>();
        String higherStat;
        List<String> pokemonMoveStats = new ArrayList<>();
        List<String> pokemonMoveTypes = new ArrayList<>();

        for (List<String> singlePokemon: moveSetDetails) {
            if (Integer.parseInt(singlePokemon.get(1)) > Integer.parseInt(singlePokemon.get(2)))
                higherStat = "Physical";
            else if (Integer.parseInt(singlePokemon.get(1)) < Integer.parseInt(singlePokemon.get(2)))
                higherStat = "Special";
            else
                higherStat = "";
            
            if (!higherStat.equals("")) {
                for (int i = 0; i < 4; i++) {
                    if (!singlePokemon.get(3 * i + 5).equals("")) {
                        if (!singlePokemon.get(3 * i + 7).equals("Status") && !singlePokemon.get(3 * i + 7).equals(higherStat)) {
                            pokemonMoveStats.add(singlePokemon.get(0));
                            break;
                        }
                    }
                }
            }
            

            int differentTypeCount = 0;
            boolean hasTwoTypes = true;
            if (singlePokemon.get(4).equals(""))
                hasTwoTypes = false;
            for (int i = 0; i < 4; i++) {
                if (!singlePokemon.get(3 * i + 5).equals("")) {
                    if (hasTwoTypes) {
                        if (!singlePokemon.get(3 * i + 6).equals(singlePokemon.get(3)) && !singlePokemon.get(3 * i + 6).equals(singlePokemon.get(4)))
                            differentTypeCount++;
                    } else {
                        if (!singlePokemon.get(3 * i + 6).equals(singlePokemon.get(3)))
                            differentTypeCount++;
                    }
                }
            }

            if (differentTypeCount >= 3)
                pokemonMoveTypes.add(singlePokemon.get(0));
        }

        if (pokemonMoveStats.size() == 1)
            moveSetHints.put(pokemonMoveStats.get(0) + " has at least one attack move which does not match its higher base stat." + 
            " Consider picking a different move or moves", 2);
        else if (pokemonMoveStats.size() > 1) {
            String hint = pokemonMoveStats.toString().substring(1, pokemonMoveStats.toString().length() - 1);
            moveSetHints.put(hint + " have at least one attack move which does not match their higher base stat." + 
            " Consider picking different moves", 2);
        }
        
        if (pokemonMoveTypes.size() == 1)
            moveSetHints.put(pokemonMoveTypes.get(0) + " has at least three moves which do not match one of the Pokemon's types. Consider picking different moves"
            , 3);
        else if (pokemonMoveTypes.size() > 1) {
            String hint = pokemonMoveTypes.toString().substring(1, pokemonMoveTypes.toString().length() - 1);
            moveSetHints.put(hint + " have at least three moves which do not match one of the Pokemons' types. Consider picking different moves"
            , 3);
        }

        return moveSetHints;
    }

}
