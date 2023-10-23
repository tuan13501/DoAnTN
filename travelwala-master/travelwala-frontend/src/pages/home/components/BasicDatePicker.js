import * as React from 'react';
import TextField from '@mui/material/TextField';
import {AdapterDateFns} from '@mui/x-date-pickers/AdapterDateFns';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import {DatePicker} from '@mui/x-date-pickers/DatePicker';
import { useDispatch, useSelector } from "react-redux";
import { dateSelector } from "../../../redux/selectors";
import { setDepartureDate } from "../../../redux/actions";


export default function BasicDatePicker() {
  const {departureDate} = useSelector(dateSelector);
  const dispatch = useDispatch();

  return (<LocalizationProvider dateAdapter={AdapterDateFns}>
    <div>
      <DatePicker
        disablePast
        value={departureDate}
        onChange={(newValue) => {
          dispatch(setDepartureDate(newValue));
        }}
        renderInput={(params) => <TextField {...params} />}
      />
    </div>
  </LocalizationProvider>);
}