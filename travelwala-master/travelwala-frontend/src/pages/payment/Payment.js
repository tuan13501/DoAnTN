import React, { useState, useEffect } from "react";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";

import CheckoutForm from "./CheckoutForm";
import "./CheckoutForm.css";
import PaymentService from "../../api/payment/PaymentService";

// Make sure to call loadStripe outside of a component’s render to avoid
// recreating the Stripe object on every render.
// This is a public sample test API key.
// Don’t submit any personally identifiable information in requests made with this key.
// Sign in to see your own test API key embedded in code samples.
const stripePromise = loadStripe(
  "pk_test_51LIPOjC6tixmsEI6uISgmXlWe8ljcX0Zpiir3WxoLcGsSYkB23pr0kC21TorCJxkPH0CuFxWt5ncaungmjRkW7Yq00WpwSDvNi"
);

export default function Payment() {
  const [clientSecret, setClientSecret] = useState("");

  useEffect(() => {
    // Create PaymentIntent as soon as the page loads
    PaymentService.payByStripe({
      amount: 100,
      currency: "usd",
    })
      .then((res) => setClientSecret(res.data.clientSecret))
      .catch((error) => console.log(error));
  }, []);

  const appearance = {
    theme: "stripe",
  };
  const options = {
    clientSecret,
    appearance,
  };

  return (
    <div className="Payment">
      {clientSecret && (
        <Elements options={options} stripe={stripePromise}>
          <CheckoutForm />
        </Elements>
      )}
    </div>
  );
}
