package matt.bollinger.dev.pokemonapi.documents;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonSpeciesInfo {
    
    private Integer base_happiness;
    private Integer capture_rate;
    private SimpleEntry color;
    private List<SimpleEntry> egg_groups;
    private EvolutionChainUrl evolution_chain;
    private SimpleEntry evolves_from_species;
    private List<Object> flavor_text_entries;
    private List<Object> form_descriptions;
    private Boolean forms_switchable;
    private Integer gender_rate;
    private List<Object> genera;
    private SimpleEntry generation;
    private SimpleEntry growth_rate;
    private Object habitat;
    private Boolean has_gender_differences;
    private Integer hatch_counter;
    private Integer id;
    private Boolean is_baby;
    private Boolean is_legendary;
    private Boolean is_mythical;
    private String name;
    private List<Object> names;
    private Integer order;
    private List<Object> pal_park_encounters;
    private List<Object> pokedex_numbers;
    private SimpleEntry shape;
    private List<Object> varieties;

}
