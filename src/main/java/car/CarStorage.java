package car;

import array.ArrayCompactor;

import java.util.Scanner;

public class CarStorage {
    private Car[] storage = new Car[100];
    private Car[] rentedCars = new Car[100];

    public CarStorage(Car[] storage) {
        this.storage = storage;
    }

    public void listCars() {
        System.out.println("Cars in our rental:");
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                System.out.println((i + 1) + ": " + storage[i].getCarDisplayInformation());
            }
        }
    }


    public Car selectCar() {
        listCars();
        System.out.println("Select Car: ");
        Scanner scanner = new Scanner(System.in);
        int selectedCarIndex = scanner.nextInt() - 1;
        if (carExists(selectedCarIndex)) {
            return storage[selectedCarIndex];
        }
        return null;
    }

    public void addRentalCar(Car rentedCar) {
        for (int i =0; i < rentedCars.length; i++) {
            if (rentedCars[i] == null) {
                rentedCars[i] = rentedCar;
                break;
            }
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == rentedCar) {
                storage[i] = null;
            }
        }
        ArrayCompactor.compactCarArray(storage);
    }

    public void addCar(Car car) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = car;
                break;
            }
        }
        ArrayCompactor.compactCarArray(rentedCars);
    }

    public void editCarRentalPrice() {
        System.out.println("Which car would you like to edit price for?");
        listCars();
        Scanner scanner = new Scanner(System.in);
        int carToEditIndex = scanner.nextInt() - 1;
        if (carExists(carToEditIndex)) {
            System.out.println("Enter new price? ");
            double newPrice = scanner.nextDouble();
            if(newPrice <= 0) {
                System.out.println("Price must be greater than zero");
                return;
            }
            Car carToEdit = storage[carToEditIndex];
            carToEdit.setPricePerDay(newPrice);
        } else {
            System.out.println("Invalid car selected by index" + (carToEditIndex + 1));
        }
    }

    private boolean carExists(int carIndex) {
        return carIndex >= 0 && carIndex < storage.length && storage[carIndex] != null;
    }

    private void addInitialCars(Car[] initialCars) {
        for (int i = 0; i < initialCars.length; i++) {
            storage[i] = initialCars[i];
        }
    }

    public void returnACar(Car toReturn) {
    addCar(toReturn);
    for (int i = 0; i < rentedCars.length; i++) {
        if(rentedCars[i] == toReturn) {
            rentedCars[i] = null;
            break;
        }
    }
    }
}
