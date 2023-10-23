import * as React from 'react';
import * as ThemeStyle from './css/style'
import Box from '@mui/material/Box';
import { Container } from '@mui/system';
import { Divider, Grid, Typography } from '@mui/material';
import BookingService from "../../../api/booking/BookingService";
import { passengerSelector } from "../../../redux/selectors";
import { passengerListSelector, departureFlightSelector, returnFlightSelector, contactInfoSelector } from "../../../redux/selectors";
import {useSelector}  from "react-redux";

export default function PriceDetail() {
    const [priceDetail, setPriceDetail] = React.useState({total: 0, details: []});

    const passengerList = useSelector(passengerListSelector);
    const departureFlight = useSelector(departureFlightSelector);
    const returnFlight = useSelector(returnFlightSelector);
    const contactDetail = useSelector(contactInfoSelector);
    const {adult, child, infant} = useSelector(passengerSelector);
    
    const typoStyle = {
        pb: 1,
        fontSize: '20px'
    }

    const loadInvoice = (bookingData) => {
        BookingService.requestBookingInvoice(bookingData)
            .then((response) => {
                if (response && response.data) {
                    return response.data
                }
            }).then((data) => createInvoice(data))
            .catch((reason) => console.log(reason));
    };

    const createInvoice = (data) => {
        var total = 0;
        var details = [];
        console.log("data");
        console.log(data);
        if(data.adultTickets.length > 0 ) {
            for(let i in data.adultTickets) {
                if(details.filter((e) => e.type === "Adult").find((e) => (e.name === data.adultTickets[i].flightId && e.type === "Adult")) === undefined) {
                    const amount = data.adultTickets.filter((e) => e.flightId === data.adultTickets[i].flightId).length;
                    details.push({
                        name: data.adultTickets[i].flightId,
                        amount: amount,
                        type: "Adult",
                        price: Math.round(data.adultTickets[i].amount)
                    });
                    total += amount * Math.round(data.adultTickets[i].amount);
                }
            }
        }
        if(data.childTickets.length > 0 ) {
            for(let i in data.childTickets) {
                if(details.filter((e) => e.type === "Child").find((e) => e.name === data.childTickets[i].flightId)  === undefined) {
                    const amount = data.childTickets.filter((e) => e.flightId === data.childTickets[i].flightId).length;
                    details.push({
                        name: data.childTickets[i].flightId,
                        amount: amount,
                        type: "Child",
                        price: Math.round(data.childTickets[i].amount)
                    });
                    total += amount * Math.round(data.childTickets[i].amount);
                }
            }
            console.log("processing");
            console.log(details);
        }
        if(data.infantTickets.length > 0 ) {
            for(let i in data.infantTickets) {
                if(details.filter((e) => e.type === "Infant").find((e) => (e.name === data.infantTickets[i].flightId && e.type === "Infant"))  === undefined) {
                    const amount = data.infantTickets.filter((e) => e.flightId === data.infantTickets[i].flightId).length;
                    details.push({
                        name: data.infantTickets[i].flightId,
                        amount: amount,
                        type: "Infant",
                        price: Math.round(data.infantTickets[i].amount)
                    });
                    total += amount * Math.round(data.infantTickets[i].amount);
                }
            }
        }
        setPriceDetail({total: total, details: details});
        console.log(priceDetail);
    };

    React.useEffect(() => {
        async function init() {
            console.log("start point");
            const bookingData = await initCriteria();
            await loadInvoice(bookingData);
        }
        init();
    }, []);

    const initCriteria = () => {
        // Recreate the field dob for matching format
        const tempPassengerList = structuredClone(passengerList);
        tempPassengerList.map((e) => {
            e.dob = 
            {
                year: e.dateOfBirth.split("/")[2],
                month: e.dateOfBirth.split("/")[1],
                day: e.dateOfBirth.split("/")[0]
            };
            e.nationality = "vietnam";
        }
        );
        return {
            createBookingFlightSpecs: {
                travellerSpecs: {
                    adultFormData: tempPassengerList.filter( (e) => e.type == "Adult" ),
                    childFormData: tempPassengerList.filter( (e) => e.type == "Child" ),
                    infantFormData: tempPassengerList.filter( (e) => e.type == "Infant" )
                },
                flightProductSpecs: {
                    departureFlightId: departureFlight.id,
                    returnFlightId: returnFlight.id ? returnFlight.id : null,
                    seatClass: departureFlight.occupiedEconomicSeats == 0 ? "bussiness" : "Economy",
                    adultCount: adult,
                    childCount: child,
                    infantCount: infant
                }
            },
            bookingContact: {
                firstName: contactDetail.firstName,
                lastName: contactDetail.lastName,
                phoneNumber: contactDetail.phoneNumber,
                email: contactDetail.email
            }
        };
    };

    return (
            <Box
                component="form"
                sx={{
                '& .MuiTextField-root': { m: 1, width: '35ch' },
                border: 1,
                flexWrap: 'wrap',
                display: 'inline-flex',
                alignContent: 'center',
                pb: 1,
                mb: 5,
                justifyContent: 'center',
                width: 700,
                ...ThemeStyle.box
                }}
                noValidate
                autoComplete="off"
            >   
                <Container>
                    <div>
                        <Box
                            sx={{          
                                py:2,   
                                px: 0,               
                                display: 'flex',
                                flexDirection: 'row',
                                justifyContent: 'space-between',
                                lineHeight: 0
                            }}
                        >
                            <h3>Price you pay</h3>
                            <h3>Total {priceDetail.total} VND</h3>
                        </Box>
                    </div>
                    <Divider/>
                    <div>
                        <Box sx={{width: '100%', mt: 2}}>
                            <Grid container columnSpacing={20}>
                            {
                                priceDetail.details.map((e) => 
                                    <>            
                                    <Grid item xs={12} sx={{mb: 2}}>
                                        <Box
                                            sx={{
                                                display:'flex',
                                                alignItems: 'flex-start',
                                                flexDirection:'row',
                                                justifyContent: 'space-between',
                                                lineHeight: 0,
                                                pr: 0,
                                            }}>
                                            <item>
                                                <Typography sx={{...typoStyle}}>{e.type + (e.amount > 0 ? " x" +e.amount : "")}</Typography>
                                            </item>
                                                <Typography sx={{...typoStyle}}>{e.price} VND</Typography>
                                        </Box>
                                    </Grid>
                                    </>
                                )
                            }
                            </Grid>
                        </Box>
                    </div>
                </Container>
            </Box>
    );
}
