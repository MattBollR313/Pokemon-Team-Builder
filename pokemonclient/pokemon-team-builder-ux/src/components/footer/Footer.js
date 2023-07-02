import React from 'react';
import './Footer.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Row, Col } from "react-bootstrap";

const Footer = () => {
    return (
        <section className="main-footer">
            <h4>Pokemon Team Builder</h4>
            <Container fluid="lg" className="footer-info">
                <Row>
                    <Col xs={12} lg={6}>
                        <div>
                            Pokemon Team Builder is a web application with the goal of making it easier to start creating a team. Once a game is chosen, Pokemon can be chosen from a list and added to your team with various options that allow you to create a team that fits your play style.
                        </div>
                    </Col>
                    <div className="d-md-none">&nbsp;</div>
                    <Col xs={12} lg={6}>
                        <div>If you have any suggestions or problems with the application, please reach out to the email address below.</div>
                        <div><a href = "mailto: matthewrb990@gmail.com">matthewrb990@gmail.com</a></div>
                    </Col>
                </Row>
            </Container>
            <div>Copyright @{new Date().getFullYear()} Matthew Bollinger | All rights reserved</div>
        </section>
    )
}

export default Footer;