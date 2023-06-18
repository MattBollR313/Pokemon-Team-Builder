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

import matt.bollinger.dev.pokemonapi.services.PokemonStatsService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/stats")
public class PokemonStatsController {
    
    @Autowired
    private PokemonStatsService statsService;

    @GetMapping
    public ResponseEntity<List<Integer>> getPokemonStatsNoArgs() {
        return new ResponseEntity<List<Integer>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{pokemon}")
    public ResponseEntity<List<Integer>> getPokemonStats(@PathVariable String pokemon) {
        try {
            return new ResponseEntity<List<Integer>>(statsService.getPokemonStats(pokemon), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Integer>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
}
