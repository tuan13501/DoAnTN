import * as React from 'react';
import Timeline from '@mui/lab/Timeline';
import TimelineItem from '@mui/lab/TimelineItem';
import TimelineSeparator from '@mui/lab/TimelineSeparator';
import TimelineConnector from '@mui/lab/TimelineConnector';
import TimelineContent from '@mui/lab/TimelineContent';
import TimelineDot from '@mui/lab/TimelineDot';

export default function BookingProcess(props) {
  const [bookingState, setBookingState] = React.useState('grey');
  const [reviewState, setReviewState] = React.useState('grey');
  const [paymentState, setPaymentState] = React.useState('grey');


  React.useEffect(() => {
    switch (props.currentState)  {
      case 'booking': 
        setBookingState('primary');
        break;
      case 'review':
        setReviewState('primary');
        break;
      case 'payment':
        setPaymentState('primary');
        break;
      default:
        break;
    }
  }, [props.currentState]);


  return (
    <Timeline>
      <TimelineItem>
        <TimelineSeparator>
          <TimelineDot color={bookingState}/>
          <TimelineConnector />
        </TimelineSeparator>
        <TimelineContent>Book</TimelineContent>
      </TimelineItem>
      <TimelineItem>
        <TimelineSeparator>
          <TimelineDot color={reviewState}/>
          <TimelineConnector />
        </TimelineSeparator>
        <TimelineContent>Review</TimelineContent>
      </TimelineItem>
      <TimelineItem>
        <TimelineSeparator>
          <TimelineDot color={paymentState}/>
        </TimelineSeparator>
        <TimelineContent>Payment</TimelineContent>
      </TimelineItem>
    </Timeline>
  );
}