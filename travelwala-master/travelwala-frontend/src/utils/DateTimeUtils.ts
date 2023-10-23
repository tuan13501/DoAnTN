import moment from "moment";
import { Moment } from "moment/moment";

function padTo2Digits(num: number) {
  return num.toString().padStart(2, "0");
}

export function convertDateToAmPm(date: Date): string {
  return date.toLocaleTimeString("en-US", {
    hour: "2-digit",
    minute: "2-digit",
  });
}

export function convertMillisecondsToHourMinute(milliseconds: number): string {
  let seconds = Math.floor(milliseconds / 1000);
  let minutes = Math.floor(seconds / 60);
  let hours = Math.floor(minutes / 60);

  seconds = seconds % 60;

  minutes = seconds >= 30 ? minutes + 1 : minutes;
  minutes = minutes % 60;

  hours = hours % 24;

  return `${padTo2Digits(hours)}h ${padTo2Digits(minutes)}m`;
}

export function hourMinuteDiff(date1: Date, date2: Date): string {
  let diff = date2.getTime() - date1.getTime();
  return convertMillisecondsToHourMinute(diff);
}

/**
 * Convert date to expected <code>format<code>
 * @param date the date needed to be formatted
 * @param isEndDate if true, this function returns the end of the date
 * @param format the format of the formatted date
 */
export function formatDate(
  date?: Date | string | null,
  isEndDate: boolean = false,
  format: string = "YYYY-MM-DDTHH:mm:ss[Z]"
): string {
  if (date) {
    let dateUtc: Moment;
    if (typeof date === "string") {
      dateUtc = moment(date, "DD/MM/YYYY");
    } else {
      // format date theo chuẩn ISO string (YYYY-MM-DD)
      dateUtc = moment(moment(date).format("YYYY-MM-DD"));
    }
    if (isEndDate) {
      // cộng 1 ngày trừ 1s để lấy 16:59:59 của ngày hôm đó tức là 23:59:59
      return dateUtc.add(1, "days").subtract(1, "seconds").format(format);
    }
    return dateUtc.format(format);
  }
  return "";
}
