import React from 'react';
import './Footer.css';

const Footer = () => {
    return (
        <section className="main-footer">
            <hr />
            Copyright @{new Date().getFullYear()} Matthew Bollinger | All rights reserved
        </section>
    )
}

export default Footer;