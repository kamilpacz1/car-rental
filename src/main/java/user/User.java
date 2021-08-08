package user;

import java.time.LocalDate;

public class User {
    private String name;
    private String lastName;
    private String pesel;
    private LocalDate dateOfBirth;
    private int carsRentedSoFar;


    public User(String name, String lastName, String pesel, LocalDate dateOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public int getCarsRentedSoFar() {
        return carsRentedSoFar;
    }

    public void incrementCarsRenter() {
        ++carsRentedSoFar;
    }

    public boolean isAgeOver(int desiredAge) {
        return LocalDate.now().minusYears(desiredAge).isAfter(dateOfBirth);
    }

    public String getDisplayInformation() {
        return String.format("%s %s. Pesel:, birth date: %s. Cars rented so far: %d", name, lastName, pesel, dateOfBirth, carsRentedSoFar);
    }
}
