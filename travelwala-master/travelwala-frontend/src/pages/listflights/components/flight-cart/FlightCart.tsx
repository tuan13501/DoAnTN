import React, { Fragment } from "react";
import { Box, Divider } from "@mui/material";
import FlightDetail from "./flight-detail/FlightDetail";
import { useSelector } from "react-redux";
import { RootState } from "../../../../redux/store";
import { FlightResponse } from "../../../../api/flight/FlightService.types";

function FlightCart() {
  const roundTrip: boolean = useSelector(
    (state: RootState) => (state.criteria as any).roundTrip
  );
  const airport = useSelector(
    (state: RootState) => (state.criteria as any).airport
  );
  const date = useSelector((state: RootState) => (state.criteria as any).date);
  const departureFlight: FlightResponse = useSelector(
    (state: RootState) => state.booking.departureFlight
  );
  const returnFlight: FlightResponse | undefined = useSelector(
    (state: RootState) => state.booking.returnFlight
  );

  return (
    <Box
      sx={{
        padding: "16px",
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-start",
        gap: "8px",
        border: "1px solid #E9E8FC",
        borderRadius: "12px",
      }}
    >
      <FlightDetail
        title="Departure"
        departureDate={date.departureDate}
        departureCity={airport.from}
        arrivalCity={airport.to}
        flight={departureFlight}
        index={1}
      />
      {roundTrip && returnFlight && (
        <Fragment>
          <Divider />
          <FlightDetail
            title="Return"
            departureDate={date.returnDate}
            departureCity={airport.to}
            arrivalCity={airport.from}
            flight={returnFlight}
            index={2}
          />
        </Fragment>
      )}
    </Box>
  );
}

export default FlightCart;
