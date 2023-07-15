package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovePastValue {
    
    private Integer accuracy; // Important
    private Integer effect_chance;
    private List<EffectEntry> effect_entries; // Important
    private Integer power; // Important
    private Integer pp; // Important
    private SimpleEntry type; // Important
    private SimpleEntry version_group;

}
