package matt.bollinger.dev.pokemonapi.documents;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonInfo {
    
    private List<AbilityList> abilities;
    private Integer base_experience;
    private List<SimpleEntry> forms;
    private List<GameIndiceList> game_indices;
    private Integer height;
    private List<HeldItemList> held_items;
    private Integer id;
    private Boolean is_default;
    private String location_area_encounters;
    private List<MoveList> moves;
    private String name;
    private Integer order;
    private List<PastTypeList> past_types;
    private SimpleEntry species;
    private Sprite sprites;
    private List<StatsList> stats;
    private List<TypeList> types;
    private Integer weight;

}
