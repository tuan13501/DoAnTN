export interface BookingRequest {
    createBookingFlightSpecs: CreateBookingFlightSpecs
    bookingContact: BookingContact;
  }

interface CreateBookingFlightSpecs {
    travellerSpecs: TravellerSpecs
    flightProductSpecs: FlightProductSpecs
}

interface FlightProductSpecs {
    departureFlightId: string
    returnFlightId: string
    seatClass: string
    adultCount: number
    childCount: number
    infantCount: number
}

interface TravellerSpecs {
    adultFormData: TravelerDataForm[]
    childFormData: TravelerDataForm[]
    infantFormData: TravelerDataForm[]
}

interface TravelerDataForm {
    title: string
    firstName: string
    lastName: string
    dob: DateOfBirth
    nationality: string
}

interface DateOfBirth {
    day: number
    month: number
    year: number
}

interface BookingContact {
    firstName: string
    lastName: string
    phonenumber: string
    email: string
}

export interface CreateBookingResponse {
    bookingId: string;
    bookingContact: BookingContactResponse
    adultTickets: TicketForm[]
    childTickets: TicketForm[]
    infantTickets: TicketForm[]
    invoiceId: string
}
interface BookingContactResponse {
    id: string
    firstName: string
    lastName: string
    phonenumber: string
    email: string
}

interface TicketForm {
    id: string
    type: string
    passenger: PassengerResponse
    seatClass: string
    amount: number
    flightId: string
}

interface PassengerResponse {
    id: string
    title: string
    firstName: string
    lastName: string
    dob: string
    nationality: string
}
