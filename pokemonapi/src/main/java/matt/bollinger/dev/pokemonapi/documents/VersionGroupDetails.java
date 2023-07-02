package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionGroupDetails {
    
    private Integer level_learned_at;
    private SimpleEntry move_learn_method;
    private SimpleEntry version_group;

}
