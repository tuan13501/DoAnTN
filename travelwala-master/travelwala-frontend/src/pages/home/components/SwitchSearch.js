import React, { useState } from "react";
import Paper from "@mui/material/Paper";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import FlightSearchBox from "./FlightSearchBox";

const SignInOutContainer = () => {
  const [value, setValue] = useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const paperStyle = {};

  function TabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`simple-tabpanel-${index}`}
        aria-labelledby={`simple-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    );
  }

  return (
    <Paper elevation={20} style={paperStyle}>
      <Tabs
        value={value}
        indicatorColor="primary"
        textColor="primary"
        onChange={handleChange}
        aria-label="disabled tabs example"
        centered
      >
        <Tab label="Flights" />
        <Tab label="Hotels" />
        <Tab label="Combo" />
      </Tabs>
      <TabPanel value={value} index={0}>
        <FlightSearchBox handleChange={handleChange} />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <Typography>Under Maintenance</Typography>
      </TabPanel>
      <TabPanel value={value} index={2}>
        <Typography>Under Maintenance</Typography>
      </TabPanel>
    </Paper>
  );
};

export default SignInOutContainer;
