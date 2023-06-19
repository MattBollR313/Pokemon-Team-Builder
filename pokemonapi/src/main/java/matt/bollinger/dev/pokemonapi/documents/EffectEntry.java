package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EffectEntry {
    
    private String effect;
    private SimpleEntry language;
    private String short_effect;

}