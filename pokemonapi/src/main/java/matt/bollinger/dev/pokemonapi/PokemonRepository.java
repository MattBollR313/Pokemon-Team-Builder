package matt.bollinger.dev.pokemonapi;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import matt.bollinger.dev.pokemonapi.documents.PokemonTeam;

@Repository
public interface PokemonRepository extends MongoRepository<PokemonTeam, ObjectId> {
    
    Optional<PokemonTeam> findTeamByTeamName(String teamName);

}
