package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficialArtworkSprite {

    private String front_default;
    private String front_shiny;

}
