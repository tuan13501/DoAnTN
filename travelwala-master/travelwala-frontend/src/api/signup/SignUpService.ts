import { UsernamePasswordRegister, UsernamePasswordRegisterResponse } from "./SignUpService.types";
import axios, { AxiosResponse } from "axios";
import { axiosConfig } from "../config";

class SignInService {
  static async register(
    usernamePasswordRegister: UsernamePasswordRegister
  ): Promise<AxiosResponse<UsernamePasswordRegisterResponse>> {
    return axios.post(`/register`, usernamePasswordRegister, {
      ...axiosConfig()
    });
  }
}

export default SignInService;