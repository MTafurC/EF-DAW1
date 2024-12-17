package com.example.DAWI_Manuel_Andres_Tafur_Calle.service.imp;

import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDetailDto;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.dto.CarDto;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.model.Car;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.repository.CarRepository;
import com.example.DAWI_Manuel_Andres_Tafur_Calle.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Indica que esta clase es un servicio en la arquitectura Spring.
public class ManageServiceImpl implements ManageService {

    @Autowired // Inyecta automáticamente el repositorio de autos.
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cars = new ArrayList<>(); // Inicializa una lista vacía para almacenar los DTOs de autos.
        Iterable<Car> iterable = carRepository.findAll(); // Recupera todos los autos desde el repositorio.
        iterable.forEach(car -> {
            cars.add(new CarDto( // Convierte cada entidad Car en un DTO CarDto y lo agrega a la lista.
                    car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getVin(),
                    car.getLicensePlate(),
                    car.getOwnerName(),
                    car.getOwnerContact(),
                    car.getPurchaseDate(),
                    car.getMileage(),
                    car.getEngineType(),
                    car.getColor(),
                    car.getInsuranceCompany(),
                    car.getInsurancePolicyNumber(),
                    car.getRegistrationExpirationDate(),
                    car.getServiceDueDate()));
        });
        return cars; // Devuelve la lista de autos en forma de DTOs.
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id); // Busca un auto por su ID.
        return optional.map(car -> new CarDetailDto( // Convierte la entidad encontrada en un DTO detallado.
                car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDto.carId()); // Busca el auto por ID.
        return optional.map(car -> { // Si se encuentra, actualiza sus atributos.
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setYear(carDto.year());
            car.setVin(carDto.vin());
            car.setLicensePlate(carDto.licensePlate());
            car.setOwnerName(carDto.ownerName());
            car.setOwnerContact(carDto.ownerContact());
            car.setPurchaseDate(carDto.purchaseDate());
            car.setMileage(carDto.mileage());
            car.setEngineType(carDto.engineType());
            car.setColor(carDto.color());
            car.setInsuranceCompany(carDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDto.registrationExpirationDate());
            car.setServiceDueDate(carDto.serviceDueDate());
            carRepository.save(car); // Guarda los cambios en la base de datos.
            return true;
        }).orElse(false); // Si no se encuentra, devuelve false.
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.carId()); // Verifica si el auto ya existe.
        if (optional.isEmpty()) { // Si no existe, crea un nuevo auto.
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(carDetailDto.purchaseDate());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDetailDto.registrationExpirationDate());
            car.setServiceDueDate(carDetailDto.serviceDueDate());
            carRepository.save(car); // Guarda el nuevo auto en la base de datos.
            return true;
        }
        return false; // Devuelve false si el auto ya existe.
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id); // Busca el auto por su ID.
        return optional.map(car -> { // Si se encuentra, lo elimina.
            carRepository.delete(car);
            return true;
        }).orElse(false); // Si no se encuentra, devuelve false.
    }
}
