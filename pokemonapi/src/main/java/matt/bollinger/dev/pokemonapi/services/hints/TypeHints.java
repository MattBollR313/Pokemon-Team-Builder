package matt.bollinger.dev.pokemonapi.services.hints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import org.springframework.stereotype.Service;

@Service
public class TypeHints {

    private static final List<String> typeNames = List.of("Normal", "Fire", "Water", "Grass", "Electric", "Ice", "Fighting", 
        "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dark", "Dragon", "Steel", "Fairy");
    
    public static Map<String, Integer> getTypeHints(List<String> types) {
        List<String> duplicatedTypes = new ArrayList<>();
        for (String typeName: typeNames) {
            if (Collections.frequency(types, typeName) > 1)
                duplicatedTypes.add(typeName);
        }
        Map<String, Integer> typeHints = new HashMap<>();
        if (!duplicatedTypes.isEmpty()) {
            String hint = duplicatedTypes.toString().substring(1, duplicatedTypes.toString().length() - 1);
            if (duplicatedTypes.size() > 1)
                typeHints.put(hint + " are duplicately covered by your team. Consider changing your team lineup to cover a more diverse set of types", 3);
            else
                typeHints.put(hint + " is duplicately covered by your team. Consider changing your team lineup to cover a more diverse set of types", 3);
        }
        return typeHints;
    }

}
