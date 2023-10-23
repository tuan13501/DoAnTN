import * as React from 'react'
import {forwardRef} from 'react'
import {MenuItem} from "@mui/material";
import Box from "@mui/material/Box";
import WalaTextField from "../../../components/WalaTextField";
import { useDispatch, useSelector } from "react-redux";
import { airportSelector } from "../../../redux/selectors";
import { setFromAirport, setToAirport } from "../../../redux/actions";

function LocationBox(props, ref) {
  const {from, to} = useSelector(airportSelector);
  const dispatch = useDispatch();
  const handleChange = (event) => {
    if(props.title === "From"){
      dispatch(setFromAirport(event.target.value));
    }else {
      dispatch(setToAirport(event.target.value));
    }
  };
  return (<div>
      <h2 style={{marginBottom: 10}}>{props.title}</h2>
      <Box
        ref={ref}
        component="form"
        sx={{
          '& .MuiTextField-root': {m: 1, width: '300px'},
        }}
        noValidate
        autoComplete="off"
      >
        <WalaTextField
          id={props.title}
          select
          label="City"
          value={props.title === "From" ? from : to }
          onChange={handleChange}
          iconStart={props.iconStart}
        >
          {airports.map((option) => (
            <MenuItem key={option.label} value={option.label}>
              {option.label}
            </MenuItem>
          ))}
        </WalaTextField>
      </Box>
    </div>
  );
}

const airports = [
  {label: 'Hanoi'},
  {label: 'Ho Chi Minh City'},
  {label: 'Nha Trang'},
  {label: 'Da Nang'},
  {label: 'Hue'}
]

export default forwardRef(LocationBox)