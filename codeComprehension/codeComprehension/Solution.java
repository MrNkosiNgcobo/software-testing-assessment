import java.util.Arrays;
import java.util.Scanner;
public class Solution {

    // Complete the miniMaxSum function below.
    /*static void miniMaxSum(int[] arr) {
        int sum =0;
        for (int number: arr) {
            sum += number;
        }

        Arrays.sort(arr);
        int min = arr[arr.length-1];
        int max = arr[0];
        int minSum = sum - min;
        int maxSum = sum - max;
        System.out.println(minSum + " " + maxSum);
    }
*/
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);

        scanner.close();
    }
}