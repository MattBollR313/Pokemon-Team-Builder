import React from 'react'
import {useState, useEffect} from 'react';
import { Box } from '@mui/material';
import { Button } from 'react-bootstrap';
import { Container, Row, Col } from "react-bootstrap";


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
  },[]);

  return (
    <div>
      <Container fluid>
        <Row className="align-items-center">
          <Col xs={12} md={3}>
            <Button variant="danger" onClick={handleClick}>Remove</Button>
          </Col>
          <Col xs={12} md={6}>
            <Box sx={{ p: 2, border: '1px dashed grey', marginTop: '0.5rem', marginBottom: '0.5rem' }}>
              <div>{pokemonInfo}</div>
            </Box>
          </Col>
          <Col xs={12} md={3}>
            <Button variant="success">Add</Button>
          </Col>
        </Row>
      </Container>
    </div>
    
  )
}

export default PokemonSlot