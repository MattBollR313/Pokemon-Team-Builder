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

import matt.bollinger.dev.pokemonapi.services.SamplePokemonService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/samplepokemon")
public class SamplePokemonController {
    
    @Autowired
    private SamplePokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<String>> getSamplePokemon() {
        return new ResponseEntity<List<String>>(pokemonService.sixPokemon(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getSamplePokemonWithId(@PathVariable String id) {
        return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.BAD_REQUEST);
    }
}
