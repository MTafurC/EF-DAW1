package com.example.DAWI_Manuel_Andres_Tafur_Calle.dto;

import java.time.LocalDate;

public record CarDetailDto(
        Integer carId,
        String make,
        String model,
        Integer year,
        String vin,
        String licensePlate,
        String ownerName,
        String ownerContact,
        LocalDate purchaseDate,
        Integer mileage,
        String engineType,
        String color,
        String insuranceCompany,
        String insurancePolicyNumber,
        LocalDate registrationExpirationDate,
        LocalDate serviceDueDate
) {
}
