package accounting;

import java.math.BigDecimal;

public class AccountingMonthlyData {
    private int numberOfRents;
    private double income;
    private BigDecimal incomeBD;

    public int getNumberOfRents() {
        return numberOfRents;
    }

    public double getIncome() {
        return income;
    }

    public void addData(double income, BigDecimal incomeBD) {
        ++numberOfRents;
        this.income = income;
        this.incomeBD = incomeBD.add(incomeBD);
    }
}
