package com.rensource.videorental.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OrderDto {

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Name of renter should be entered")
    @NotEmpty(message = "Renter's name cannot be empty")
    private String rentedBy;

    @NotNull(message = "Number of days for rent should be entered")
    private Integer rentDays;
}
