import { CreateBookingResponse, BookingRequest } from "./BookingService.types";
import axios, { AxiosResponse } from "axios";
import { axiosConfig } from "../config";

class BookingService {
  static async requestBookingInvoice(
    bookingRequest: BookingRequest
  ): Promise<AxiosResponse<CreateBookingResponse>> {
    return axios.post(
      `/bookings`,
      bookingRequest,
      { ...axiosConfig() }
    );
  }
}

export default BookingService;
