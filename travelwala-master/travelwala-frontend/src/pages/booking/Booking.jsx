import { Button } from '@mui/material';
import Grid from '@mui/material/Grid';
import { Box } from '@mui/material';
import React from "react";
import BookingProcess from "./components/BookingProcess";
import ContactDetail from "./components/ContactDetail";
import FlightDisplay from "./components/FlightDisplay";
import TravelerDetail from "./components/TravelerDetail";
import {useDispatch, useSelector}  from "react-redux";
import { passengerSelector } from "../../redux/selectors";
import useNavigateSearch from "../../hooks/useNavigateSearch";

const Booking = () => {
    const {adult, child, infant} = useSelector(passengerSelector);
    const createPassengerList = () => {
        let passengers = [];
        for(let i = 0; i < adult; i++) {
            passengers = [
                ...passengers ,
                {
                    title: "",
                    firstName: "",
                    lastName: "",
                    dateOfBirth: "",
                    email: "",
                    type: "Adult"
                }
            ];
        }
        for(let i = 0; i < child; i++) {
            passengers = [
                ...passengers ,
                {
                    title: "",
                    firstName: "",
                    lastName: "",
                    dateOfBirth: "",
                    email: "",
                    type: "Child"
                }
            ];
        }
        for(let i = 0; i < infant; i++) {
            passengers = [
                ...passengers ,
                {
                    title: "",
                    firstName: "",
                    lastName: "",
                    dateOfBirth: "",
                    email: "",
                    type: "Infant"
                }
            ];
        }
        return passengers;
    }
    const passengers = [...createPassengerList()];
    const navigate = useNavigateSearch();

    return (
    <Box sx={{
        ml: 7,
        mt: 5
    }}>
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <div>
                    <h1>Contact Details</h1>
                </div>
            </Grid>
            <Grid item xs={7.5}>
                <ContactDetail/>
            </Grid>
            <Grid item xs={2.5}>
                <FlightDisplay/>
            </Grid>
            <Grid item xs={2}>
                <BookingProcess currentState='booking'/>
            </Grid>
            {/* Traveler Details */}
            <Grid item xs={12} sx={{mt:-5}}>
                <div>
                    <h1>Traveler Details</h1>
                </div>
            </Grid>
            <Grid item xs={7.5}>
                <TravelerDetail passengers={passengers}/>
            </Grid>
            <Grid item xs={5} >
                <Box sx={{          
                    mb: 5,      
                    display: 'flex',
                    flexDirection: 'row',
                    alignItems: 'center',
                    justifyContent: 'center',
                    alignContent: 'center',
                }}>
                    <Button variant='outlined' onClick={() => navigate("/reviewbooking")}>Continue To Review</Button>
                </Box>
            </Grid>
        </Grid>
    </Box>
  );
};

export default Booking;