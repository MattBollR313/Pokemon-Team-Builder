package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityInfo {
    
    private Object effect_changes;
    private List<EffectEntry> effect_entries;
    private Object flavor_text_entries;
    private SimpleEntry generation;
    private Integer id;
    private Boolean is_main_series;
    private String name;
    private List<PokedexName> names;
    private Object pokemon;

}