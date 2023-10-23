import React from "react";
import styled from "styled-components";

const DetailsContainer = styled.div`
  height: 100%;
  display: flex;
  flex-direction: column;
  flex: 1;
  align-items: center;
  justify-content: center;
`;

const InnerContainer = styled.div`
  display: flex;
  flex-direction: column;
  max-width: 80%;
`;

const Header = styled.h1`
  margin: 0;
  color: #527c9e;
  font-weight: 700;
  font-size: 45px;
`;

const SubHeader = styled.h3`
  margin: 10px 0;
  color: #000;
  font-weight: 700;
  font-size: 24px;
`;

const Text = styled.p`
  color: #000;
  font-weight: 500;
  font-size: 20px;
`;

const FormGroup = styled.div`
  display: flex;
  position: relative;
  height: 53px;
  margin-top: 1em;
`;

const EmailInput = styled.input`
  outline: none;
  border: none;
  background-color: #fff;
  padding-left: 1.5em;
  padding-right: 3em;
  border-radius: 17px;
  font-size: 20px;
  color: #7b786b;
  height: 100%;

  &::placeholder {
    color: #7b786b;
  }
`;

const SubscribeButton = styled.button`
  position: absolute;
  right: -10px;
  top: 0;
  height: 100%;
  border: none;
  outline: none;
  color: #fff;
  background-color: #080404;
  font-size: 20px;
  font-weight: 500;
  cursor: pointer;
  transition: all 50ms ease-in-out;
  border-bottom-left-radius: 16px;
  border-bottom-right-radius: 16px;
  border-top-right-radius: 16px;
  padding: 0 10px;

  &:hover {
    background-color: #527c9e;
  }
`;

export function Details(props) {
  return (
    <DetailsContainer>
      <InnerContainer>
        <Header>JOIN OUR LIST</Header>
        <Text>
          Subscribe to get latest product information, promotional news and more!
        </Text>
        <FormGroup>
          <EmailInput className = "emailInput" type="text" placeholder="example@email.com" />
          <SubscribeButton onClick = {() => {
            document.querySelector(".emailInput").value = "";
          }}>Subscribe</SubscribeButton>
        </FormGroup>
      </InnerContainer>
    </DetailsContainer>
  );
}
