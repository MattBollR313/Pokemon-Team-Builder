package matt.bollinger.dev.pokemonapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/pokemonteam")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAllPokemonTeams() {
        return new ResponseEntity<List<Pokemon>>(pokemonService.allPokemon(), HttpStatus.OK);
    }
}