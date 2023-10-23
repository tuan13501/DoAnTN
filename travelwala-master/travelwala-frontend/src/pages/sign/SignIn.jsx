import React from "react";
import { Grid } from "@mui/material";
import SignInComponent from "./components/SignInComponent";
import BookNow from "./components/BookNow";
import "./BookNowBackground.css";

const SignIn = () => {
  return (
    <Grid container>
      <Grid item xs={8} id="booknow-bg">
        <BookNow />
      </Grid>
      <Grid item xs={4}>
        <SignInComponent />
      </Grid>
    </Grid>
  );
};

export default SignIn;