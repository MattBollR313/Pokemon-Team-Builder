//import logo from './logo.svg';
import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './components/home/Home';

function App() {

  const [pokemon, setPokemon] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getPokemon = async () => {

    try {  
      const response = await api.get("/api/samplepokemon");
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
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route path="/" element={<Home pokemon={pokemon}/>}></Route>
        </Route>
      </Routes>
    </div>
  );
  
}

export default App;
