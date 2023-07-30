package matt.bollinger.dev.pokemonapi.services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matt.bollinger.dev.pokemonapi.PokemonRepository;
import matt.bollinger.dev.pokemonapi.documents.PokemonTeam;

@Service
public class PokemonSaveService {

    @Autowired
    private PokemonRepository pokemonRepository;
    
    public List<PokemonTeam> allPokemonTeams() {
        return pokemonRepository.findAll();
    }

    public PokemonTeam singlePokemonTeam(String teamName) throws Exception {
        Optional<PokemonTeam> team = pokemonRepository.findTeamByTeamName(teamName);
        if (!team.isPresent())
            throw new Exception("Cannot find team with given name");
        return team.get();
    }

    public Boolean savePokemonTeam(String teamName, String chosenGame, List<String> pokemon1, List<String> pokemon2, 
            List<String> pokemon3, List<String> pokemon4, List<String> pokemon5, List<String> pokemon6) {
        String time = LocalDate.now().toString();
        PokemonTeam team;
        if (!pokemonRepository.findTeamByTeamName(teamName).isPresent()) {
            team = new PokemonTeam(time, teamName, chosenGame, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6);
            pokemonRepository.insert(team);
        } else {
            PokemonTeam existingTeam = pokemonRepository.findTeamByTeamName(teamName).get();
            team = new PokemonTeam(existingTeam.getId(), time, teamName, chosenGame, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6);
            pokemonRepository.save(team);
        }
            
        return true;
    }
}
