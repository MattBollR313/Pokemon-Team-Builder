import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from "react-bootstrap";
import PokemonSlot from '../pokemonSlot/PokemonSlot';

const Home = ({pokemon}) => {
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
          <div>Dropdown Placeholder</div>
        </Col>
      </Row>
    </Container>
  )
}

export default Home