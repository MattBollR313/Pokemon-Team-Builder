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

import matt.bollinger.dev.pokemonapi.services.PokedexService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/pokedex")
public class PokedexController {
    
    @Autowired
    private PokedexService pokedexService;

    @GetMapping
    public ResponseEntity<List<String>> getPokedex() {
        try {
            if (pokedexService.allPokedex().isEmpty())
                return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
            else
                return new ResponseEntity<List<String>>(pokedexService.allPokedex(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
       
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getPokedex(@PathVariable String id) {
        return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
