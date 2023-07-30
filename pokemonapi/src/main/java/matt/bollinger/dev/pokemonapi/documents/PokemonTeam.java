package matt.bollinger.dev.pokemonapi.documents;

import java.util.ArrayList;
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
    private String updateDate;
    private String teamName;
    private String chosenGame;
    private List<String> pokemon1;
    private List<String> pokemon2;
    private List<String> pokemon3;
    private List<String> pokemon4;
    private List<String> pokemon5;
    private List<String> pokemon6;

    public PokemonTeam(String updateDate, String teamName, String chosenGame, List<String> pokemon1, List<String> pokemon2,
            List<String> pokemon3, List<String> pokemon4, List<String> pokemon5, List<String> pokemon6) {
        this.updateDate = updateDate;
        this.teamName = teamName;
        this.chosenGame = chosenGame;
        this.pokemon1 = new ArrayList<>(pokemon1);
        this.pokemon2 = new ArrayList<>(pokemon2);
        this.pokemon3 = new ArrayList<>(pokemon3);
        this.pokemon4 = new ArrayList<>(pokemon4);
        this.pokemon5 = new ArrayList<>(pokemon5);
        this.pokemon6 = new ArrayList<>(pokemon6);
    }

}
