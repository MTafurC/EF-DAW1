package com.example.DAWI_Manuel_Andres_Tafur_Calle.response;

import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDetailDto;

public record FindCarResponse(
        String code,
        String error,
        CarDetailDto car) {
}
