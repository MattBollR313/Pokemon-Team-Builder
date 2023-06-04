package matt.bollinger.dev.pokemonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

//import matt.bollinger.dev.pokemonapi.documents.Pokedex;

@SpringBootApplication
public class PokemonapiApplication {

	@Bean
	public WebClient getWebClientBuilder() {
		return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
		.codecs(configurer -> configurer
		  .defaultCodecs()
		  .maxInMemorySize(16 * 1024 * 1024))
		.build())
	    .build();
	}

	public static void main(String[] args) {
		SpringApplication.run(PokemonapiApplication.class, args);

		/*String url = "https://pokeapi.co/api/v2/pokedex/kanto";
		WebClient builder = getWebClientBuilder();

		Pokedex pokemonExample = builder.get().uri(url).retrieve().bodyToMono(Pokedex.class).block();

		System.out.println("-----------------");
		System.out.println(pokemonExample.getPokemon_entries());
		System.out.println("-----------------");*/
	}

}
