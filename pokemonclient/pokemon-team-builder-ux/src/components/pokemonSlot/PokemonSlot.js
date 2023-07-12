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
      if (typesResponse.data.length === 1) {
        setPokemonTypes([
          `images/${typesResponse.data[0]}.png`
        ]);
      } else {
        setPokemonTypes([
          `images/${typesResponse.data[0]}.png`,
          `images/${typesResponse.data[1]}.png`
        ]);
      }
    } 
    catch (err) {  
      console.log(err);
    } 
  }

  // Pokemon Stat Values
  const [statNames, setStatNames] = useState([]);

  const getPokemonStats = async (pokemonName) => {
    try {  
      const statsResponse = await api.get(`/api/stats/${pokemonName}`);
      console.log(`Stats Returned:`, statsResponse.data);
      setStatNames([
        `HP: ${statsResponse.data[0]}`,
        `Attack: ${statsResponse.data[1]}`,
        `Defense: ${statsResponse.data[2]}`,
        `Sp. Atk: ${statsResponse.data[3]}`,
        `Sp. Def: ${statsResponse.data[4]}`,
        `Speed: ${statsResponse.data[5]}`
      ]);
      console.log(`Stat Names: `, statNames);
    } 
    catch (err) {  
      console.log(err);
    } 
  }

  // Pokemon Evolution Status
  const [pokemonEvolution, setPokemonEvolution] = useState(null); // Must have [] as an argument as will get an undefined error otherwise

  const getPokemonEvolution = async (pokemonName) => {
    try {  
      const evolutionResponse = await api.get(`/api/evolution/${pokemonName}`);
      console.log(`Evolution Status Returned:`, evolutionResponse.data);
      if (evolutionResponse.data[0] === true)
        setPokemonEvolution("Final Stage");
      else
        setPokemonEvolution("Not Final Stage");
    } 
    catch (err) {  
      console.log(err);
    } 
  }

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
    if (selectedOption !== null) {
      setAbilityDescription(pokemonAbilities[selectedOption.value][1]);
      console.log(`Option selected:`, selectedOption);
    } else {
      setAbilityDescription(null);
    }
  };

  // Move Values and Dropdown Functionality
  const [moveNames, setMoveNames] = useState([]);
  const getMoves = async (pokemonName) => {
    try {  
      const movesResponse = await api.get(`/api/moves/${pokemonName}`);
      console.log(`Moves Returned:`, movesResponse.data);
      setMoveNames(movesResponse.data)
    } 
    catch (err) {  
      console.log(err);
    } 
  }

  const moveOptions = [];
  for (let i = 0; i < moveNames.length; i++) {
    moveOptions.push({
      value: i.toString(),
      label: moveNames[i][1]
    })
  }

  const [moveDetails, setMoveDetails] = useState([]);
  const getMoveDetails = async (moveName) => {
    try {  
      const moveDetailsResponse = await api.get(`/api/moves/${moveName}/${gameGeneration}`);
      console.log(`Move Details Returned:`, moveDetailsResponse.data);
      setMoveDetails([
        moveDetailsResponse.data[0],
        `images/${moveDetailsResponse.data[1]}.png`,
        moveDetailsResponse.data[2],
        moveDetailsResponse.data[3],
        moveDetailsResponse.data[4]
      ])
    } 
    catch (err) {  
      console.log(err);
    } 
  }

  const handleMoveChange = (selectedOption) => {
    if (selectedOption !== null) {
      getMoveDetails(moveNames[selectedOption.value][0]);
      console.log(`Option selected:`, selectedOption);
    } else {
      setMoveDetails([]);
    }
  };

  // Held Item Values and Dropdown Functionality
  const [heldItemNames, setHeldItemNames] = useState([]);
  const getHeldItemNames = async () => {
    try {  
      const heldItemsResponse = await api.get(`/api/helditem`);
      console.log(`Held Item Names Returned:`, heldItemsResponse.data);
      setHeldItemNames(heldItemsResponse.data)
    } 
    catch (err) {  
      console.log(err);
    } 
  } 

  const heldItemOptions = [];
  for (let i = 0; i < heldItemNames.length; i++) {
    heldItemOptions.push({
      value: i.toString(),
      label: heldItemNames[i][1]
    })
  }

  const [heldItemDescription, setHeldItemDescription] = useState(null);
  const getHeldItemDescription = async (heldItemName) => {
    try {  
      const heldItemDescriptionResponse = await api.get(`/api/helditem/${heldItemName}`);
      console.log(`Held Item Description Returned:`, heldItemDescriptionResponse.data);
      setHeldItemDescription(heldItemDescriptionResponse.data);
    } 
    catch (err) {  
      console.log(err);
    } 
  }

  const handleHeldItemChange = (selectedOption) => {
    if (selectedOption !== null) {
      getHeldItemDescription(heldItemNames[selectedOption.value][0]);
      console.log(`Option selected:`, selectedOption);
    } else {
      setHeldItemDescription(null);
    }
  };

  // Handle Change of Pokemon Selection
  const handlePokemonChange = (selectedOption) => {
    setPokemonInfo(selectedOption.label);
    setAddClick(false);
    const paramName = selectedOption.label.charAt(0).toLowerCase() + selectedOption.label.slice(1);
    getPokemonAbilities(paramName);
    setAbilityDescription(null);
    getMoves(paramName);
    setMoveDetails([]);
    getHeldItemNames();
    getPokemonTypes(paramName);
    getPokemonStats(paramName);
    getPokemonEvolution(paramName);
    console.log(`Option selected:`, selectedOption);
  };

  // Remove Button Functionality
  const handleRemoveClick = () => {
    setPokemonInfo(null);
    setPokemonAbilities([]);
    setAbilityDescription(null);
    setMoveNames([]);
    setMoveDetails([]);
    setHeldItemNames([]);
    setHeldItemDescription(null);
    setPokemonTypes([]);
    setStatNames([]);
    setPokemonEvolution(null);
    console.log("Box Cleared");
  };

  useEffect(() => {
    handleRemoveClick();
  },[gameGeneration])

  return (
    <div>
      <Container fluid className="slot-container">
        <Row className="align-items-center" >
          <Col xs={12} md={9}>
            <Box className="slot-box">
              <h5>{pokemonInfo}</h5>
              {pokemonInfo !== null ? <hr/> : null }
              <Row className="pokemon-info">
                <Col xs={6}>
                  { pokemonTypes.length !== 0 ? <div className="pokemon-info"><h6>Types:</h6>{pokemonTypes.map(type => <img className="type-icon" src={type} alt="Type" />)}</div> : null }
                  { pokemonEvolution !== null ? <div className="pokemon-info"><h6>Evolution Status:</h6><div>{pokemonEvolution}</div></div> : null }
                </Col>
                <Col xs={6}>
                  { statNames.length !== 0 ? <div><h6>Base Stats:</h6>{statNames.map(stat => <div> {stat} </div>)}</div> : null }
                </Col>
              </Row>
              { pokemonAbilities.length !== 0 ? <div><Select options={abilityOptions} onChange={handleAbilityChange} autoFocus={true} isClearable={true} placeholder={"Select an ability"} /></div> : null }
              { abilityDescription !== null ? <div className="ability-description">{abilityDescription}</div> : null }
              
              { moveNames.length !== 0 ? <div><Select options={moveOptions} onChange={handleMoveChange} autoFocus={true} isClearable={true} placeholder={"Select a move"} /></div> : null }
              { moveDetails.length !== 0 && moveDetails[0] !== null ? <div className="move-description">Description: {moveDetails[0]}</div> : null }
              { moveDetails.length !== 0 && moveDetails[1] !== null ? <div className="move-type">Type: <img className="type-icon" src={moveDetails[1]} alt="Move Type" /></div> : null }
              { moveDetails.length !== 0 && moveDetails[2] !== null ? <div className="move-power">Power: {moveDetails[2]}</div> : null }
              { moveDetails.length !== 0 && moveDetails[3] !== null ? <div className="move-pp">PP: {moveDetails[3]}</div> : null }
              { moveDetails.length !== 0 && moveDetails[4] !== null ? <div className="move-accuracy">Accuracy: {moveDetails[4]}</div> : null }

              { heldItemNames.length !== 0 ? <div><Select options={heldItemOptions} onChange={handleHeldItemChange} autoFocus={true} isClearable={true} placeholder={"Select an item"} /></div> : null }
              { heldItemDescription !== null ? <div className="held-item-description">{heldItemDescription}</div> : null }
            </Box>
          </Col>
          <Col xs={12} md={3}>
            <div className="d-md-none">&nbsp;</div>
            { pokemonInfo !== null ? <div><Button variant="danger" onClick={handleRemoveClick}>Remove</Button></div> : <div><Button variant="danger" disabled>Remove</Button></div> }
            <Button className="add-button" variant="success" onClick={handleAddClick}>Add</Button>
            { addClick ? <div className="pokemon-dropdown"><Select options={pokemonOptions} onChange={handlePokemonChange} autoFocus={true}/></div> : null }
          </Col>
        </Row>
      </Container>
    </div>
  )
}

export default PokemonSlot