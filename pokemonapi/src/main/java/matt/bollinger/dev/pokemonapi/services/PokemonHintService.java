package matt.bollinger.dev.pokemonapi.services;

import java.util.*;

import org.springframework.stereotype.Service;

import matt.bollinger.dev.pokemonapi.services.hints.*;

@Service
public class PokemonHintService {
    
    public List<String> getHints(List<String> pokemon1, List<String> pokemon2, List<String> pokemon3,
            List<String> pokemon4, List<String> pokemon5, List<String> pokemon6) {
        String notCompleteHint = "";
        if (pokemon1.get(0).equals("") || pokemon2.get(0).equals("") || pokemon3.get(0).equals("")
            || pokemon4.get(0).equals("") || pokemon5.get(0).equals("") || pokemon6.get(0).equals(""))
            notCompleteHint = "Six Pokemon have not been added to your team. Consider filling out the remaining team";
        List<String> types = getTypes(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6);
        Map<String, Integer> typeHints = TypeHints.getTypeHints(types);
        List<List<String>> evolutionStatuses = getEvolutionStatuses(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6);
        Map<String, Integer> evolutionStatusHints = EvolutionStatusHints.getEvolutionStatusHints(evolutionStatuses);
        Map<String, Integer> allWeightedHints = new HashMap<>();
        allWeightedHints.putAll(typeHints);
        allWeightedHints.putAll(evolutionStatusHints);
        List<String> finalHints = getFinalHints(allWeightedHints);
        if (!notCompleteHint.equals(""))
            finalHints.add(notCompleteHint);
        return finalHints;
    }

    public List<String> getTypes(List<String> pokemon1, List<String> pokemon2, List<String> pokemon3,
            List<String> pokemon4, List<String> pokemon5, List<String> pokemon6) {
        List<String> types = new ArrayList<>();
        if (!pokemon1.get(0).equals("")) {
            types.add(pokemon1.get(7));
            if (!pokemon1.get(8).equals(""))
                types.add(pokemon1.get(8));
        }
        if (!pokemon2.get(0).equals("")) {
            types.add(pokemon2.get(7));
            if (!pokemon2.get(8).equals(""))
                types.add(pokemon2.get(8));
        }
        if (!pokemon3.get(0).equals("")) {
            types.add(pokemon3.get(7));
            if (!pokemon3.get(8).equals(""))
                types.add(pokemon3.get(8));
        }
        if (!pokemon4.get(0).equals("")) {
            types.add(pokemon4.get(7));
            if (!pokemon4.get(8).equals(""))
                types.add(pokemon4.get(8));
        }
        if (!pokemon5.get(0).equals("")) {
            types.add(pokemon5.get(7));
            if (!pokemon5.get(8).equals(""))
                types.add(pokemon5.get(8));
        }
        if (!pokemon6.get(0).equals("")) {
            types.add(pokemon6.get(7));
            if (!pokemon6.get(8).equals(""))
                types.add(pokemon6.get(8));
        }
        return types;
    }

    public List<List<String>> getEvolutionStatuses(List<String> pokemon1, List<String> pokemon2, List<String> pokemon3,
            List<String> pokemon4, List<String> pokemon5, List<String> pokemon6) {
        List<List<String>> evolutionStatuses = new ArrayList<>();
        if (!pokemon1.get(0).equals(""))
            evolutionStatuses.add(Arrays.asList(pokemon1.get(0), pokemon1.get(9)));
        if (!pokemon2.get(0).equals(""))
            evolutionStatuses.add(Arrays.asList(pokemon2.get(0), pokemon2.get(9)));
        if (!pokemon3.get(0).equals(""))
            evolutionStatuses.add(Arrays.asList(pokemon3.get(0), pokemon3.get(9)));
        if (!pokemon4.get(0).equals(""))
            evolutionStatuses.add(Arrays.asList(pokemon4.get(0), pokemon4.get(9)));
        if (!pokemon5.get(0).equals(""))
            evolutionStatuses.add(Arrays.asList(pokemon5.get(0), pokemon5.get(9)));
        if (!pokemon6.get(0).equals(""))
            evolutionStatuses.add(Arrays.asList(pokemon6.get(0), pokemon6.get(9)));
        return evolutionStatuses;
    }

    private List<String> getFinalHints(Map<String, Integer> allHints) {
        List<Map.Entry<String, Integer> > hintList = new ArrayList<Map.Entry<String, Integer> >(allHints.entrySet());
 
        Collections.sort(hintList, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            }
        });
         
        /*Map<String, Integer> temp = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : hintList) {
            temp.put(aa.getKey(), aa.getValue());
        }*/

        List<String> finalHints = new ArrayList<>();
        if (hintList.size() <= 5) {
            for (int i = 0; i < hintList.size(); i++) {
                finalHints.add(hintList.get(i).getKey());
            }
        } else {
            for (int i = 0; i < 5; i++) {
                finalHints.add(hintList.get(i).getKey());
            }
        }

        return finalHints;
    }

}
