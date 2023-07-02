package matt.bollinger.dev.pokemonapi.documents;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChainInfo {
    
    private List<Object> evolution_details;
    private List<ChainInfo> evolves_to;
    private Boolean is_baby;
    private SimpleEntry species;

}
