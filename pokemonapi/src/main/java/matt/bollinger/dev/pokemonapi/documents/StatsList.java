package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsList {
    
    private Integer base_stat;
    private Integer effort;
    private SimpleEntry stat;

}
