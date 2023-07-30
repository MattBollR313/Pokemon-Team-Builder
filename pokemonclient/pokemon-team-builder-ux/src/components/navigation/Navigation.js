import React from 'react';
import {Container, Nav, Navbar} from 'react-bootstrap';
import {NavLink} from 'react-router-dom';
import pokemonIcon from '../../images/pokeball.png';
import './Navigation.css';

const Navigation = () => {
  return (
    <>
        <Navbar sticky="top" bg="primary" data-bs-theme="dark" expand="lg">
            <Container fluid>
                <Navbar.Brand href="/">
                    <img className="home-icon" src={pokemonIcon} alt="Pokemon Icon" /> Pokemon Team Builder
                </Navbar.Brand>
                <Navbar.Toggle />
                <Navbar.Collapse className="justify-content-end">
                    <Nav>
                        <NavLink className="nav-link" to="/">Home</NavLink>
                        <NavLink className="nav-link" to="/about">About</NavLink>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    </>
  )
}

export default Navigation