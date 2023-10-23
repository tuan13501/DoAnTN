import { CreatePaymentResponse, PaymentRequest } from "./PaymentService.types";
import axios, { AxiosResponse } from "axios";
import { axiosConfig } from "../config";

class PaymentService {
  static async payByStripe(
    paymentRequest: PaymentRequest
  ): Promise<AxiosResponse<CreatePaymentResponse>> {
    return axios.post(
      `/payments/stripe/create-payment-intent`,
      paymentRequest,
      { ...axiosConfig() }
    );
  }
}

export default PaymentService;
