import React from 'react'
import { Box } from '@mui/material';

const PokemonSlot = ({singlePokemon}) => {
  return (
    <div>
      <Box sx={{ p: 2, border: '1px dashed grey' }}>
        <div>{singlePokemon}</div>
      </Box>
    </div>
    
  )
}

export default PokemonSlot