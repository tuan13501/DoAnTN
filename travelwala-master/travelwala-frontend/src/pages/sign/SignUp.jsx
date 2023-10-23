import React from "react";
import { Grid } from "@mui/material";
import SignUpComponent from "./components/SignUpComponent";
import BookNow from "./components/BookNow";
import "./BookNowBackground.css";

const SignUp = () => {
  return (
    <Grid container>
      <Grid item xs={8} id="booknow-bg">
        <BookNow />
      </Grid>
      <Grid item xs={4}>
        <SignUpComponent />
      </Grid>
    </Grid>
  );
};

export default SignUp;