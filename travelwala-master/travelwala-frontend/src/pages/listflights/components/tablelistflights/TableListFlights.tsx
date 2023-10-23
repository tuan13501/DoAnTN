import React, { useState } from "react";
import {
  FlightResponse,
  SearchFlightResponse,
} from "../../../../api/flight/FlightService.types";
import { Tab, Table, TableBody, TableContainer, Tabs } from "@mui/material";
import FlightItem from "./FlightItem/FlightItem";
import { BoxTableListFlight } from "./TableListFlight.styles";
import { useDispatch, useSelector } from "react-redux";
import {
  chooseDepartureFlightForBooking,
  chooseReturnFlightForBooking,
} from "../../../../redux/booking/BookingSlice";
import { RootState } from "../../../../redux/store";

type TableListFlightsProps = {
  data?: SearchFlightResponse;
};

function TableListFlights(props: TableListFlightsProps) {
  const { data } = props;

  const dispatch = useDispatch();
  const returnDate = useSelector(
    (state: RootState) => (state.criteria as any).date.returnDate
  );
  const willReturn: boolean = returnDate !== null;

  const [tabValue, setTabValue] = useState(0);

  const handelTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setTabValue(newValue);
  };

  const handleChooseDepartureFlight = (flight: FlightResponse): void => {
    dispatch(chooseDepartureFlightForBooking(flight));
  };

  const handleChooseReturnFlight = (flight: FlightResponse): void => {
    dispatch(chooseReturnFlightForBooking(flight));
  };

  return (
    <BoxTableListFlight
      sx={{
        boxShadow: 3,
      }}
    >
      <Tabs value={tabValue} onChange={handelTabChange}>
        <Tab label="Departure Flight" />
        {willReturn && <Tab label="Return Flight" />}
      </Tabs>
      <TableContainer
        sx={{
          margin: "0px 16px 8px",
          width: "calc(100% - 30px)",
          height: "100%",
          "&::-webkit-scrollbar": {
            width: 10,
          },
          "&::-webkit-scrollbar-track": {
            backgroundColor: "#fff",
          },
          "&::-webkit-scrollbar-thumb": {
            background: "#888",
            borderRadius: 10,
            border: "2px solid transparent",
            backgroundClip: "content-box",
            "&:hover": {
              backgroundColor: "#555",
            },
          },
        }}
      >
        <Table aria-label="simple table" style={{ borderCollapse: "inherit" }}>
          <TableBody>
            {tabValue === 0 &&
              data?.departureFlights?.map((flight) => (
                <FlightItem
                  flight={flight}
                  onChoose={handleChooseDepartureFlight}
                />
              ))}
            {tabValue === 1 &&
              willReturn &&
              data?.returnFlights?.map((flight) => (
                <FlightItem
                  flight={flight}
                  onChoose={handleChooseReturnFlight}
                />
              ))}
          </TableBody>
        </Table>
      </TableContainer>
    </BoxTableListFlight>
  );
}

export default TableListFlights;
