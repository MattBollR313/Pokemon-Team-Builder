import React from 'react';
import Select from 'react-select';
import { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from "react-bootstrap";
import PokemonSlot from '../pokemonSlot/PokemonSlot';

const Home = ({pokemon}) => {
  const options = [
    { value: 'region1', label: 'Region 1' },
    { value: 'region2', label: 'Region 2' },
    { value: 'region3', label: 'Region 3' }
  ];

  const [selected, setSelected] = useState(null);

  const handleChange = (selectedOption) => {
    setSelected(selectedOption);
    console.log(`Option selected:`, selectedOption);
  };

  return (
    <Container fluid="md">
      <Row>
        <Col className="justify-content-md-left" xs={12} md={4}>
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
          <div><Select options={options} onChange={handleChange} autoFocus={true} /></div>
        </Col>
      </Row>
    </Container>
  )
}

export default Home