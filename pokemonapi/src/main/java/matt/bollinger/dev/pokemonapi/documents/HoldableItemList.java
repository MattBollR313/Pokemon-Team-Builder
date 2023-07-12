package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoldableItemList {
    
    private List<Object> descriptions;
    private Integer id;
    private List<SimpleEntry> items;
    private String name;
    private List<Object> names;

}
