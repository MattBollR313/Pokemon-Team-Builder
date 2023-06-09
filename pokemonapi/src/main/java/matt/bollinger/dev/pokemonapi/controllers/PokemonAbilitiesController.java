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

import matt.bollinger.dev.pokemonapi.services.PokemonAbilitiesService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/abilities")
public class PokemonAbilitiesController {
    
    @Autowired
    private PokemonAbilitiesService abilitiesService;

    @GetMapping
    public ResponseEntity<List<String>> getPokemonAbilitiesNoArgs() {
        return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{pokemon}")
    public ResponseEntity<List<List<String>>> getPokemonAbilities(@PathVariable String pokemon) {
        try {
            return new ResponseEntity<List<List<String>>>(abilitiesService.getPokemonAbilities(pokemon), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<List<String>>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
    
}
