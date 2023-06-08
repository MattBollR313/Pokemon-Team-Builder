import React from 'react'
import PokemonSlot from '../pokemonSlot/PokemonSlot';

const Home = ({pokemon}) => {
  /*return (
    <div>
      Hello World
    </div>
  )*/
  
  return (
    <div>
        {
            pokemon.map((singlePokemon) => {
            return(
                <div>
                  {<PokemonSlot singlePokemon={singlePokemon} />}
                  <br/>
                </div>
            )
            })
        }
    </div>
  )
}

export default Home