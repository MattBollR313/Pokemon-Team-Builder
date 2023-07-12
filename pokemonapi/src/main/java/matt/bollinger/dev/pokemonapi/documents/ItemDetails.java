package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetails {
    
    private List<Object> attributes;
    private Object baby_trigger_for;
    private Object category;
    private Integer cost;
    private List<EffectEntry> effect_entries;
    private List<Object> flavor_text_entries;
    private Object fling_effect;
    private Integer fling_power;
    private List<Object> game_indices;
    private List<Object> held_by_pokemon;
    private Integer id;
    private List<Object> machines;
    private String name;
    private List<Object> names;
    private Object sprites;

}
