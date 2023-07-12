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

    public List<String> getPokemonMoves(String moveName, String pokemonGame) throws Exception {
        String apiUrl = moveUrl + moveName;
		
        MoveListDetails move = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(MoveListDetails.class).block();

        String description = move.getEffect_entries().get(0).getShort_effect();
        if (description.contains("$")) {
            description = description.substring(0, description.indexOf("$")) + description.substring(description.indexOf("%") + 1);
        }
        String type = move.getType().getName().substring(0, 1).toUpperCase() + move.getType().getName().substring(1);
        String power = move.getPower() != null ? move.getPower().toString() : null;
        String pp = move.getPp() != null ? move.getPp().toString() : null;
        String accuracy = move.getAccuracy() != null ? move.getAccuracy().toString() : null;

        /*if (pastTypes != null && pastTypes.size() != 0) {
            Integer pokemonGameGeneration = getGeneration(pokemonGame);
            if (pokemonGameGeneration.equals(null))
                throw new Exception();
            PastTypeList pastType = pastTypes.get(pastTypes.size() - 1);
            String generationUrl = pastType.getGeneration().getUrl();
            if (Character.getNumericValue(generationUrl.charAt(generationUrl.length() - 2)) >= pokemonGameGeneration) {
                pokemonTypes = pastType.getTypes();
                //return getTypeNames(pokemonTypes);
                return new ArrayList<>();
            }
        }*/

        //return getTypeNames(pokemonTypes);
        List<String> moveDetails = new ArrayList<>();
        moveDetails.add(description);
        moveDetails.add(type);
        moveDetails.add(power);
        moveDetails.add(pp);
        moveDetails.add(accuracy);
        return moveDetails;
    }

    private Integer getGeneration(String pokemonGame) {
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
                return null;
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