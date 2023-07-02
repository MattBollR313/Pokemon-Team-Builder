package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDetailList {
    
    private Integer rarity;
    private SimpleEntry version;

}
