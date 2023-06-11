import React from 'react'
import {useState, useEffect} from 'react';
import Select from 'react-select';
import { Box } from '@mui/material';
import { Button } from 'react-bootstrap';
import { Container, Row, Col } from "react-bootstrap";


const PokemonSlot = ({availablePokemon}) => {

  const [pokemonInfo, setPokemonInfo] = useState(null);

  // Remove Button Functionality
  const handleRemoveClick = () => {
    setPokemonInfo(null);
    console.log("Box Cleared");
  };

  // Add Button Functionality
  const [addClick, setAddClick] = useState(false);

  const handleAddClick = () => {
    if (addClick === false) {
      setAddClick(true);
      console.log(`Show Dropdown: ${addClick}`);
    } else {
      setAddClick(false);
      console.log(`Hide Dropdown: ${addClick}`);
    }
  };

  // Available Pokemon Dropdown Values
  const pokemonOptions = [];
  for (let i = 0; i < availablePokemon.length; i++) {
    pokemonOptions.push({
      value: i.toString(),
      label: availablePokemon[i]
    })
  }

  const handlePokemonChange = (selectedOption) => {
    setPokemonInfo(selectedOption.label);
    setAddClick(false);
    console.log(`Option selected:`, selectedOption);
  };

  return (
    <div>
      <Container fluid>
        <Row className="align-items-center">
          <Col xs={12} md={3}>
            <Button variant="danger" onClick={handleRemoveClick}>Remove</Button>
          </Col>
          <Col xs={12} md={6}>
            <Box sx={{ p: 2, border: '1px dashed grey', minHeight: '60px', marginTop: '1.5rem', marginBottom: '1.5rem' }}>
              <div>{pokemonInfo}</div>
            </Box>
          </Col>
          <Col xs={12} md={3}>
            <Button variant="success" onClick={handleAddClick}>Add</Button>
            { addClick ? <div><Select options={pokemonOptions} onChange={handlePokemonChange} autoFocus={true} /></div> : null }
          </Col>
        </Row>
      </Container>
    </div>
  )
}

export default PokemonSlot