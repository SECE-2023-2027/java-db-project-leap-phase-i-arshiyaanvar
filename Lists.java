import java.util.*;

public class Lists {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        System.out.println("Enter the number of elements:");
        int n = sc.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        // Finding the minimum value
        int min = list.get(0);
        for (int nm : list) {
            if (nm < min) {
                min = nm;
            }
        }
        System.out.println("Minimum value: " + min);


        int max = list.get(0);
        for (int nm : list) {
            if (nm > max) {
                max = nm;
            }
        }
        System.out.println("Maximum value: " + max);


}}
