import React, { useMemo } from "react";
import { FlightResponse } from "../../../../../api/flight/FlightService.types";
import { Box, Button, TableCell, TableRow, Typography } from "@mui/material";
import {
  convertDateToAmPm,
  hourMinuteDiff,
} from "../../../../../utils/DateTimeUtils";
import DefaultImage from "../../../../../components/SVG/DefaultImage";
import { useSelector } from "react-redux";
import { RootState } from "../../../../../redux/store";

type FlightItemProps = {
  flight: FlightResponse;
  onChoose: (f: FlightResponse) => void;
};

function FlightItem(props: FlightItemProps) {
  const { flight, onChoose } = props;

  const seatClass = useSelector(
    (state: RootState) => (state.criteria as any).seatClass
  );

  const CellImage = useMemo(() => {
    return (
      <TableCell align="center" style={{ width: "60px" }}>
        {flight.airline.imageLink ? (
          <Box
            component="img"
            sx={{
              height: 64,
              width: 64,
            }}
            src={flight.airline.imageLink}
          />
        ) : (
          <DefaultImage style={{ height: "40px", width: "40px" }} />
        )}
      </TableCell>
    );
  }, [flight.airline.imageLink]);

  const CellAgencyName = useMemo(() => {
    return (
      <TableCell
        align="left"
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "flex-start",
          // width: "",
        }}
      >
        <Typography>
          {hourMinuteDiff(
            new Date(flight.departureTime),
            new Date(flight.expectedArrivalTime)
          )}
        </Typography>
        <Typography>{flight.airline.name}</Typography>
      </TableCell>
    );
  }, [flight.departureTime, flight.expectedArrivalTime, flight.airline.name]);

  const CellFlightDuration = useMemo(() => {
    return (
      <TableCell align="right" style={{ width: "160px" }}>
        {convertDateToAmPm(new Date(flight.departureTime))}
        &nbsp; - &nbsp;
        {convertDateToAmPm(new Date(flight.expectedArrivalTime))}
      </TableCell>
    );
  }, [flight.departureTime, flight.expectedArrivalTime]);

  const CellPrice = useMemo(() => {
    return (
      <TableCell align="right" style={{ width: "150px" }}>
        {seatClass === "Economy"
          ? flight.adultEconomicPrice.toLocaleString()
          : flight.adultBusinessPrice.toLocaleString()}{" "}
        VND
      </TableCell>
    );
  }, [seatClass, flight.adultBusinessPrice, flight.adultEconomicPrice]);

  const CellBtnChoose = useMemo(() => {
    return (
      <TableCell align="right" style={{ width: "115px" }}>
        <Button
          sx={{
            background: "#FC6C1B",
            fontWeight: 700,
            width: "170px",
            height: "42px",
            ":hover": {
              backgroundColor: "#FC8643",
            },
          }}
          onClick={() => onChoose(flight)}
          variant="contained"
        >
          Choose
        </Button>
      </TableCell>
    );
  }, [flight, onChoose]);

  return (
    <TableRow>
      {CellImage}
      {CellAgencyName}
      {CellFlightDuration}
      {CellPrice}
      {CellBtnChoose}
    </TableRow>
  );
}

export default FlightItem;
