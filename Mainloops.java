public class Mainloops {
    public static void main(String[] args) {
        System.out.println("For");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        System.out.println("While");
        int j = 5;
        while (j >= 1) {
            System.out.print(j + " ");
            j--;
        }
        System.out.println("\n");
        System.out.println("Do-While:");
        int k = 1;
        do {
            System.out.print(k + " ");
            k++;
        } while (k <= 3);
        System.out.println("\n");
        System.out.println("Triangle Pattern:");
        int n = 5;
        for (int r = 1; r<= n; r++) {
            for (int col = 1; col <= r; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

