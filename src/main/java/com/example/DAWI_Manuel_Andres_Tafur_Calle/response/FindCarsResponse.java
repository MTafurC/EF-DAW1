package com.example.DAWI_Manuel_Andres_Tafur_Calle.response;

import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDto;

public record FindCarsResponse (
        String code,
        String error,
        Iterable<CarDto> cars) {

}