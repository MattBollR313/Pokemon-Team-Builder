//import logo from './logo.svg';
import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';

function App() {

  const [pokemon, setPokemon] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getPokemon = async () => {

    try {  
      const response = await api.get("/api/pokemonteam");
      console.log(response.data);
      setPokemon(response.data);
    } 
    catch (err) {  
      console.log(err);
    }
    
  }

  useEffect(() => {
    getPokemon();
  },[])

  return (
    <div className="App">
      {
        pokemon.map((singlePokemon) => {
          return(
            <h4>{singlePokemon.teamName}</h4>
          )
        })
      }
    </div>
  );
  
}

export default App;
