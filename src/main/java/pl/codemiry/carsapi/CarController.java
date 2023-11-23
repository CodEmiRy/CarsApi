package pl.codemiry.carsapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getCars() {
        return carService.getCarList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Car> getCarsById(@PathVariable long id) {
        return carService.getCarsById(id);
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getCarsByColor(@PathVariable String color) {
        return carService.getCarsByColor(color);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCar(@RequestBody Car car) {
        carService.updateCars(car);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void editCar(@PathVariable long id,
                        @RequestHeader(required = false) String mark,
                        @RequestHeader(required = false) String model,
                        @RequestHeader(required = false) String color) {
        carService.editCar(id, mark, model, color);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }
}
