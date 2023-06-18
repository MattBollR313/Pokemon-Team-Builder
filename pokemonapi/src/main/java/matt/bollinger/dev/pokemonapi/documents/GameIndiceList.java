package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameIndiceList {
    
    private Integer game_index;
    private SimpleEntry version;

}
