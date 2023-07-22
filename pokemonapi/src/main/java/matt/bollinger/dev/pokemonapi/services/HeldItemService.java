package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;
import matt.bollinger.dev.pokemonapi.common.NameConverter;

@Service
public class HeldItemService {
    
    private static final String holdableItemUrl = "https://pokeapi.co/api/v2/item-attribute/5/";
    private static final String itemDetailsUrl = "https://pokeapi.co/api/v2/item/";

    @Autowired
    private WebClient builder;
    
    public List<List<String>> allHeldItems() throws Exception {
	
        HoldableItemList heldItemList = builder.get().uri(holdableItemUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(HoldableItemList.class).block();

        List<SimpleEntry> originalItemNames = heldItemList.getItems();
        List<List<String>> newItemNames = new ArrayList<>();

        for (int i = 0; i < originalItemNames.size(); i++) {
            String itemEntry = originalItemNames.get(i).getName();
            newItemNames.add(new ArrayList<>());
            newItemNames.get(i).add(itemEntry);
            newItemNames.get(i).add(NameConverter.convertName(itemEntry));
        }

        return newItemNames;
    }

    public String getHeldItemDescription(String itemName) throws Exception {
        String apiUrl = itemDetailsUrl + itemName;

        ItemDetails itemInfo = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(ItemDetails.class).block();

        String description = itemInfo.getEffect_entries().get(0).getShort_effect();
        if (description.length() >= 6 && description.substring(0, 6).equals("Held: "))
            description = description.substring(6);

        return description;
    }

}
