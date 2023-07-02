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

import matt.bollinger.dev.pokemonapi.services.PokemonTypesService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/types")
public class PokemonTypesController {
    
    @Autowired
    private PokemonTypesService typesService;

    @GetMapping
    public ResponseEntity<List<String>> getPokemonTypesNoArgs() {
        return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{pokemon}")
    public ResponseEntity<List<String>> getPokemonTypes(@PathVariable String pokemon) {
        try {
            return new ResponseEntity<List<String>>(typesService.getPokemonTypes(pokemon), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{pokemonGame}/{pokemon}")
    public ResponseEntity<List<String>> getPokemonTypesTwoArgs(@PathVariable String pokemonGame, @PathVariable String pokemon) {
        try {
            return new ResponseEntity<List<String>>(typesService.getPokemonTypes(pokemon, pokemonGame), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
}
