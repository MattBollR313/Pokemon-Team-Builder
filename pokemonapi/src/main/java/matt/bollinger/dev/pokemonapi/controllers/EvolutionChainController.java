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

import matt.bollinger.dev.pokemonapi.services.EvolutionChainService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/evolution")
public class EvolutionChainController {
    
    @Autowired
    private EvolutionChainService evolutionChainService;

    @GetMapping
    public ResponseEntity<List<Boolean>> getEvolutionStatusNoArgs() {
        return new ResponseEntity<List<Boolean>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{pokemon}")
    public ResponseEntity<List<Boolean>> getEvolutionStatus(@PathVariable String pokemon) {
        try {
            return new ResponseEntity<List<Boolean>>(evolutionChainService.getEvolutionStatus(pokemon), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Boolean>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
    
}
