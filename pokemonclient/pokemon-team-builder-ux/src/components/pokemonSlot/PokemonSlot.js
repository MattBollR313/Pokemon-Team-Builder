import React from 'react'
import {useState, useEffect} from 'react';
import Select from 'react-select';
import { Box } from '@mui/material';
import { Button } from 'react-bootstrap';
import { Container, Row, Col } from "react-bootstrap";
import api from '../../api/axiosConfig';
import './PokemonSlot.css';


const PokemonSlot = ({availablePokemon, gameGeneration}) => {

  const [pokemonInfo, setPokemonInfo] = useState(null);

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

  // Pokemon Ability Values
  const [pokemonAbilities, setPokemonAbilities] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getPokemonAbilities = async (pokemonName) => {
    try {  
      const abilitiesResponse = await api.get(`/api/abilities/${pokemonName}`);
      console.log(`Abilities Returned:`, abilitiesResponse.data);
      setPokemonAbilities(abilitiesResponse.data);
    } 
    catch (err) {  
      console.log(err);
    } 
  }

  // Pokemon Type Values
  const [pokemonTypes, setPokemonTypes] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getPokemonTypes = async (pokemonName) => {
    try {  
      const typesResponse = await api.get(`/api/types/${gameGeneration}/${pokemonName}`);
      console.log(`Types Returned:`, typesResponse.data);
      const typeCorrectNames = [];
      for (let i = 0; i < typesResponse.data.length; i++) {
        typeCorrectNames.push(typesResponse.data[i].charAt(0).toUpperCase() + typesResponse.data[i].slice(1));
      }
      setPokemonTypes(typeCorrectNames);
    } 
    catch (err) {  
      console.log(err);
    } 
  }

  // Handle Change of Pokemon Selection
  const handlePokemonChange = (selectedOption) => {
    setPokemonInfo(selectedOption.label);
    setAddClick(false);
    const paramName = selectedOption.label.charAt(0).toLowerCase() + selectedOption.label.slice(1);
    getPokemonAbilities(paramName);
    getPokemonTypes(paramName);
    console.log(`Option selected:`, selectedOption);
  };

  // Ability Dropdown Functionality
  const abilityOptions = [];
  for (let i = 0; i < pokemonAbilities.length; i++) {
    abilityOptions.push({
      value: i.toString(),
      label: pokemonAbilities[i][0]
    })
  }

  const [abilityDescription, setAbilityDescription] = useState(null);

  const handleAbilityChange = (selectedOption) => {
    setAbilityDescription(pokemonAbilities[selectedOption.value][1]);
    console.log(`Option selected:`, selectedOption);
  };

  // Remove Button Functionality
  const handleRemoveClick = () => {
    setPokemonInfo(null);
    setAbilityDescription(null);
    setPokemonTypes([]);
    console.log("Box Cleared");
  };

  return (
    <div>
      <Container fluid className="slot-container">
        <Row className="align-items-center" >
          <Col xs={12} md={9}>
            <Box className="slot-box">
              <h5>{pokemonInfo}</h5>
              { pokemonTypes.length !== 0 ? <div>{pokemonTypes.map(type => <div> {type} </div>)}</div> : null }
              { pokemonInfo !== null ? <div><Select options={abilityOptions} onChange={handleAbilityChange} autoFocus={true} /></div> : null }
              { abilityDescription !== null ? <div>{abilityDescription}</div> : null }
            </Box>
          </Col>
          <Col xs={12} md={3}>
            <Button variant="danger" onClick={handleRemoveClick}>Remove</Button>
            <div>&nbsp;</div>
            <Button variant="success" onClick={handleAddClick}>Add</Button>
            { addClick ? <div><Select options={pokemonOptions} onChange={handlePokemonChange} autoFocus={true} /></div> : null }
          </Col>
        </Row>
      </Container>
    </div>
  )
}

export default PokemonSlot