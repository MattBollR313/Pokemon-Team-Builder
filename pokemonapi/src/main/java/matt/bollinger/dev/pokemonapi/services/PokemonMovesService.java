package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class PokemonMovesService {
    
    private static final String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
    private static final String moveUrl = "https://pokeapi.co/api/v2/move/";

    @Autowired
    private WebClient builder;
    
    public List<List<String>> getPokemonMoves(String pokemonName) throws Exception {
        String apiUrl = pokemonUrl + pokemonName;
		
        PokemonInfo pokemon = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokemonInfo.class).block();

        List<MoveList> pokemonMoves = pokemon.getMoves();
        return getMoveNames(pokemonMoves);
    }

    public List<String> getPokemonMoveInfo(String moveName, String pokemonGame) throws Exception {
        String apiUrl = moveUrl + moveName;
		
        MoveListDetails move = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(MoveListDetails.class).block();

        String description = move.getEffect_entries().get(0).getShort_effect();
        String type = move.getType().getName().substring(0, 1).toUpperCase() + move.getType().getName().substring(1);
        String damageClass = move.getDamage_class().getName().substring(0, 1).toUpperCase() + move.getDamage_class().getName().substring(1);
        String power = move.getPower() != null ? move.getPower().toString() : null;
        String pp = move.getPp() != null ? move.getPp().toString() : null;
        String accuracy = move.getAccuracy() != null ? move.getAccuracy().toString() : null;

        if (move.getPast_values().size() != 0) {
            MovePastValue pastVal = move.getPast_values().get(0);

            Integer chosenGen = getGeneration(pokemonGame);
            Integer oldGen = getOldGeneration(pastVal.getVersion_group().getName());

            if (chosenGen < oldGen) {
                if (pastVal.getAccuracy() != null)
                    accuracy = pastVal.getAccuracy().toString();
                if (pastVal.getEffect_entries().size() != 0)
                    description = pastVal.getEffect_entries().get(0).getShort_effect();
                if (pastVal.getPower() != null)
                    power = pastVal.getPower().toString();
                if (pastVal.getPp() != null)
                    pp = pastVal.getPp().toString();
                if (pastVal.getType() != null)
                    type = pastVal.getType().getName().substring(0, 1).toUpperCase() + pastVal.getType().getName().substring(1);
            }
        }

        if (description.contains("$")) {
            description = description.substring(0, description.indexOf("$")) + description.substring(description.indexOf("%") + 1);
        }
        List<String> moveDetails = new ArrayList<>();
        moveDetails.add(description);
        moveDetails.add(type);
        moveDetails.add(damageClass);
        moveDetails.add(power);
        moveDetails.add(pp);
        moveDetails.add(accuracy);
        return moveDetails;
    }

    private Integer getGeneration(String pokemonGame) throws Exception {
        switch (pokemonGame) {
            case "kanto":
            case "hoenn":
                return 3;
            case "original-johto":
                return 2;
            case "original-sinnoh":
            case "extended-sinnoh":
            case "updated-johto":
                return 4;
            case "original-unova":
            case "updated-unova":
                return 5;
            case "kalos-central":
            case "updated-hoenn":
                return 6;
            case "original-alola":
            case "updated-alola":
                return 7;
            case "letsgo-kanto":
            case "galar":
                return 8;
            case "hisui":
            case "paldea":
                return 9;
            default:
                throw new Exception();
        }
    }

    private Integer getOldGeneration(String pokemonGame) throws Exception {
        switch (pokemonGame) {
            case "gold-silver":
            case "crystal":
                return 2;
            case "ruby-sapphire":
            case "emerald":
            case "firered-leafgreen":
                return 3;
            case "diamond-pearl":
            case "platinum":
            case "heartgold-soulsilver":
                return 4;
            case "black-white":
            case "black-2-white-2":
                return 5;
            case "x-y":
            case "omega-ruby-alpha-sapphire":
                return 6;
            case "sun-moon":
            case "ultra-sun-ultra-moon":
                return 7;
            case "lets-go-pikachu-lets-go-eevee":
            case "sword-shield":
                return 8;
            case "legends-arceus":
            case "scarlet-violet":
                return 9;
            default:
                throw new Exception();
        }
    }

    private List<List<String>> getMoveNames(List<MoveList> pokemonMoves) {
        List<List<String>> moveNames = new ArrayList<>();

        for (int i = 0; i < pokemonMoves.size(); i++) {
            moveNames.add(new ArrayList<>());
        }

        for (int i = 0; i < moveNames.size(); i++) {
            String typeEntry = pokemonMoves.get(i).getMove().getName();
            moveNames.get(i).add(typeEntry);
            if (!typeEntry.contains("-")) {
                moveNames.get(i).add(typeEntry.substring(0, 1).toUpperCase() + typeEntry.substring(1));
            } else {
                String newMoveName = typeEntry.replaceAll("-", " ");
                newMoveName = newMoveName.substring(0, 1).toUpperCase() + newMoveName.substring(1);
                int firstSpace = newMoveName.indexOf(" ");
                int lastSpace = newMoveName.lastIndexOf(" ");
                newMoveName = newMoveName.substring(0, firstSpace + 1) + newMoveName.substring(firstSpace + 1, firstSpace + 2).toUpperCase() + newMoveName.substring(firstSpace + 2);
                if (lastSpace != firstSpace) {
                    newMoveName = newMoveName.substring(0, lastSpace + 1) + newMoveName.substring(lastSpace + 1, lastSpace + 2).toUpperCase() + newMoveName.substring(lastSpace + 2);
                }
                moveNames.get(i).add(newMoveName);
            }
        }

        return moveNames;
    }
    
}