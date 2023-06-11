package matt.bollinger.dev.pokemonapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matt.bollinger.dev.pokemonapi.PokemonRepository;
import matt.bollinger.dev.pokemonapi.documents.PokemonTeam;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;
    
    public List<PokemonTeam> allPokemon() {
        return pokemonRepository.findAll();
    }
}
