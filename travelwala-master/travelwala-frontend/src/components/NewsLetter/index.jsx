import React from "react";
import styled from "styled-components";
import { Details } from "./details";
import { SideImage } from "./sideImage";

const CardContainer = styled.div`
  width: 1100px;
  height: 500px;
  display: flex;
  position: relative;
  border: 10px solid #fff;
  background-color: rgba(231,232,229,255);
  backdrop-filter: blur(10px);
`;
const NewsLetterContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgb(255, 255, 255);
  background: linear-gradient(
    50deg,
    rgba(255, 255, 255, 1) 100%,
    rgba(255, 255, 255, 1) 100%
  );
`;
export function NewsLetter(props) {
  return (
      <NewsLetterContainer>
          <CardContainer>
              <Details />
              <SideImage />
          </CardContainer>
      </NewsLetterContainer>

  );
}
