import logo from './logo.svg';
import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';

function App() {

  const [pokemon, setPokemon] = useState();

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
      <h3>Hello World</h3>
    </div>
  );
}

export default App;
