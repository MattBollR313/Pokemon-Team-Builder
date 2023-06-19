package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

@Service
public class PokemonAbilitiesService {
    
    private static final String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
    private static final String abilityUrl = "https://pokeapi.co/api/v2/ability/";

    @Autowired
    private WebClient builder;
    
    public List<String> getPokemonAbilities(String pokemonName) throws Exception {
        String apiUrl = pokemonUrl + pokemonName;
		
        PokemonInfo pokemon = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(PokemonInfo.class).block();

        List<AbilityList> pokemonAbilities = pokemon.getAbilities();
        List<String> abilityFormattedNames = new ArrayList<>();
        for (AbilityList entry: pokemonAbilities) {
            String apiAbilityUrl = abilityUrl + entry.getAbility().getName();

            AbilityInfo ability = builder.get().uri(apiAbilityUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(AbilityInfo.class).block();

            List<PokedexName> abilityNames = ability.getNames();
            for (PokedexName name: abilityNames) {
                if (name.getLanguage().getName().equals("en")) {
                    abilityFormattedNames.add(name.getName());
                    break;
                } 
            }
        }

        return abilityFormattedNames;
    }

    public String getAbilityDescription(String abilityName) throws Exception {
        String apiAbilityUrl = abilityUrl + abilityName;

        AbilityInfo ability = builder.get().uri(apiAbilityUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(AbilityInfo.class).block();

        List<EffectEntry> abilityEffects = ability.getEffect_entries();
        for (EffectEntry effect: abilityEffects) {
            if (effect.getLanguage().getName().equals("en"))
                return effect.getShort_effect();
        }

        return null;
    }
    
}