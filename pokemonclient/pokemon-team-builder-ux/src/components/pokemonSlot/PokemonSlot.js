import React from 'react'
import {useState, useEffect} from 'react';
import { Box } from '@mui/material';
import { Button } from 'react-bootstrap';


const PokemonSlot = ({singlePokemon}) => {

  const [pokemonInfo, setPokemonInfo] = useState(null);

  const getPokemonInfo = () => {
    console.log(`Pokemon Info:`, singlePokemon);
    setPokemonInfo(singlePokemon);
  };

  const handleClick = () => {
    setPokemonInfo(null);
    console.log("Box Cleared");
  };

  useEffect(() => {
    getPokemonInfo();
  },[])

  return (
    <div>
      <Box sx={{ p: 2, border: '1px dashed grey' }}>
        <div>{pokemonInfo}</div>
      </Box>
      <Button variant="success">Add</Button>
      <Button variant="danger" onClick={handleClick}>Remove</Button>
    </div>
    
  )
}

export default PokemonSlot