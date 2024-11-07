import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Unary operators:");

        int a = 20;
        System.out.println(a--);
        System.out.println(--a);

        int b = 20;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));

        boolean x = true;
        boolean y = false;
        System.out.println("x = " + x + ", y = " + y);
        System.out.println("x && y: " + (x && y));
        System.out.println("x || y: " + (x || y));
        System.out.println("!x: " + (!x));
        System.out.println("!y: " + (!y));

        int age = 50;
        if (age >= 18) {
            System.out.println("Eligible to vote");
        } else {
            System.out.println("Not eligible to vote");
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("j" + j);
            }
            System.out.println(" i" + i);
        }

        int rows = 8;
        for (int i = 1; i <= rows; i++) {
            for (int j = i; j < rows; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.print("Enter your score (0-100): ");
        int sc = scanner.nextInt();

        if (sc >= 0 && sc <= 100) {
            if (sc >= 90) {
                System.out.println("Grade: A");
            } else if (sc>= 80) {
                System.out.println("Grade: B");
            } else if (sc >= 70) {
                System.out.println("Grade: C");
            } else if (sc >= 60) {
                System.out.println("Grade: D");
            } else {
                System.out.println("Grade: F");
            }
        } else {
            System.out.println("Invalid score! Please enter a score between 0 and 100.");
        }

        scanner.close();
    }
}
