package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokedexList {
    
    private Integer count;
    private String next;
    private String previous;
    private List<PokedexEntry> results;
}
