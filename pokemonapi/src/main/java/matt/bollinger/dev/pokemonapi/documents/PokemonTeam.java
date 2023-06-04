package matt.bollinger.dev.pokemonapi.documents;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pokemon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonTeam {
    
    @Id
    private ObjectId id;
    private String teamId;
    private String teamName;
    private String updateDate;
    private List<String> pokemon;

}
