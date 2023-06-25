package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvolutionChainInfo {
    
    private Object baby_trigger_item;
    private ChainInfo chain;
    private Integer id;

}
