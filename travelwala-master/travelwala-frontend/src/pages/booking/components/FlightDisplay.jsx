import * as React from 'react';
import * as ThemeStyle from './css/style'
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Divider from '@mui/material/Divider';
import { departureFlightSelector, returnFlightSelector } from '../../../redux/selectors';
import {useDispatch, useSelector}  from "react-redux";
import { isConstructorDeclaration } from 'typescript';
import { hourMinuteDiff } from '../../../utils/DateTimeUtils';


const style = {
    border:1,
    ...ThemeStyle.box, 
    width: '140%',
    px: 2,
    ml: -20
};

const infoText = {
    p: 0,
    mb: 0,
    color: 'red',
    border: 1
};

const Flight = ({Flight}) => {
    const departureTime = new Date(Flight.departureTime);
    const expectedArrivalTime = new Date(Flight.expectedArrivalTime);
    // const flightTime = (expectedArrivalTime.getTime() - departureTime.getTime()) / (1000 * 3600); 
    const flightTime = hourMinuteDiff(departureTime, expectedArrivalTime);
    return (
        <Grid container spacing={2} sx={{}}>
            <Grid item xs={6}>
                <Box sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'flex-start',
                    lineHeight: 0
                }}>
                    <item>
                        <p sx={infoText}>{Flight.airline.name}</p>
                    </item>
                    <item>
                        <p>{Flight.id.slice(0, 7)}</p>
                    </item>
                </Box>
            </Grid>
            <Grid item xs={6}>
                <Box sx={{
                    display:'flex',
                    alignItems: 'flex-end',
                    flexDirection:'column',
                    lineHeight: 0
                }}>
                    <item>
                        <p>{flightTime}</p>
                    </item>
                    <item>
                        <p>{departureTime.getHours()}:{departureTime.getMinutes()} - {expectedArrivalTime.getHours()}:{expectedArrivalTime.getMinutes()}</p>
                    </item>
                    <item>
                        <p>{departureTime.getHours()}:{departureTime.getMinutes() === 0 ? "00" : departureTime.getMinutes()} in {Flight.departureCity}</p>
                    </item>
                </Box>
            </Grid>
        </Grid>
    );
}

export default function FlightDisplay() {
    const departureFlight = useSelector(departureFlightSelector);
    const returnFlight = useSelector(returnFlightSelector)

    return (
        !(Object.keys(departureFlight).length === 0 && departureFlight.constructor === Object) ? (
        <Box sx={style}>
            <div>
                <h3>Departure</h3>
            </div>
            <Flight Flight={departureFlight}/>
            {
                !(Object.keys(returnFlight).length === 0 && returnFlight.constructor === Object) ? 
                (
                    <>
                    <Divider />
                    <div>
                        <h3>Return</h3>
                    </div>
                    <Flight Flight={returnFlight}/>
                    </>
                ) : (<></>)
            }
            <Divider />
        </Box>) : (<></>)
    );
}
