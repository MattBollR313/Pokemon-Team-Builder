import React from 'react';
import Select from 'react-select';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from "react-bootstrap";
import PokemonSlot from '../pokemonSlot/PokemonSlot';

const Home = () => {
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
    getPokemon();
    getPokemonGame();
  },[])


  const gameOptions = [];
  for (let i = 0; i < pokemonGame.length; i++) {
    gameOptions.push({
      value: i.toString(),
      label: pokemonGame[i]
    })
  }

  const [selected, setSelected] = useState(null);

  const handleChange = (selectedOption) => {
    setSelected(selectedOption);
    console.log(`Option selected:`, selectedOption);
  };

  return (
    <Container fluid="md">
      <Row style={{marginTop: '2rem'}}>
        <Col xs={12} md={4}>
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
        </Col>
        <Col xs={12} md={8}>
          <Col className="mx-auto" md={6}>
            <div><Select options={gameOptions} onChange={handleChange} autoFocus={true} /></div>
          </Col>
        </Col>
      </Row>
    </Container>
  )
}

export default Home