import React from 'react';
import RemoveIcon from '@mui/icons-material/Remove';
import AddIcon from '@mui/icons-material/Add';
import {Divider, IconButton, Paper, Stack, styled} from "@mui/material";
import {useDispatch, useSelector}  from "react-redux";
import { passengerSelector } from "../../../redux/selectors";
import { addAdult, addChild, addInfant, removeAdult, removeChild, removeInfant } from "../../../redux/actions";

const Item = styled(Paper)(({theme}) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff', ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
  marginTop: 15
}));

const StackDisplay = ({...props}) => {
  return (
    <Stack direction="row" spacing={2}>
      <div style={{width: "150px"}}>
        <h3 style={{marginBottom: "-5px"}}>{props.title}</h3>
        {props.description}
      </div>
      <div>
        <Stack
          direction="row"
          divider={<Divider orientation="vertical" flexItem/>}
          spacing={2}
          sx={{marginTop: 2, marginLeft: 3}}
        >
          <IconButton color="secondary" component="span" onClick={() => {
            props.decrease(props.title)
          }}>
            <RemoveIcon/>
          </IconButton>
          <Item>{props.value}</Item>
          <IconButton color="secondary" component="span" onClick={() => {
            props.increase(props.title)
          }}>
            <AddIcon/>
          </IconButton>
        </Stack>
      </div>
    </Stack>
  )
}

function Passengers() {
  const dispatch = useDispatch();
  const {adult, child, infant} = useSelector(passengerSelector);
  const handleIncrease = (type) => {
    switch (type) {
      case "Adult":
        dispatch(addAdult())
        break
      case "Child":
        dispatch(addChild())
        break
      case "Infant":
        dispatch(addInfant())
        break
      default:
        break
    }
  }

  const handleDecrease = (type) => {
    switch (type) {
      case "Adult":
        dispatch(removeAdult())
        break
      case "Child":
        dispatch(removeChild())
        break
      case "Infant":
        dispatch(removeInfant())
        break
      default:
        break
    }
  }

  const passengers = [{
    title: "Adult", value: adult, description: "(age 12 and over)"
  }, {
    title: "Child", value: child, description: "(Age 2 - 11)"
  }, {
    title: "Infant", value: infant, description: "(below age 2)"
  }]
  return (<div id="passengers" style={{position: "relative"}}>
    {passengers.map((passenger, index) => {
      return <StackDisplay key={index} increase={(type) => {
        handleIncrease(type)
      }} decrease={(type) => {
        handleDecrease(type)
      }} {...passenger}
      />
    })}
  </div>);
}

export default Passengers;