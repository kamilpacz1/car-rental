import accounting.Accounting;
import car.Car;
import car.CarCreator;
import car.CarStorage;
import rental.Rental;
import user.UserCreator;
import user.UserStorage;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleApplication {
    public void run(Car[] initialCars) {
        ConsoleMenu mainMenu = buildMainMenu();
        CarStorage carStorage = new CarStorage(initialCars);
        CarCreator carCreator = new CarCreator(carStorage);
        UserStorage userStorage = new UserStorage();
        UserCreator userCreator = new UserCreator(userStorage);
        Rental rental = new Rental(carStorage, userStorage);
        Accounting accounting = new Accounting(rental);
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);

        while (true) {
            mainMenu.display();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    carStorage.listCars();
                    break;
                case 2:
                    carStorage.editCarRentalPrice();
                    break;
                case 3:
                    carCreator.createNewCar();
                    break;
                case 4:
                    userStorage.listActiveUsers();
                    break;
                case 5:
                    userCreator.createNewUser();
                    break;
                case 6:
                    userStorage.activateUser();
                    break;
                case 7:
                    rental.rentACar();
                    break;
                case 8:
                    rental.listAllActiveRentedCars();
                    break;
                case 9:
                    rental.returnACar();
                    break;
                case 10:
                    accounting.displayMonthlyReport();
                    break;
                case 11:
                    accounting.displayYearlyReport();
                    break;
                case 12:
                    System.out.println("Thanks, bye ...");
                    return;
                default:
                    System.out.println("Invalid number. Please try again");
            }
        }
    }


    private static ConsoleMenu buildMainMenu() {
        String[] options = {
                "List available cars",
                "Edit rental price",
                "Add new car",
                "List active users",
                "Add user",
                "Active a user",
                "Rent a car",
                "List all rented cars",
                "Return a car",
                "Display monthly report",
                "Display yearly report",
                "Exit"
        };
        String header = "Welcome to car rental.";
        return new ConsoleMenu(header, options);
    }
}
