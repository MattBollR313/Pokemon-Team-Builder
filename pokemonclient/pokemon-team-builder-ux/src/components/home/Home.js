import React from 'react';
import Select from 'react-select';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from "react-bootstrap";
import PokemonSlot from '../pokemonSlot/PokemonSlot';

const Home = () => {

  // Function to alter value of game to right string for API Call
  const modifyGameName = (gameName) => {
    switch (gameName) {
      case "Kanto (Gen 1/3)":
          return "kanto";
      case "Johto (Gen 2)":
          return "original-johto";
      case "Hoenn (Gen 3)":
          return "hoenn";
      case "Sinnoh (DP Gen 4)":
          return "original-sinnoh";
      case "Sinnoh (Plat Gen 4)":
          return "extended-sinnoh";
      case "Johto (Gen 4)":
          return "updated-johto";
      case "Unova (BW Gen 5)":
          return "original-unova";
      case "Unova (B2W2 Gen 5)":
          return "updated-unova";
      case "Kalos (Gen 6)":
          return "kalos-central";
      case "Hoenn (Gen 6)":
          return "updated-hoenn"; 
      case "Alola (SM Gen 7)":
          return "original-alola";
      case "Alola (USUM Gen 7)":
          return "updated-alola";
      case "Kanto (Let's Go)":
          return "letsgo-kanto";
      case "Galar (Gen 8)":
          return "galar";
      case "Hisui (Gen 9)":
          return "hisui";
      case "Paldea (Gen 9)":
          return "paldea";
      default:
          return "";
    }
  }
 
  // Sample Pokemon API Request
  /*const [pokemon, setPokemon] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getPokemon = async () => {
    try {  
      const response = await api.get("/api/samplepokemon");
      console.log(response.data);
      setPokemon(response.data);
    } 
    catch (err) {  
      console.log(err);
    }     
  }*/

  // Pokemon Game API Request
  const [pokemonGame, setPokemonGame] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getPokemonGame = async () => {
    try {  
      const pokedexResponse = await api.get("/api/pokedex");
      console.log(pokedexResponse.data);
      setPokemonGame(pokedexResponse.data);
    } 
    catch (err) {  
      console.log(err);
    }    
  }

  useEffect(() => {
    //getPokemon();
    getPokemonGame();
  },[])

  // Available Pokemon API Request
  const [availPokemon, setAvailPokemon] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getAvailPokemon = async (selectedGame) => {
    try {  
      const availPokemonResponse = await api.get(`/api/availablepokemon/${selectedGame}`);
      console.log(`Available Pokemon Returned:`, availPokemonResponse.data);
      setAvailPokemon(availPokemonResponse.data);
    } 
    catch (err) {  
      console.log(err);
    }    
  }

  // Pokemon Game Dropdown Functionality
  const gameOptions = [];
  for (let i = 0; i < pokemonGame.length; i++) {
    gameOptions.push({
      value: i.toString(),
      label: pokemonGame[i]
    })
  }

  const [selected, setSelected] = useState(null);

  const handleChange = (selectedOption) => {
    setSelected(selectedOption.label);
    getAvailPokemon(modifyGameName(selectedOption.label));
    console.log(`Option selected:`, selectedOption);
  };

  

  return (
    <Container fluid="lg" data-testid="home-1">
      <Row style={{marginTop: '2rem'}}>
        <Col xs={12} lg={6}>
          <Col className="mx-auto" md={6}>
            <div><Select options={gameOptions} onChange={handleChange} autoFocus={true} /></div>
            <div className="d-md-none">&nbsp;</div>
          </Col>
        </Col>
        <Col xs={12} lg={6}>
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)}/>}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)}/>}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)}/>}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)}/>}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)}/>}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)}/>}
            </div>
          }
        </Col>
      </Row>
    </Container>
  )
}

export default Home