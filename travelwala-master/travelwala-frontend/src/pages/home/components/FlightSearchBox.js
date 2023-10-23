import * as React from "react";
import FlightTakeoffIcon from "@mui/icons-material/FlightTakeoff";
import FlightLandIcon from "@mui/icons-material/FlightLand";
import ChangeCircleIcon from "@mui/icons-material/ChangeCircle";
import FlightClassIcon from "@mui/icons-material/FlightClass";
import TravelExploreIcon from "@mui/icons-material/TravelExplore";
import { DateRangePicker } from "@mui/x-date-pickers-pro/DateRangePicker";
import {
  Button,
  Checkbox,
  FormControlLabel,
  IconButton,
  MenuItem,
  TextField,
} from "@mui/material";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import "./FlightSearchBox.css";
import BoxLocation from "./LocationBox";
import SimplePopper from "./SimplePopper";
import BasicDatePicker from "./BasicDatePicker";
import useNavigateSearch from "../../../hooks/useNavigateSearch";
import { useDispatch, useSelector } from "react-redux";
import {
  setDepartureDate,
  setFromAirport,
  setReturnDate,
  setRoundTrip,
  setSeatClass,
  setToAirport,
} from "../../../redux/actions";
import WalaTextField from "../../../components/WalaTextField";
import {
  dateSelector,
  roundTripSelector,
  seatClassSelector,
} from "../../../redux/selectors";

const seatClasses = [
  {
    label: "Economy",
  },
  {
    label: "Business",
  },
];

Date.prototype.addDays = function (days) {
  const date = new Date(this.valueOf());
  date.setDate(date.getDate() + days);
  return date;
};

function FlightSearchBox() {
  const roundTrip = useSelector(roundTripSelector);
  const { departureDate, returnDate } = useSelector(dateSelector);
  const value = [departureDate, returnDate];
  const seatClass = useSelector(seatClassSelector);
  const navigate = useNavigateSearch();
  const dispatch = useDispatch();

  const handleChange = (e) => {
    if (e.currentTarget.checked) {
      dispatch(setRoundTrip(true));
      dispatch(
        setReturnDate(departureDate.addDays(2))
      );
    } else {
      dispatch(setRoundTrip(false));
      dispatch(setReturnDate(null));
    }
  };

  const handleChooseSeatClass = (e) => {
    dispatch(setSeatClass(e.target.value));
  };

  const fromRef = React.useRef();
  const toRef = React.useRef();

  const handleSwap = () => {
    let fromInput = fromRef.current.querySelector("input");
    let toInput = toRef.current.querySelector("input");
    let fromDiv = fromRef.current.querySelector("#From");
    let toDiv = toRef.current.querySelector("#To");
    let buff = fromInput.value;
    fromInput.value = toInput.value;
    toInput.value = buff;
    buff = fromDiv.innerHTML;
    fromDiv.innerHTML = toDiv.innerHTML;
    toDiv.innerHTML = buff;
    dispatch(setFromAirport(fromDiv.innerHTML));
    dispatch(setToAirport(toDiv.innerHTML));
  };

  const getSearchParams = () => {
    let fromDiv = fromRef.current.querySelector("#From");
    let toDiv = toRef.current.querySelector("#To");
    const departureDate = document
      .getElementById("departure-date")
      .querySelector("input").value;
    const returnDate = roundTrip
      ? document.getElementById("return-date").querySelectorAll("input")[1]
          .value
      : "NA";
    const seatClass = document
      .getElementById("seat-class")
      .querySelector("input").value;
    const passengerLabelList = document
      .getElementById("passenger")
      .querySelector("input")
      .value.split(" ");
    return {
      ap: `${fromDiv.innerHTML}.${toDiv.innerHTML}`,
      dt: `${departureDate}.${returnDate}`,
      ps: `${passengerLabelList[0]}.${passengerLabelList[2]}.${passengerLabelList[4]}`,
      sc: seatClass,
    };
  };

  return (
    <div id="box">
      <div id="left-side">
        <div className="from">
          <BoxLocation
            ref={fromRef}
            title="From"
            iconStart={
              <FlightTakeoffIcon sx={{ color: "#2196f3", fontSize: 25 }} />
            }
          />
        </div>
        <div>
          <IconButton
            onClick={handleSwap}
            sx={{ marginLeft: 14, marginBottom: -4, marginTop: 2 }}
          >
            <ChangeCircleIcon sx={{ color: "#2196f3", fontSize: 70 }} />
          </IconButton>
        </div>
        <div>
          <BoxLocation
            ref={toRef}
            title="To"
            iconStart={
              <FlightLandIcon sx={{ color: "#2196f3", fontSize: 25 }} />
            }
          />
        </div>
      </div>
      <div id="center">
        <LocalizationProvider dateAdapter={AdapterDateFns}>
          <DateRangePicker
            disablePast
            calendars={2}
            PopperProps={{
              sx: { paddingTop: 22, paddingRight: 10 },
            }}
            value={value}
            onChange={(newValue) => {
              dispatch(setDepartureDate(newValue[0]));
              dispatch(setReturnDate(newValue[1]));
            }}
            renderInput={(startProps, endProps) => (
              <React.Fragment>
                <div id="departure-date">
                  <h2>Departure Date</h2>
                  {roundTrip ? (
                    <TextField {...startProps} />
                  ) : (
                    <BasicDatePicker />
                  )}
                </div>
                <div id="return-date">
                  <FormControlLabel
                    className="CheckBox"
                    value="end"
                    control={
                      <Checkbox
                        checked = {roundTrip}
                        onChange={(e) => {
                          handleChange(e);
                        }}
                      />
                    }
                    label={<h2>Return Date</h2>}
                    labelPlacement="end"
                  />
                  <br />
                  {roundTrip && <TextField {...endProps} />}
                </div>
              </React.Fragment>
            )}
          />
        </LocalizationProvider>
      </div>
      <div id="right-side">
        <div id="passenger">
          <SimplePopper></SimplePopper>
        </div>
        <h2 style={{ marginTop: 100 }}>Seat Class</h2>
        <div id="seat-class">
          <WalaTextField
            select
            label="SeatClass"
            value={seatClass}
            onChange={handleChooseSeatClass}
            iconStart={
              <FlightClassIcon sx={{ color: "#2196f3", fontSize: 25 }} />
            }
          >
            {seatClasses.map((option) => (
              <MenuItem key={option.label} value={option.label}>
                {option.label}
              </MenuItem>
            ))}
          </WalaTextField>
        </div>
        <Button
          variant="contained"
          startIcon={<TravelExploreIcon />}
          sx={{ height: 50, marginTop: 5, marginLeft: 27 }}
          onClick={() => {
            navigate("/flight/search", getSearchParams());
          }}
        >
          Search flights
        </Button>
      </div>
    </div>
  );
}

export default FlightSearchBox;
