package com.example.DAWI_Manuel_Andres_Tafur_Calle.api;

import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDetailDto;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDto;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.response.*;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Marca esta clase como un controlador REST en Spring.
@RequestMapping("/manage/car") // Define la ruta base para todos los endpoints de esta clase.
public class ManageCarApi {

    @Autowired // Inyecta automáticamente el servicio ManageService.
    ManageService manageService;

    @GetMapping("/all") // Define un endpoint GET para obtener todos los autos.
    public FindCarsResponse findCars() {
        try {
            List<CarDto> cars = manageService.getAllCars(); // Llama al servicio para obtener la lista de autos.
            if (!cars.isEmpty())
                return new FindCarsResponse("01", null, cars); // Respuesta si se encuentran autos.
            else
                return new FindCarsResponse("02", "Car not found", null); // Respuesta si no hay autos.

        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola para debugging.
            return new FindCarsResponse("99", "An error ocurred, please try again", null); // Respuesta de error general.
        }
    }

    @GetMapping("/detail") // Define un endpoint GET para obtener detalles de un auto por ID.
    public FindCarResponse findCar(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            Optional<CarDetailDto> optional = manageService.getCarById(Integer.parseInt(id)); // Busca el auto por su ID.
            return optional.map(car ->
                    new FindCarResponse("01", null, car) // Respuesta si se encuentra el auto.
            ).orElse(
                    new FindCarResponse("02", "Car not found", null) // Respuesta si no se encuentra el auto.
            );
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola para debugging.
            return new FindCarResponse("99", "An error ocurred, please try again", null); // Respuesta de error general.
        }
    }

    @PutMapping("/update") // Define un endpoint PUT para actualizar un auto.
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto) {
        try {
            if (manageService.updateCar(carDto)) // Llama al servicio para actualizar el auto.
                return new UpdateCarResponse("01", null); // Respuesta si se actualiza exitosamente.
            else
                return new UpdateCarResponse("02", "Car not found"); // Respuesta si el auto no existe.
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola para debugging.
            return new UpdateCarResponse("99", "An error ocurred, please try again"); // Respuesta de error general.
        }
    }

    @DeleteMapping("/delete/{id}") // Define un endpoint DELETE para eliminar un auto por ID.
    public DeleteCarResponse deleteCar(@PathVariable String id) {
        try {
            if (manageService.deleteCarById(Integer.parseInt(id))) // Llama al servicio para eliminar el auto.
                return new DeleteCarResponse("01", null); // Respuesta si se elimina exitosamente.
            else
                return new DeleteCarResponse("02", "Car not found"); // Respuesta si el auto no existe.
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola para debugging.
            return new DeleteCarResponse("99", "An error ocurred, please try again"); // Respuesta de error general.
        }
    }

    @PostMapping("/create") // Define un endpoint POST para crear un nuevo auto.
    public CreateCarResponse createCar(@RequestBody CarDetailDto carDetailDto) {
        try {
            if (manageService.addCar(carDetailDto)) // Llama al servicio para agregar un nuevo auto.
                return new CreateCarResponse("01", null); // Respuesta si se crea exitosamente.
            else
                return new CreateCarResponse("02", "Car already exists"); // Respuesta si el auto ya existe.
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola para debugging.
            return new CreateCarResponse("99", "An error ocurred, please try again"); // Respuesta de error general.
        }
    }
}
