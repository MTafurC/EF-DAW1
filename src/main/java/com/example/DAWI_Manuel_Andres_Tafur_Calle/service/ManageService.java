package com.example.DAWI_Manuel_Andres_Tafur_Calle.service;


import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDetailDto;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDto;

import java.util.List;
import java.util.Optional;

// Interfaz para la gestión de autos
public interface ManageService {

    // Obtiene la lista de todos los autos
    List<CarDto> getAllCars() throws Exception;

    // Agrega un nuevo auto
    boolean addCar(CarDetailDto carDetailDto) throws Exception;

    // Obtiene los detalles de un auto por ID
    Optional<CarDetailDto> getCarById(int id) throws Exception;

    // Actualiza un auto existente
    boolean updateCar(CarDto carDto) throws Exception;

    // Elimina un auto por ID
    boolean deleteCarById(int id) throws Exception;
}
