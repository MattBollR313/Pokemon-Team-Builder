package matt.bollinger.dev.pokemonapi.documents;

import lombok.Data;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpriteOtherList {
    
    private DreamWorldSprite dream_world;
    private HomeSprite home;
    @SerializedName("official-artwork")
    private OfficialArtworkSprite official_artwork;

}
