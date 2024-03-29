import React from 'react';
import Select from 'react-select';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col, Button, Form } from "react-bootstrap";
import PokemonSlot from '../pokemonSlot/PokemonSlot';
import TypeCoverageTable from '../typeCoverageTable/TypeCoverageTable';
import './Home.css';
import { Box } from '@mui/material';
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from 'react-responsive-carousel';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';

const Home = () => {

  // Function to alter value of game to right string for API Call
  const modifyGameName = (gameName) => {
    switch (gameName) {
      case "Kanto (Gen 1/3)":
          return "kanto";
      case "Johto (Gen 2)":
          return "original-johto";
      case "Hoenn (Gen 3)":
          return "hoenn";
      case "Sinnoh (DP Gen 4)":
          return "original-sinnoh";
      case "Sinnoh (Plat Gen 4)":
          return "extended-sinnoh";
      case "Johto (Gen 4)":
          return "updated-johto";
      case "Unova (BW Gen 5)":
          return "original-unova";
      case "Unova (B2W2 Gen 5)":
          return "updated-unova";
      case "Kalos (Gen 6)":
          return "kalos-central";
      case "Hoenn (Gen 6)":
          return "updated-hoenn"; 
      case "Alola (SM Gen 7)":
          return "original-alola";
      case "Alola (USUM Gen 7)":
          return "updated-alola";
      case "Kanto (Let's Go)":
          return "letsgo-kanto";
      case "Galar (Gen 8)":
          return "galar";
      case "Hisui (Gen 9)":
          return "hisui";
      case "Paldea (Gen 9)":
          return "paldea";
      default:
          return "";
    }
  }

  // Pokemon Game API Request
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

  // Available Pokemon API Request
  const [availPokemon, setAvailPokemon] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getAvailPokemon = async (selectedGame) => {
    try {
      if (selectedGame !== null) {
        const availPokemonResponse = await api.get(`/api/availablepokemon/${selectedGame}`);
        console.log(`Available Pokemon Returned:`, availPokemonResponse.data);
        setAvailPokemon(availPokemonResponse.data);
      } else {
        setAvailPokemon([]);
      }
    } 
    catch (err) {  
      console.log(err);
    }    
  }

  // Pokemon Game Dropdown Functionality
  const gameOptions = [];
  for (let i = 0; i < pokemonGame.length; i++) {
    gameOptions.push({
      value: i.toString(),
      label: pokemonGame[i]
    })
  }

  const [selected, setSelected] = useState(null);

  const handleChange = (selectedOption) => {
    if (selectedOption !== null) {
      setSelected(selectedOption.label);
      getAvailPokemon(modifyGameName(selectedOption.label));
      console.log(`Option selected:`, selectedOption);
    } else {
      setSelected(null);
      getAvailPokemon(null);
    }
  };

  // Pokemon Details Input
  const [pokemonOneDetails, setPokemonOneDetails] = useState(['']);
  const [pokemonTwoDetails, setPokemonTwoDetails] = useState(['']);
  const [pokemonThreeDetails, setPokemonThreeDetails] = useState(['']);
  const [pokemonFourDetails, setPokemonFourDetails] = useState(['']);
  const [pokemonFiveDetails, setPokemonFiveDetails] = useState(['']);
  const [pokemonSixDetails, setPokemonSixDetails] = useState(['']);

  // Form Input
  const [value, setTeamName] = useState('');

  let saveTeam = api.create({
    headers: {
      'team-name': value,
      'chosen-game': selected,
      'pokemon-1': pokemonOneDetails,
      'pokemon-2': pokemonTwoDetails,
      'pokemon-3': pokemonThreeDetails,
      'pokemon-4': pokemonFourDetails,
      'pokemon-5': pokemonFiveDetails,
      'pokemon-6': pokemonSixDetails
    }
  })

  // Save Pokemon Functionality
  const savePokemonTeam = async () => {
    try {
      if (selected !== null) {
        const savePokemonTeamResponse = await saveTeam.post(`/api/pokemonteam/save`);
        console.log(`Save Team Status:`, savePokemonTeamResponse.data);
      } else {
        console.log('Failed');
      }
    } 
    catch (err) {  
      console.log(err);
    }    
  }

  const onInput = ({target:{value}}) => setTeamName(value);
  const onFormSubmit = e => {
    e.preventDefault();
    console.log(value);
    savePokemonTeam();
    setTeamName('');
  }

  // Choose Team Functionality
  const [teamNames, setTeamNames] = useState([]); // Must have [] as an argument as will get an undefined error otherwise

  const getTeamNames = async () => {
    try {  
      const teamResponse = await api.get("/api/pokemonteam/allteams");
      console.log(teamResponse.data);
      setTeamNames(teamResponse.data);
    } 
    catch (err) {  
      console.log(err);
    }    
  }

  const teamOptions = [];
  for (let i = 0; i < teamNames.length; i++) {
    teamOptions.push({
      value: i.toString(),
      label: teamNames[i]
    })
  }

  const [selectedTeamDetails, setSelectedTeamDetails] = useState([]);
  const getTeamSelectedDetails = async (teamSelected) => {
    try { 
      let chooseTeam = api.create({
        headers: {
          "team-name": teamSelected
        }
      })
      const selectedTeamResponse = await chooseTeam.get("/api/pokemonteam/getTeam");
      console.log(selectedTeamResponse.data);
      setSelectedTeamDetails(selectedTeamResponse.data);
    } 
    catch (err) {  
      console.log(err);
    }  
  }

  const handleTeamChange = (selectedOption) => {
    if (selectedOption !== null) {
      getTeamSelectedDetails(selectedOption.label);
    } else {
      setSelectedTeamDetails([]);
    }
  };

  // Hint Carousel Functionality
  const [pokemonOneHints, setPokemonOneHints] = useState(['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']);
  const [pokemonTwoHints, setPokemonTwoHints] = useState(['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']);
  const [pokemonThreeHints, setPokemonThreeHints] = useState(['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']);
  const [pokemonFourHints, setPokemonFourHints] = useState(['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']);
  const [pokemonFiveHints, setPokemonFiveHints] = useState(['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']);
  const [pokemonSixHints, setPokemonSixHints] = useState(['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']);

  const [allHints, setAllHints] = useState(['']);

  const getAllHints = async () => {
    try {
      let hintRequest = api.create({
        headers: {
          'pokemon-1': pokemonOneHints,
          'pokemon-2': pokemonTwoHints,
          'pokemon-3': pokemonThreeHints,
          'pokemon-4': pokemonFourHints,
          'pokemon-5': pokemonFiveHints,
          'pokemon-6': pokemonSixHints
        }
      })
      const allHintsResponse = await hintRequest.get(`/api/hints`);
      setAllHints(allHintsResponse.data);
      console.log(`All Hints:`, allHintsResponse.data);
    } 
    catch (err) {  
      console.log(err);
    }    
  }

  // Pokemon Type Chart Input
  const [pokemonOneTypes, setPokemonOneTypes] = useState([]);
  const [pokemonTwoTypes, setPokemonTwoTypes] = useState([]);
  const [pokemonThreeTypes, setPokemonThreeTypes] = useState([]);
  const [pokemonFourTypes, setPokemonFourTypes] = useState([]);
  const [pokemonFiveTypes, setPokemonFiveTypes] = useState([]);
  const [pokemonSixTypes, setPokemonSixTypes] = useState([]);

  // Pokemon Type Chart Toggle Button Logic
  const [showTable, setShowTable] = useState(false);

  const handleTableClick = () => {
    if (showTable === true)
      setShowTable(false);
    else
      setShowTable(true);
  };

  useEffect(() => {
    getPokemonGame();
    getTeamNames();
  },[]);

  useEffect(() => {
    getAllHints();
  }, [pokemonOneHints, pokemonTwoHints, pokemonThreeHints, pokemonFourHints, pokemonFiveHints, pokemonSixHints]);

  return (
    <Container fluid="lg" data-testid="home-1">
      <Row style={{marginTop: '2rem'}}>
        <Col xs={12} lg={6}>
          <Col className="mx-auto" md={6}>
            <div><Select options={gameOptions} onChange={handleChange} autoFocus={true} isClearable={true} placeholder={"Select a game"} /></div>
            <div>&nbsp;</div>
          </Col>

          <Col className="mx-auto" md={6}>
            <div>&nbsp;</div>
            <div><Select options={teamOptions} onChange={handleTeamChange} isClearable={true} placeholder={"Select an existing team"} /></div>
            <div>&nbsp;</div>
          </Col>
          { selectedTeamDetails.length !== 0 ? <div><Popup trigger={<Button variant="success">View Team Information</Button>}>
            <div>Chosen Game: {selectedTeamDetails.chosenGame}</div>
            <div>Pokemon 1:</div>
            <div>{selectedTeamDetails.pokemon1.map((info) => {
                return(<div>{info}</div>)
              })}</div>
            <div>Pokemon 2:</div>
            <div>{selectedTeamDetails.pokemon2.map((info) => {
                return(<div>{info}</div>)
              })}</div>
            <div>Pokemon 3:</div>
            <div>{selectedTeamDetails.pokemon3.map((info) => {
                return(<div>{info}</div>)
              })}</div>
            <div>Pokemon 4:</div>
            <div>{selectedTeamDetails.pokemon4.map((info) => {
                return(<div>{info}</div>)
              })}</div>
            <div>Pokemon 5:</div>
            <div>{selectedTeamDetails.pokemon5.map((info) => {
                return(<div>{info}</div>)
              })}</div>
            <div>Pokemon 6:</div>
            <div>{selectedTeamDetails.pokemon6.map((info) => {
                return(<div>{info}</div>)
              })}</div>
          </Popup><div>&nbsp;</div></div> : <div>&nbsp;</div>}

          <Col className="mx-auto" md={8}>
            <Form onSubmit={onFormSubmit}>
              <Form.Control required type="text" onChange={onInput} value={value} placeholder="Enter team name" />
              <div>&nbsp;</div>
              <Button variant="primary" type="submit">
                Save Team
              </Button>
            </Form>
          </Col>

          <Col className="mx-auto" md={8}>
            <Box className="hint-box">
              <Carousel showStatus={false} showThumbs={false} >
                {allHints.map((hint) => {
                  return(<div>{hint}</div>)
                })}
              </Carousel>
            </Box>  
          </Col>
          
          <div className="d-lg-none">&nbsp;</div>
          <div className="d-xs-block d-lg-none"><Button onClick={handleTableClick}>Toggle Type Coverage Table</Button></div>
          { showTable === true ? <div className="d-lg-none"><div className="type-coverage-table">{<TypeCoverageTable pokemon1Types={pokemonOneTypes} pokemon2Types={pokemonTwoTypes} pokemon3Types={pokemonThreeTypes} pokemon4Types={pokemonFourTypes} pokemon5Types={pokemonFiveTypes} pokemon6Types={pokemonSixTypes} />}</div></div> : <div className="d-lg-none">&nbsp;</div> }
          <div className="d-none d-lg-block"><div className="type-coverage-table">{<TypeCoverageTable pokemon1Types={pokemonOneTypes} pokemon2Types={pokemonTwoTypes} pokemon3Types={pokemonThreeTypes} pokemon4Types={pokemonFourTypes} pokemon5Types={pokemonFiveTypes} pokemon6Types={pokemonSixTypes} />}</div></div>
        </Col>
        <Col xs={12} lg={6}>
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)} setPokemonTableType={setPokemonOneTypes} setPokemonDetails={setPokemonOneDetails} setPokemonHints={setPokemonOneHints} />}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)} setPokemonTableType={setPokemonTwoTypes} setPokemonDetails={setPokemonTwoDetails} setPokemonHints={setPokemonTwoHints} />}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)} setPokemonTableType={setPokemonThreeTypes} setPokemonDetails={setPokemonThreeDetails} setPokemonHints={setPokemonThreeHints} />}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)} setPokemonTableType={setPokemonFourTypes} setPokemonDetails={setPokemonFourDetails} setPokemonHints={setPokemonFourHints} />}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)} setPokemonTableType={setPokemonFiveTypes} setPokemonDetails={setPokemonFiveDetails} setPokemonHints={setPokemonFiveHints} />}
            </div>
          }
          {
            <div>
              {<PokemonSlot availablePokemon={availPokemon} gameGeneration={modifyGameName(selected)} setPokemonTableType={setPokemonSixTypes} setPokemonDetails={setPokemonSixDetails} setPokemonHints={setPokemonSixHints} />}
            </div>
          }
        </Col>
      </Row>
    </Container>
  )
}

export default Home