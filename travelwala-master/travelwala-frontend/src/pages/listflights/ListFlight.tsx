import React, { useEffect, useState } from "react";
import {
  FlightCriteria,
  SearchFlightResponse,
} from "../../api/flight/FlightService.types";
import FlightService from "../../api/flight/FlightService";
import { useLocation } from "react-router-dom";
import TableListFlights from "./components/tablelistflights/TableListFlights";
import { formatDate } from "../../utils/DateTimeUtils";
import { Button, Grid } from "@mui/material";
import FlightCart from "./components/flight-cart/FlightCart";
import FlightSearchBar from "./components/FlightSearchBar";
import useNavigateSearch from "../../hooks/useNavigateSearch";

function ListFlight() {
  const [data, setData] = useState<SearchFlightResponse>();

  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const navigate = useNavigateSearch();

  useEffect(() => {
    async function init() {
      const flightCriteria = await initCriteria();
      loadFlight(flightCriteria);
    }

    init();
  }, [location.search]);

  /**
   * Extract criteria from search URL
   *
   * <br>
   *
   * This function extracts the following query params:
   * <ul>
   *   <li>ap: contains 2 value for departure and arrival city, separated by a dot.
   *       For example, with <code>ap=Hanoi.Singapore</code>, departure city is Hanoi, and
   *       city is Singapore.
   *   </li>
   *   <li>dt: contains 2 value for departure and return date, separated by a dot.</li>
   *   <li>ps: contains 3 value for adult count, child count and infant count,
   *       separated by a dot.
   *    </li>
   *    <li>sc: contains value for seatClass.</li>
   * </ul>
   *
   * @return {FlightCriteria} an object that holds criteria to search for flights
   * @see FlightCriteria
   */
  const initCriteria = (): FlightCriteria => {
    const ap: string | null = queryParams.get("ap");
    const dt: string | null = queryParams.get("dt");
    const ps: string | null = queryParams.get("ps");
    const sc: string | null = queryParams.get("sc");

    const airports: string[] | null = ap ? ap.split(".") : null;
    const departureDate: string[] | null = dt ? dt.split(".") : null;
    const seatCount: string[] | null = ps ? ps.split(".") : null;

    return {
      departureCity: airports && airports[0] ? airports[0] : "",
      arrivalCity: airports && airports[1] ? airports[1] : "",
      departureDate:
        departureDate && departureDate[0]
          ? formatDate(new Date(departureDate[0]))
          : formatDate(new Date()),
      returnDate:
        departureDate && departureDate[1] && departureDate[1] !== "NA"
          ? formatDate(new Date(departureDate[1]))
          : null,
      adultCount: seatCount && seatCount[0] ? parseInt(seatCount[0]) : 0,
      childCount: seatCount && seatCount[1] ? parseInt(seatCount[1]) : 0,
      infantCount: seatCount && seatCount[2] ? parseInt(seatCount[2]) : 0,
      seatClass: sc ? sc : "Economic",
    };
  };

  const loadFlight = (criteria: FlightCriteria) => {
    FlightService.search(criteria)
      .then((response) => {
        if (response && response.data) {
          setData(response.data);
        }
      })
      .catch((reason) => console.log(reason));
  };

  return (
    <Grid container spacing={3} sx={{ padding: "32px 24px 16px 24px" }}>
      <Grid item sm={12}>
        <FlightSearchBar />
      </Grid>
      <Grid item sm={12} md={9}>
        <TableListFlights data={data} />
      </Grid>
      <Grid item sm={12} md={3}>
        <Grid container rowGap={3}>
          <Grid item sm={12}>
            <FlightCart />
          </Grid>
          <Grid item textAlign="end" sm={12}>
            <Button
              sx={{
                color: "#FFF",
                backgroundColor: "#508BFF",
                height: "42px",
                ":hover": {
                  backgroundColor: "#578df7",
                },
              }}
              onClick={() => navigate("/booking")}
            >
              Continue booking
            </Button>
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
}

export default ListFlight;
