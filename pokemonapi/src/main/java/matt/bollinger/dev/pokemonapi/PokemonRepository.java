package matt.bollinger.dev.pokemonapi;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import matt.bollinger.dev.pokemonapi.documents.PokemonTeam;

@Repository
public interface PokemonRepository extends MongoRepository<PokemonTeam, ObjectId> {
    
}
