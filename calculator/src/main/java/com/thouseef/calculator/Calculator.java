package com.thouseef.calculator;

import java.util.Scanner;

public class Calculator {
    // Function to calculate the square root of a number
    public double squareRoot(double x) {
        if (x < 0) {
            System.out.println("Error: Cannot compute square root of a negative number.");
            return -1;
        }
        return Math.sqrt(x);
    }

    // Function to calculate the factorial of a number
    public long factorial(int n) {
        if (n < 0) {
            System.out.println("Error: Factorial of a negative number is undefined.");
            return -1;
        }
        if (n == 0) return 1;
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    // Function to calculate the natural logarithm of a number
    public double naturalLog(double x) {
        if (x <= 0) {
            System.out.println("Error: Logarithm of non-positive numbers is undefined.");
            return -1;
        }
        return Math.log(x);
    }

    // Function to calculate the power of a number (x^b)
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        while (true) {
            System.out.println("\n--- Scientific Calculator ---");
            System.out.println("1. Square Root (√x)");
            System.out.println("2. Factorial (x!)");
            System.out.println("3. Natural Log (ln x)");
            System.out.println("4. Power (x^b)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            if (choice == 5) {
                System.out.println("Exiting Calculator...");
                break;
            }

            double num, result;
            switch (choice) {
                case 1:
                    System.out.print("Enter a number: ");
                    num = scanner.nextDouble();
                    result = calc.squareRoot(num);
                    if (result != -1) System.out.println("√" + num + " = " + result);
                    break;

                case 2:
                    System.out.print("Enter a number: ");
                    int n = scanner.nextInt();
                    long factResult = calc.factorial(n);
                    if (factResult != -1) System.out.println(n + "! = " + factResult);
                    break;

                case 3:
                    System.out.print("Enter a number: ");
                    num = scanner.nextDouble();
                    result = calc.naturalLog(num);
                    if (result != -1) System.out.println("ln(" + num + ") = " + result);
                    break;

                case 4:
                    System.out.print("Enter base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Enter exponent: ");
                    double exponent = scanner.nextDouble();
                    result = calc.power(base, exponent);
                    System.out.println(base + "^" + exponent + " = " + result);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}