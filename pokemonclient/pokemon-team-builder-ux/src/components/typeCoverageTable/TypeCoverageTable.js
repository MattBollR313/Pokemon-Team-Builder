import React from 'react'
import {useState, useEffect} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const TypeCoverageTable = ({pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6}) => {

    function createData(type, coverage1, coverage2, coverage3, coverage4, coverage5, coverage6) {
        return { type, coverage1, coverage2, coverage3, coverage4, coverage5, coverage6 };
    }
      
    const rows = [
        createData('Normal', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Fire', 'No', 'No', 'No', 'Yes', 'No', 'No'),
        createData('Water', 'Yes', 'No', 'No', 'No', 'No', 'No'),
        createData('Grass', 'No', 'Yes', 'No', 'No', 'No', 'No'),
        createData('Electric', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('Ice', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('Fighting', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('Poison', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('Ground', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Flying', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Psychic', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Bug', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Rock', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Ghost', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Dark', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Dragon', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('Steel', 'Yes', 'No', 'No', 'No', 'No', 'No'),
        createData('Fairy', 'No', 'No', 'Yes', 'No', 'No', 'No')
    ];

    useEffect(() => {
        
    },[pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6])

    return (
        <div>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                    <TableRow>
                        <TableCell></TableCell>
                        <TableCell>{pokemon1}</TableCell>
                        <TableCell>{pokemon2}</TableCell>
                        <TableCell>{pokemon3}</TableCell>
                        <TableCell>{pokemon4}</TableCell>
                        <TableCell>{pokemon5}</TableCell>
                        <TableCell>{pokemon6}</TableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {rows.map((row) => (
                        <TableRow
                        key={row.type}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">
                                {row.type}
                            </TableCell>
                            <TableCell>{row.coverage1}</TableCell>
                            <TableCell>{row.coverage2}</TableCell>
                            <TableCell>{row.coverage3}</TableCell>
                            <TableCell>{row.coverage4}</TableCell>
                            <TableCell>{row.coverage5}</TableCell>
                            <TableCell>{row.coverage6}</TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );

}

export default TypeCoverageTable