package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveListDetails {
    
    private Integer accuracy; // Important
    private Object contest_combos;
    private Object contest_effect;
    private SimpleEntry contest_type;
    private SimpleEntry damage_class;
    private Integer effect_chance;
    private List<Object> effect_changes;
    private List<EffectEntry> effect_entries; // Important
    private List<Object> flavor_text_entries;
    private SimpleEntry generation;
    private Integer id;
    private List<SimpleEntry> learned_by_pokemon;
    private List<Object> machines;
    private Object meta;
    private String name;
    private List<Object> names;
    private List<Object> past_values; // Important
    private Integer power; // Important
    private Integer pp; // Important
    private Integer priority;
    private List<Object> stat_changes;
    private Object super_contest_effect;
    private SimpleEntry target;
    private SimpleEntry type; // Important

}
