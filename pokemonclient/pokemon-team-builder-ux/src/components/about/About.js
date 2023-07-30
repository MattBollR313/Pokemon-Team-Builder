import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from "react-bootstrap";
import smilingPhoto from '../../images/SmilingPhoto.jpg';
import './About.css';

const About = () => {
  return (
    <Container fluid="lg">
        <Row style={{marginTop: '2rem'}}>
            <h3>About Us</h3>
            <hr />
            <div>As more and more Pokemon games are being made, it has become quite clear to myself that many players do not have the necessary resources starting out to properly build a team that fits their playstyle and is powerful enough to go through an entire game. The purpose of this web application is to fix this issue. Once a game is selected from the dropdown, it is then possible to add Pokemon to your team based off of the Pokemon available in the chosen game. When a Pokemon is added to your team, you will be able to customize it through its held item, ability, and moveset. In addition, the statistics about the Pokemon are displayed for easy viewing and planning.</div>
            <div>&nbsp;</div>
            <div>Beyond the statistics and options that each Pokemon has to offer, there are additional features that make up this application. A type coverage chart has been created for easy viewing of how well your team is balanced. All eighteen types are listed, making it easy to tell if one type has no representation. In addition, hints are displayed to help you with your team building, ranging from tips on how to improving type coverage to creating a more balanced team based off of the statistics that they have.</div>
            <div>&nbsp;</div>
            <div>Finally, the ability to save and retrieve a previously created team is available so that you don't need to write down all of your chosen Pokemon once your team is created. All of these features have been created to help serve you, the player. I hope as you continue to pursue the various Pokemon games, that this application may be helpful.</div>
            <div>&nbsp;</div>
            <p><small>*PokeAPI is used to help provide the information that is displayed on this website.</small></p>
        </Row>
        <Row style={{marginTop: '2rem', marginBottom: '2rem'}}>
            <Col xs={12} lg={4}>
                <img className="about-photo" src={smilingPhoto} alt="Matt Smiling" />
            </Col>
            <Col xs={12} lg={8}>
                <div className="photo-info">I am a student at Penn State World Campus where I am currently going for my Masters of Software Engineering. This project has been something that I have been wanting to work on for some time now so here is my creation. Please let me know if you have any feedback by reaching out to this email address: <a href = "mailto: matthewrb990@gmail.com">matthewrb990@gmail.com</a></div>
            </Col>
        </Row>
    </Container>
  )
}

export default About