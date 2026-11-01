import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);


        // arr[n - 1] * arr[n - 2] * arr[n - 3]; - 이랬다가 틀려서 ??? gpt한테 물어봄
        // 음수 두 개를 곱하면 양수로 변하는걸 생각 못함. 그냥 예제에 나와있는데 못봄ㅋㅋ
        int answer = Math.max(arr[n - 1] * arr[n - 2] * arr[n - 3], arr[0] * arr[1] * arr[n-1]);

        System.out.println(answer);
    }
}