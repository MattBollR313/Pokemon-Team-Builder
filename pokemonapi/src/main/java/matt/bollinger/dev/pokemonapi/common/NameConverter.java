package matt.bollinger.dev.pokemonapi.common;

public class NameConverter {
    
    public static String convertName(String itemEntry) {
        if (!itemEntry.contains("-")) {
            return itemEntry.substring(0, 1).toUpperCase() + itemEntry.substring(1);
        } else {
            String newMoveName = itemEntry.replaceAll("-", " ");
            newMoveName = newMoveName.substring(0, 1).toUpperCase() + newMoveName.substring(1);
            int firstSpace = newMoveName.indexOf(" ");
            int lastSpace = newMoveName.lastIndexOf(" ");
            newMoveName = newMoveName.substring(0, firstSpace + 1) + newMoveName.substring(firstSpace + 1, firstSpace + 2).toUpperCase() + newMoveName.substring(firstSpace + 2);
            if (lastSpace != firstSpace) {
                newMoveName = newMoveName.substring(0, lastSpace + 1) + newMoveName.substring(lastSpace + 1, lastSpace + 2).toUpperCase() + newMoveName.substring(lastSpace + 2);
            }
            return newMoveName;
        }
    }

}
