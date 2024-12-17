package com.example.DAWI_Manuel_Andres_Tafur_Calle.controller;

import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDto;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // Marca esta clase como un controlador en Spring MVC.
@RequestMapping("/manage") // Define la ruta base para todos los endpoints de esta clase.
public class ManageController {

    @Autowired // Inyecta automáticamente el servicio ManageService.
    ManageService manageService;

    @GetMapping("/start") // Define un endpoint GET para cargar la vista inicial con datos.
    public String start(Model model) {
        try {
            List<CarDto> cars = manageService.getAllCars(); // Obtiene la lista de autos desde el servicio.
            model.addAttribute("cars", cars); // Agrega la lista de autos como atributo para la vista.
            model.addAttribute("error", null); // Asegura que no haya un mensaje de error.
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola para debugging.
            model.addAttribute("error", "Ocurrió un error al obtener los datos"); // Agrega un mensaje de error para la vista.
        }
        return "manage"; // Retorna el nombre de la vista a renderizar.
    }
}
