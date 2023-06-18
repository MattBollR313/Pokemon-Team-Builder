package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEntry {
    
    private String name;
    private String url;

}
