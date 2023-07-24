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

import matt.bollinger.dev.pokemonapi.services.HeldItemService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/helditem")
public class HeldItemController {
    
    @Autowired
    private HeldItemService heldItemService;

    @GetMapping
    public ResponseEntity<List<List<String>>> getHeldItems() {
        try {
            return new ResponseEntity<List<List<String>>>(heldItemService.allHeldItems(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<List<String>>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
       
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<String> getHeldItemsWithArg(@PathVariable String itemName) {
        try {
            return new ResponseEntity<String>(heldItemService.getHeldItemDescription(itemName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
        }
    }
}
