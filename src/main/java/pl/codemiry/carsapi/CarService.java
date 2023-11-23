package pl.codemiry.carsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CarService {
    private final List<Car> carList;

    @Autowired
    public CarService(List<Car> carList) {
        this.carList = carList;
        createCarsList();
    }

    public void createCarsList() {
        carList.add(new Car(1L, "Daewoo", "Nubira", "claret"));
        carList.add(new Car(2L, "FSO", "Polonez", "green"));
        carList.add(new Car(3L, "Fiat", "126", "red"));
    }

    public List<Car> getCarList() {
        return carList;
    }

    public Optional<Car> getCarsById(long id) {
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

    public List<Car> getCarsByColor(String color) {
        return carList.stream().filter(car -> car.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
    }

    public void addCar(Car car) {
        carList.add(car);
    }

    public void updateCars(Car car) {
        if (getCarsById(car.getId()).isPresent()) {
            carList.remove(getCarsById(car.getId()).get());
            carList.add(car);
        }
    }

    public void editCar(long id, String mark, String model, String color) {
        if (getCarsById(id).isPresent()) {
            if (mark != null)
                getCarsById(id).get().setMark(mark);
            if (model != null)
                getCarsById(id).get().setModel(model);
            if (color != null)
                getCarsById(id).get().setColor(color);
        }
    }

    public void deleteCar(long id) {
        if (getCarsById(id).isPresent()) {
            carList.remove(getCarsById(id).get());
        }
    }
}
