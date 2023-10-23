import React, { Fragment } from "react";
import { Box, Typography } from "@mui/material";
import DefaultImage from "../../../../../components/SVG/DefaultImage";
import {
  convertDateToAmPm,
  hourMinuteDiff,
} from "../../../../../utils/DateTimeUtils";
import { FlightResponse } from "../../../../../api/flight/FlightService.types";

type FlightDetailProps = {
  title: string;
  departureDate: string;
  departureCity: string;
  arrivalCity: string;
  flight?: FlightResponse;
  index: number;
};

function FlightDetail(props: FlightDetailProps) {
  const { title, departureDate, departureCity, arrivalCity, flight, index } =
    props;

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: "24px",
      }}
    >
      {/* Title of the box */}
      <Typography variant="h5">{title}</Typography>

      {/* Departure date + departure city + arrival city */}
      <Box
        sx={{
          display: "flex",
          flexDirection: "row",
          alignItems: "center",
          justifyContent: "flex-start",
        }}
      >
        <Box
          sx={{
            width: "20px",
            height: "24px",
            padding: "4px 4px 4px 8px",
            margin: "0px 16px 0px 0px",
            color: "white",
            fontSize: "24px",
            backgroundColor: "#0194f3",
            borderRadius: "9999px",
          }}
        >
          {index}
        </Box>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            rowGap: "8px",
          }}
        >
          {new Date(departureDate).toDateString()}
          <Typography variant="h6" sx={{ fontWeight: 700 }}>
            {departureCity} → {arrivalCity}
          </Typography>
        </Box>
      </Box>

      {flight && flight.airline && (
        <Fragment>
          {/* Airline */}
          <Box
            sx={{ display: "flex", alignItems: "center", columnGap: "16px" }}
          >
            {flight.airline.imageLink ? (
              <Box
                component="img"
                sx={{
                  height: 48,
                  width: 48,
                }}
                src={flight.airline.imageLink}
              />
            ) : (
              <DefaultImage style={{ height: "48px", width: "48px" }} />
            )}
            <Typography variant="h6">{flight.airline.name}</Typography>
          </Box>

          {/* Flight detail information */}
          <Box sx={{ display: "flex", flexDirection: "column" }}>
            <Typography>
              {hourMinuteDiff(
                new Date(flight.departureTime),
                new Date(flight.expectedArrivalTime)
              )}
            </Typography>
            <Typography>
              {convertDateToAmPm(new Date(flight.departureTime))}
              &nbsp; - &nbsp;
              {convertDateToAmPm(new Date(flight.expectedArrivalTime))}
            </Typography>
            <Typography>
              {flight.departureAirport}&nbsp; - &nbsp;{flight.arrivalAirport}
            </Typography>
          </Box>
        </Fragment>
      )}
    </Box>
  );
}

export default FlightDetail;
