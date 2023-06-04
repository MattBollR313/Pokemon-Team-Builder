package matt.bollinger.dev.pokemonapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matt.bollinger.dev.pokemonapi.services.PokedexService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/pokedex")
public class PokedexController {
    
    @Autowired
    private PokedexService pokedexService;

    @GetMapping
    public ResponseEntity<List<String>> getPokedex() {
        return new ResponseEntity<List<String>>(pokedexService.allPokedex(), HttpStatus.OK);
    }
}
