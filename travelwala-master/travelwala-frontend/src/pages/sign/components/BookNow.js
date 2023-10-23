import React from "react";
import { Button } from "@mui/material";

const BookNow = () => {
  return (
    <div style={{
      textAlign: "center",
      fontFamily: "Monospace",
      fontSize: "26px",
      color: "white",
      backgroundColor: "#33363450",
      margin: "140px",
      padding: "10px 30px 45px",
      borderRadius: "40px",
      backdropFilter: "blur(8px)"
    }}>
      <h1>
        Do it wala
      </h1>
      <h1>
        And travel with us
      </h1>
      <Button
        href="/#search_box"
        variant="outlined"
        size="large"
        sx={[{ color: "white", borderColor: "white", fontSize: "24px" }, {
          "&:hover": {
            backgroundColor: "gray",
            borderColor: "#C7BDB03D"
          }
        }]}>
        Book Now
      </Button>
    </div>
  );
};

export default BookNow;