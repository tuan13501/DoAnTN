import SwitchSearch from "./components/SwitchSearch";
import backgroundImage from "../../assets/sunset-beach.jpg";
import { Box, Typography } from "@mui/material";
import { NewsLetter } from "../../components/NewsLetter";

function Home() {
  return (
    <Box
      style={{
        position: "static",
        display: "flex",
        flexDirection: "column",
      }}
    >
      <img
        src={backgroundImage}
        style={{ width: "100%" }}
        alt="background-home"
      />
      <Typography
        fontSize="60px"
        fontWeight={400}
        textAlign="center"
        color="#3B505A"
        margin="40px 0px 40px"
      >
        BOOK YOUR TRAVEL
      </Typography>
      <SwitchSearch id="search_box" />
      <Box style={{ marginTop: "193px" }}>
        <NewsLetter />
      </Box>
    </Box>
  );
}

export default Home;
