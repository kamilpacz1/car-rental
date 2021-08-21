package accounting;

import rental.Rental;
import rental.RentalEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Accounting {
    private Rental rental;

    public Accounting(Rental rental) {
        this.rental = rental;
    }

    public void displayMonthlyReport() {
        LocalDateTime today = LocalDateTime.now();
        int year = today.getYear();
        Month month = today.getMonth();
        AccountingMonthlyData[] accountingMonthlyData = buildYearlyAccounting(year);
        AccountingMonthlyData accountingData = accountingMonthlyData[month.getValue() - 1];
        System.out.printf("Earrings for %s. Total number of rentals: %d. Income: %.2f\n", month, accountingData.getNumberOfRents(), accountingData.getIncome());

    }

    public void displayYearlyReport() {
        LocalDateTime today = LocalDateTime.now();
        int year = today.getYear();
        double totalIncome = 0;
        int totalNumberORents = 0;
        AccountingMonthlyData[] accountingMonthlyData = buildYearlyAccounting(year);
        System.out.printf("Year %d summary: \n", year);
        for (int i = 0; i < accountingMonthlyData.length; i++) {
            AccountingMonthlyData monthlyData = accountingMonthlyData[i];
            Month month = Month.of(i + 1);
            System.out.printf("%-25s", "Earrings for " + month + ":");
            System.out.printf("Total number of rentals: %4d. Income: %4.2f%n", monthlyData.getNumberOfRents(), monthlyData.getIncome());
            totalIncome += monthlyData.getIncome();
            totalNumberORents += monthlyData.getNumberOfRents();
        }
        System.out.printf("Earrings for %s. Total number of rentals: %d. Income: %.2f\n", year, totalNumberORents, totalIncome);
    }

    private AccountingMonthlyData[] buildYearlyAccounting(int year) {
        AccountingMonthlyData[] data = new AccountingMonthlyData[12];
        for (int i = 0; i < data.length; i++) {
            data[i] = new AccountingMonthlyData();
        }
        RentalEntry[] rentalArchive = rental.getRentalArchive();
        for (RentalEntry entry : rentalArchive) {
            if (entry == null) {
                break;
            }
            LocalDateTime rentalStartTime = entry.getRentalStartTime();
            int rentalYear = rentalStartTime.getYear();
            Month rentalMonth = rentalStartTime.getMonth();
            if (rentalYear == year) {
                data[rentalMonth.getValue() - 1].addData(entry.getCost(), entry.getCostBD());
            }
        }
        return data;
    }
}
