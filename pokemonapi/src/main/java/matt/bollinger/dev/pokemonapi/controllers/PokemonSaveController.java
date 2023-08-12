package matt.bollinger.dev.pokemonapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matt.bollinger.dev.pokemonapi.documents.PokemonTeam;
import matt.bollinger.dev.pokemonapi.services.PokemonSaveService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/pokemonteam")
public class PokemonSaveController {

    @Autowired
    private PokemonSaveService pokemonService;

    @GetMapping("/allteams")
    public ResponseEntity<List<String>> getAllPokemonTeams() {
        try {
            return new ResponseEntity<List<String>>(pokemonService.allPokemonTeams(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<String>>(List.of(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTeam")
    public ResponseEntity<PokemonTeam> getSinglePokemonTeam(@RequestHeader("team-name") String teamName) {
        try {
            return new ResponseEntity<PokemonTeam>(pokemonService.singlePokemonTeam(teamName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<PokemonTeam>(new PokemonTeam(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> postPokemonTeam(@RequestHeader("team-name") String teamName, 
        @RequestHeader("chosen-game") String chosenGame, 
        @RequestHeader("pokemon-1") List<String> pokemon1, 
        @RequestHeader("pokemon-2") List<String> pokemon2, 
        @RequestHeader("pokemon-3") List<String> pokemon3, 
        @RequestHeader("pokemon-4") List<String> pokemon4, 
        @RequestHeader("pokemon-5") List<String> pokemon5, 
        @RequestHeader("pokemon-6") List<String> pokemon6) {
        try {
            return new ResponseEntity<Boolean>(pokemonService.savePokemonTeam(teamName, chosenGame, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }
}