import { UsernamePassword, SignInResponse } from "./SignInService.types";
import axios, { AxiosResponse } from "axios";
import { axiosConfig } from "../config";

class SignInService {
  static async login(
    usernamePassword: UsernamePassword
  ): Promise<AxiosResponse<SignInResponse>> {
    return axios.post(`/login`, usernamePassword, {
      ...axiosConfig()
    });
  };

  static async loginGoogle(
  ): Promise<AxiosResponse<any>> {
    return axios.get(`https://travelwala-backend.herokuapp.com/oauth2/authorization/google`)
  }
}

export default SignInService;