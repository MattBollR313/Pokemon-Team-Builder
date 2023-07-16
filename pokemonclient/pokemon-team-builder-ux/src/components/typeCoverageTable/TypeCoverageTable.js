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

const TypeCoverageTable = ({pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6}) => {

    function createData(type, coverage1, coverage2, coverage3, coverage4, coverage5, coverage6) {
        return { type, coverage1, coverage2, coverage3, coverage4, coverage5, coverage6 };
    }
      
    const rows = [
        createData('images/Normal.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Fire.png', 'No', 'No', 'No', 'Yes', 'No', 'No'),
        createData('images/Water.png', 'Yes', 'No', 'No', 'No', 'No', 'No'),
        createData('images/Grass.png', 'No', 'Yes', 'No', 'No', 'No', 'No'),
        createData('images/Electric.png', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('images/Ice.png', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('images/Fighting.png', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('images/Poison.png', 'No', 'No', 'No', 'No', 'No', 'Yes'),
        createData('images/Ground.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Flying.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Psychic.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Bug.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Rock.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Ghost.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Dark.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Dragon.png', 'No', 'No', 'Yes', 'No', 'No', 'No'),
        createData('images/Steel.png', 'Yes', 'No', 'No', 'No', 'No', 'No'),
        createData('images/Fairy.png', 'No', 'No', 'Yes', 'No', 'No', 'No')
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
                        <TableCell align="center">{pokemon1}</TableCell>
                        <TableCell align="center">{pokemon2}</TableCell>
                        <TableCell align="center">{pokemon3}</TableCell>
                        <TableCell align="center">{pokemon4}</TableCell>
                        <TableCell align="center">{pokemon5}</TableCell>
                        <TableCell align="center">{pokemon6}</TableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {rows.map((row) => (
                        <TableRow
                        key={row.type}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row" align="center">
                                <img className="table-type-icon" src={row.type} alt="Type" />
                            </TableCell>
                            <TableCell align="center">{row.coverage1}</TableCell>
                            <TableCell align="center">{row.coverage2}</TableCell>
                            <TableCell align="center">{row.coverage3}</TableCell>
                            <TableCell align="center">{row.coverage4}</TableCell>
                            <TableCell align="center">{row.coverage5}</TableCell>
                            <TableCell align="center">{row.coverage6}</TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );

}

export default TypeCoverageTable