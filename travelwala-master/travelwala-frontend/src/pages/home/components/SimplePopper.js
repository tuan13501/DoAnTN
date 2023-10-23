import * as React from 'react';
import Popper from '@mui/material/Popper';
import Passengers from "./Passengers";
import Box from "@mui/material/Box";
import {Button, ClickAwayListener} from "@mui/material";
import AirlineSeatReclineNormalIcon from '@mui/icons-material/AirlineSeatReclineNormal';
import WalaTextField from "../../../components/WalaTextField";
import { useSelector } from "react-redux";
import { passengerSelector } from "../../../redux/selectors";

export default function SimplePopper() {
  const [open, setOpen] = React.useState(false);
  const {adult, child, infant} = useSelector(passengerSelector);
  const label = adult.toString().concat(" Adult, ", child.toString(), " Child, ", infant.toString(), " Infant");
  let anchorElRef = React.useRef(null)

  const handleFocus = () => {
    setOpen(true)
  };

  const handleClickAway = () => {
    setOpen(false)
  }

  const id = open ? 'simple-popper' : undefined;

  return (<div>
    <h2>No. of Passengers</h2>
    <div ref={anchorElRef}>
      <WalaTextField
        aria-describedby={id}
        onFocus={handleFocus}
        label='Passengers'
        value={label}
        sx={{width: 400}}
        InputProps={{
          readOnly: true,
        }}
        iconStart={<AirlineSeatReclineNormalIcon sx={{color: "#2196f3", fontSize: 25}}/>}
      />
    </div>

    <ClickAwayListener onClickAway={(e) => {
      if (!anchorElRef.current.contains(e.target)) {
        handleClickAway()
      }
    }}>
      <Popper id={id} open={open} anchorEl={anchorElRef.current} sx={{zIndex: 'modal'}}>
        <Box sx={{border: 1, p: 1, bgcolor: 'white', marginTop: 2, width: 380}}>
          <Passengers />
          <Button color='success' sx={{marginLeft: 39}} onClick={handleClickAway}>Done</Button>
        </Box>
      </Popper>
    </ClickAwayListener>
  </div>);
}
