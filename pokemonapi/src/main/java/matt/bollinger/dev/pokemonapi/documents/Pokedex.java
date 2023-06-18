package matt.bollinger.dev.pokemonapi.documents;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokedex {
    
    private List<PokedexDescription> descriptions;
    private Integer id;
    private Boolean is_main_series;
    private String name;
    private List<PokedexName> names;
    private List<PokemonEntry> pokemon_entries;
    private SimpleEntry region;
    private List<SimpleEntry> version_groups;

}