export interface PaymentRequest {
  amount: number;
  currency: string;
}

export interface CreatePaymentResponse {
  amount: number;
  currency: string;
  clientSecret: string;
}
