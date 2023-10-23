package com.tuandh.travelwala.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest {
    private String title;

    @NotBlank(message = "First name can't be empty")
    private String firstName;

    @NotBlank(message = "Last name can't be empty")
    private String lastName;

    @NotNull(message = "Please provide date of birth")
    private DateOfBirth dob;

    @NotBlank(message = "Can't leave nationality empty")
    private String nationality;

    @Getter
    @AllArgsConstructor
    public static class DateOfBirth {
        private int day;
        private int month;
        private int year;

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(year).append('-')
                    .append(month).append('-')
                    .append(day);
            return builder.toString();
        }
    }
}
