package matt.bollinger.dev.pokemonapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matt.bollinger.dev.pokemonapi.services.PokemonHintService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/hints")
public class PokemonHintController {

    @Autowired
    private PokemonHintService hintService;

    @GetMapping
    public ResponseEntity<List<String>> getSinglePokemonTeam(@RequestHeader("pokemon-1") List<String> pokemon1, 
            @RequestHeader("pokemon-2") List<String> pokemon2, 
            @RequestHeader("pokemon-3") List<String> pokemon3, 
            @RequestHeader("pokemon-4") List<String> pokemon4, 
            @RequestHeader("pokemon-5") List<String> pokemon5, 
            @RequestHeader("pokemon-6") List<String> pokemon6) {
        return new ResponseEntity<List<String>>(hintService.getHints(pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6), HttpStatus.OK);
    }

}