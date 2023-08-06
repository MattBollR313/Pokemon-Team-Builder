package matt.bollinger.dev.pokemonapi.services.hints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class BaseStatsHints {
    
    public static Map<String, Integer> getBaseStatsHints(List<List<String>> baseStats) {
        Map<String, Integer> baseStatsHints = new HashMap<>();
        if (baseStats.get(0).isEmpty() || baseStats.get(1).isEmpty() || baseStats.get(2).isEmpty() || 
            baseStats.get(3).isEmpty() || baseStats.get(4).isEmpty() || baseStats.get(5).isEmpty())
            return baseStatsHints;
        Boolean hpGoal = false, attackGoal = false, defenseGoal = false, spAttackGoal = false, spDefenseGoal = false, speedGoal = false;
        for(String hp : baseStats.get(0)) {
            if (Integer.parseInt(hp) > 90) {
                hpGoal = true;
                break;
            }   
        }
        for(String attack : baseStats.get(1)) {
            if (Integer.parseInt(attack) > 90) {
                attackGoal = true;
                break;
            }   
        }
        for(String defense : baseStats.get(2)) {
            if (Integer.parseInt(defense) > 90) {
                defenseGoal = true;
                break;
            }   
        }
        for(String specialAttack : baseStats.get(3)) {
            if (Integer.parseInt(specialAttack) > 90) {
                spAttackGoal = true;
                break;
            }   
        }
        for(String specialDefense : baseStats.get(4)) {
            if (Integer.parseInt(specialDefense) > 90) {
                spDefenseGoal = true;
                break;
            }   
        }
        for(String speed : baseStats.get(5)) {
            if (Integer.parseInt(speed) > 90) {
                speedGoal = true;
                break;
            }   
        }
        List<String> hint = new ArrayList<>();
        if (!hpGoal)
            hint.add("HP");
        if (!attackGoal)
            hint.add("Attack");
        if (!defenseGoal)
            hint.add("Defense");
        if (!spAttackGoal)
            hint.add("Special Attack");
        if (!spDefenseGoal)
            hint.add("Special Defense");
        if (!speedGoal)
            hint.add("Speed");
        
        if (hint.isEmpty())
            return baseStatsHints;
        else if (hint.size() == 1) {
            baseStatsHints.put(hint.get(0) + 
                " does not have any Pokemon above the 90 baseline. Consider picking a Pokemon with a higher " + hint.get(0), 3);
        } else {
            baseStatsHints.put(hint.toString().substring(1, hint.toString().length() - 1) + 
                " do not have any Pokemon above the 90 baseline. Consider picking Pokemon(s) with higher statistics", 4);
        }
        return baseStatsHints;
    }

}
