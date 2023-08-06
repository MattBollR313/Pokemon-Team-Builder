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
        List<List<String>> baseStats = getBaseStats(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6);
        Map<String, Integer> baseStatsHints = BaseStatsHints.getBaseStatsHints(baseStats);
        Map<String, Integer> allWeightedHints = new HashMap<>();
        allWeightedHints.putAll(typeHints);
        allWeightedHints.putAll(evolutionStatusHints);
        allWeightedHints.putAll(baseStatsHints);
        List<String> finalHints = getFinalHints(allWeightedHints);
        if (!notCompleteHint.equals(""))
            finalHints.add(notCompleteHint);
        return finalHints;
    }

    private List<String> getTypes(List<String> pokemon1, List<String> pokemon2, List<String> pokemon3,
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

    private List<List<String>> getEvolutionStatuses(List<String> pokemon1, List<String> pokemon2, List<String> pokemon3,
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

    private List<List<String>> getBaseStats(List<String> pokemon1, List<String> pokemon2, List<String> pokemon3,
            List<String> pokemon4, List<String> pokemon5, List<String> pokemon6) {
        List<List<String>> baseStats = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            baseStats.add(new ArrayList<>());
        }

        if (!pokemon1.get(0).equals("")) {
            baseStats.get(0).add(pokemon1.get(1));
            baseStats.get(1).add(pokemon1.get(2));
            baseStats.get(2).add(pokemon1.get(3));
            baseStats.get(3).add(pokemon1.get(4));
            baseStats.get(4).add(pokemon1.get(5));
            baseStats.get(5).add(pokemon1.get(6));
        }
        if (!pokemon2.get(0).equals("")) {
            baseStats.get(0).add(pokemon2.get(1));
            baseStats.get(1).add(pokemon2.get(2));
            baseStats.get(2).add(pokemon2.get(3));
            baseStats.get(3).add(pokemon2.get(4));
            baseStats.get(4).add(pokemon2.get(5));
            baseStats.get(5).add(pokemon2.get(6));
        }
        if (!pokemon3.get(0).equals("")) {
            baseStats.get(0).add(pokemon3.get(1));
            baseStats.get(1).add(pokemon3.get(2));
            baseStats.get(2).add(pokemon3.get(3));
            baseStats.get(3).add(pokemon3.get(4));
            baseStats.get(4).add(pokemon3.get(5));
            baseStats.get(5).add(pokemon3.get(6));
        }
        if (!pokemon4.get(0).equals("")) {
            baseStats.get(0).add(pokemon4.get(1));
            baseStats.get(1).add(pokemon4.get(2));
            baseStats.get(2).add(pokemon4.get(3));
            baseStats.get(3).add(pokemon4.get(4));
            baseStats.get(4).add(pokemon4.get(5));
            baseStats.get(5).add(pokemon4.get(6));
        }
        if (!pokemon5.get(0).equals("")) {
            baseStats.get(0).add(pokemon5.get(1));
            baseStats.get(1).add(pokemon5.get(2));
            baseStats.get(2).add(pokemon5.get(3));
            baseStats.get(3).add(pokemon5.get(4));
            baseStats.get(4).add(pokemon5.get(5));
            baseStats.get(5).add(pokemon5.get(6));
        }
        if (!pokemon6.get(0).equals("")) {
            baseStats.get(0).add(pokemon6.get(1));
            baseStats.get(1).add(pokemon6.get(2));
            baseStats.get(2).add(pokemon6.get(3));
            baseStats.get(3).add(pokemon6.get(4));
            baseStats.get(4).add(pokemon6.get(5));
            baseStats.get(5).add(pokemon6.get(6));
        }

        return baseStats;
    }

    private List<String> getFinalHints(Map<String, Integer> allHints) {
        List<Map.Entry<String, Integer> > hintList = new ArrayList<Map.Entry<String, Integer> >(allHints.entrySet());
 
        Collections.sort(hintList, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            }
        });

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
