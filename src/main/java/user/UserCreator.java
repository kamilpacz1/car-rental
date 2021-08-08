package user;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserCreator {
    private UserStorage userStorage;

    public UserCreator(UserStorage userStorage) {
        this.userStorage = userStorage;

    }
    public void createNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add details for new user: ");
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        if(name.isEmpty()) {
            System.out.println("Invaild name - exiting . . .");
            return;
        }

        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        if(lastName.isEmpty()) {
            System.out.println("Invaild last name - exiting . . .");
            return;
        }

        System.out.println("Enter pesel: ");
        String pesel = scanner.nextLine();
        if(!Pattern.compile("\\d{11}").matcher(pesel).matches()) {
            System.out.println("Pesel incorrect, should contain 11 digits - exiting . . .");
            return;
        }

        if(userStorage.containsUserWithPesel(pesel)) {
            System.out.printf("User with pesel %s exists - exiting . . .", pesel);
            return;
        }

        LocalDate birthDate = parsePeselIntoBirthDate(pesel) ;
        if(LocalDate.now().minusYears(18).isBefore(birthDate)) {
            System.out.println("Only people with age over 18 are allowed - exiting . . . ");
            return;
        }

        User newUser = new User(name, lastName, pesel, birthDate);
        userStorage.addUser(newUser);
        }

    private LocalDate parsePeselIntoBirthDate(String pesel) {
        int yearPart = Integer.parseInt(pesel.substring(0, 2));
        int monthPart = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        int year = 1900 + yearPart;
        int month = monthPart;
        if (monthPart > 20) {
            year = 2000 + yearPart;
            month = monthPart - 20;
        }

        return LocalDate.of(year, month, day);
    }
}

