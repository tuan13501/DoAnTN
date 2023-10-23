import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import useNavigateSearch from "../hooks/useNavigateSearch";

const OAuthSuccess = () => {
  const navigate = useNavigateSearch();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  useEffect(() => {
    const accessToken = queryParams.get("accessToken");
    const refreshToken = queryParams.get("refreshToken");
    const username = queryParams.get("username");
    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);
    localStorage.setItem("username", username);
    navigate("/");
  }, []);

  return (
    <div>
      success
    </div>
  );
};

export default OAuthSuccess;