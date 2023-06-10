import React from 'react'
import { Box } from '@mui/material';
import { Button } from 'react-bootstrap';

const PokemonSlot = ({singlePokemon}) => {
  return (
    <div>
      <Box sx={{ p: 2, border: '1px dashed grey' }}>
        <div>{singlePokemon}</div>
      </Box>
      <Button variant="success">Add</Button>
      <Button variant="danger">Remove</Button>
    </div>
    
  )
}

export default PokemonSlot