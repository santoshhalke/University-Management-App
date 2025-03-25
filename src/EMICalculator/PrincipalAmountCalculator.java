package EMICalculator;

import java.util.Scanner;
import java.text.DecimalFormat;

public class PrincipalAmountCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.print("Enter loan amount (principal): ");
        double principal = scanner.nextDouble();

        System.out.print("Enter annual interest rate (e.g., 5.5 for 5.5%): ");
        double annualInterestRate = scanner.nextDouble() / 100.0;

        System.out.print("Enter loan term in years: ");
        int years = scanner.nextInt();

        System.out.print("Enter number of payments per year (e.g., 12 for monthly): ");
        int paymentsPerYear = scanner.nextInt();

        double monthlyPayment = calculatePMT(principal, annualInterestRate, years, paymentsPerYear);

        if (monthlyPayment != Double.NaN) {
            System.out.println("\nMonthly Payment: $" + df.format(monthlyPayment));
            printAmortizationSchedule(principal, annualInterestRate, years, paymentsPerYear);
        } else {
            System.out.println("Invalid input. Please check your inputs.");
        }

        scanner.close();
    }

    public static double calculatePMT(double principal, double annualInterestRate, int years, int paymentsPerYear) {
        double monthlyInterestRate = annualInterestRate / paymentsPerYear;
        int totalPayments = years * paymentsPerYear;

        if (monthlyInterestRate == 0) {
            return principal / totalPayments; // Avoid division by zero
        }
        if(monthlyInterestRate < 0 || totalPayments <=0 || principal < 0) {
            return Double.NaN; //indicate invalid input
        }

        double pmt = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalPayments))
                / (Math.pow(1 + monthlyInterestRate, totalPayments) - 1);

        return pmt;
    }

    public static void printAmortizationSchedule(double principal, double annualInterestRate, int years, int paymentsPerYear) {
        double monthlyInterestRate = annualInterestRate / paymentsPerYear;
        int totalPayments = years * paymentsPerYear;
        double monthlyPayment = calculatePMT(principal, annualInterestRate, years, paymentsPerYear);
        DecimalFormat df = new DecimalFormat("#.##");

        if(Double.isNaN(monthlyPayment)){
            return; //do not print if payment is invalid.
        }

        System.out.println("\nAmortization Schedule:");
        System.out.println("Payment #\tPayment\t\tPrincipal\tInterest\tBalance");

        double balance = principal;

        for (int i = 1; i <= totalPayments; i++) {
            double interestPayment = balance * monthlyInterestRate;
            double principalPayment = monthlyPayment - interestPayment;

            if (principalPayment > balance) {
                principalPayment = balance;
                monthlyPayment = principalPayment + interestPayment; //adjust final payment
            }

            balance -= principalPayment;

            System.out.println(i + "\t\t$" + df.format(monthlyPayment) + "\t\t$" + df.format(principalPayment) + "\t\t$" + df.format(interestPayment) + "\t\t$" + df.format(balance));

        }
    }
}