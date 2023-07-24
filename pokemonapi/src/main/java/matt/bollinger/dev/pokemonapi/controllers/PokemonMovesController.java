package matt.bollinger.dev.pokemonapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matt.bollinger.dev.pokemonapi.services.PokemonMovesService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/moves")
public class PokemonMovesController {
    
    @Autowired
    private PokemonMovesService movesService;

    @GetMapping
    public ResponseEntity<List<String>> getPokemonTypesNoArgs() {
        return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{pokemon}")
    public ResponseEntity<List<List<String>>> getPokemonMoves(@PathVariable String pokemon) {
        try {
            return new ResponseEntity<List<List<String>>>(movesService.getPokemonMoves(pokemon), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<List<String>>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{move}/{pokemonGame}")
    public ResponseEntity<List<String>> getPokemonMovesTwoArgs(@PathVariable String move, @PathVariable String pokemonGame) {
        try {
            return new ResponseEntity<List<String>>(movesService.getPokemonMoveInfo(move, pokemonGame), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
}
