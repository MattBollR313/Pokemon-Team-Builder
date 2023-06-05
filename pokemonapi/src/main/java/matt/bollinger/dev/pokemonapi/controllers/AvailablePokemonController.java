package matt.bollinger.dev.pokemonapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matt.bollinger.dev.pokemonapi.services.AvailablePokemonService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/availablepokemon")
public class AvailablePokemonController {
    
    @Autowired
    private AvailablePokemonService pokemonService;

    @GetMapping("/{pokemonGame}")
    public ResponseEntity<List<String>> getSamplePokemon(@PathVariable String pokemonGame) {
        return new ResponseEntity<List<String>>(pokemonService.availablePokemon(pokemonGame), HttpStatus.OK);
    }
}
