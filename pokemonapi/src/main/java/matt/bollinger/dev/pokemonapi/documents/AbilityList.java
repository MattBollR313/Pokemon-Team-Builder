package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbilityList {
    
    private SimpleEntry ability;
    private Boolean is_hidden;
    private Integer slot;

}