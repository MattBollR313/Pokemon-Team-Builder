package matt.bollinger.dev.pokemonapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import matt.bollinger.dev.pokemonapi.documents.*;

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
            newItemNames.add(new ArrayList<>());
        }

        for (int i = 0; i < newItemNames.size(); i++) {
            String itemEntry = originalItemNames.get(i).getName();
            newItemNames.get(i).add(itemEntry);
            if (!itemEntry.contains("-")) {
                newItemNames.get(i).add(itemEntry.substring(0, 1).toUpperCase() + itemEntry.substring(1));
            } else {
                String newMoveName = itemEntry.replaceAll("-", " ");
                newMoveName = newMoveName.substring(0, 1).toUpperCase() + newMoveName.substring(1);
                int firstSpace = newMoveName.indexOf(" ");
                int lastSpace = newMoveName.lastIndexOf(" ");
                newMoveName = newMoveName.substring(0, firstSpace + 1) + newMoveName.substring(firstSpace + 1, firstSpace + 2).toUpperCase() + newMoveName.substring(firstSpace + 2);
                if (lastSpace != firstSpace) {
                    newMoveName = newMoveName.substring(0, lastSpace + 1) + newMoveName.substring(lastSpace + 1, lastSpace + 2).toUpperCase() + newMoveName.substring(lastSpace + 2);
                }
                newItemNames.get(i).add(newMoveName);
            }
        }

        return newItemNames;
    }

    public String getHeldItemDescription(String itemName) {
        String apiUrl = itemDetailsUrl + itemName;

        ItemDetails itemInfo = builder.get().uri(apiUrl).retrieve()
            .onStatus(HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(String.class).map(Exception::new))
            .bodyToMono(ItemDetails.class).block();

        String description = itemInfo.getEffect_entries().get(0).getShort_effect();

        return description;
    }

}