package matt.bollinger.dev.pokemonapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;
    
    public List<Pokemon> allPokemon() {
        return pokemonRepository.findAll();
    }
}
