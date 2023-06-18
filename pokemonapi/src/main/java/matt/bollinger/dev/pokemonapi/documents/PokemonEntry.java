package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntry {
    
    private Integer entry_number;
    private SimpleEntry pokemon_species;

}
