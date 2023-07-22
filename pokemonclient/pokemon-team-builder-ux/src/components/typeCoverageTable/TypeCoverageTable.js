import React from 'react'
import {useState, useEffect} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import './TypeCoverageTable.css';

const TypeCoverageTable = ({pokemon1Types, pokemon2Types, pokemon3Types, pokemon4Types, pokemon5Types, pokemon6Types}) => {

    function createTypeData(type) {
        const typeData = [`images/${type}.png`];
        const allPokemonTypes = [pokemon1Types, pokemon2Types, pokemon3Types, pokemon4Types, pokemon5Types, pokemon6Types];
        for (let i = 0; i < allPokemonTypes.length; i++) {
            if (allPokemonTypes[i].includes(type))
                typeData.push('\u2713');
            else
                typeData.push('');
        }
        return typeData;
    }

    const rows = [
        createTypeData('Normal'),
        createTypeData('Fire'),
        createTypeData('Water'),
        createTypeData('Grass'),
        createTypeData('Electric'),
        createTypeData('Ice'),
        createTypeData('Fighting'),
        createTypeData('Poison'),
        createTypeData('Ground'),
        createTypeData('Flying'),
        createTypeData('Psychic'),
        createTypeData('Bug'),
        createTypeData('Rock'),
        createTypeData('Ghost'),
        createTypeData('Dark'),
        createTypeData('Dragon'),
        createTypeData('Steel'),
        createTypeData('Fairy')
    ];

    useEffect(() => {
        
    },[pokemon1Types, pokemon2Types, pokemon3Types, pokemon4Types, pokemon5Types, pokemon6Types])

    return (
        <div>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} size="medium" aria-label="simple table">
                    <TableHead>
                    <TableRow>
                        <TableCell></TableCell>
                        {pokemon1Types.length !== 0 ? <TableCell align="center">{pokemon1Types[0]}</TableCell> : <TableCell align="center">Pokemon</TableCell> }
                        {pokemon2Types.length !== 0 ? <TableCell align="center">{pokemon2Types[0]}</TableCell> : <TableCell align="center">Pokemon</TableCell> }
                        {pokemon3Types.length !== 0 ? <TableCell align="center">{pokemon3Types[0]}</TableCell> : <TableCell align="center">Pokemon</TableCell> }
                        {pokemon4Types.length !== 0 ? <TableCell align="center">{pokemon4Types[0]}</TableCell> : <TableCell align="center">Pokemon</TableCell> }
                        {pokemon5Types.length !== 0 ? <TableCell align="center">{pokemon5Types[0]}</TableCell> : <TableCell align="center">Pokemon</TableCell> }
                        {pokemon6Types.length !== 0 ? <TableCell align="center">{pokemon6Types[0]}</TableCell> : <TableCell align="center">Pokemon</TableCell> }
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {rows.map((row) => (
                        <TableRow
                        key={row.type}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row" align="center">
                                <img className="table-type-icon" src={row[0]} alt="Type" />
                            </TableCell>
                            <TableCell align="center">{row[1]}</TableCell>
                            <TableCell align="center">{row[2]}</TableCell>
                            <TableCell align="center">{row[3]}</TableCell>
                            <TableCell align="center">{row[4]}</TableCell>
                            <TableCell align="center">{row[5]}</TableCell>
                            <TableCell align="center">{row[6]}</TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );

}

export default TypeCoverageTable