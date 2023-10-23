import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { Provider } from "react-redux";
import storeProvider from "./redux/store";
import { PersistGate } from "redux-persist/integration/react";
import { ThemeProvider } from "@mui/material/styles";
import WalaTheme from "./styles/theme";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <ThemeProvider theme={WalaTheme}>
    <Provider store={storeProvider.store}>
      <PersistGate loading={null} persistor={storeProvider.persistor}>
        <App />
      </PersistGate>
    </Provider>
  </ThemeProvider>
);
