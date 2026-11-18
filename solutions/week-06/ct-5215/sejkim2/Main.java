import java.util.Scanner;
import java.util.*;

public class Main {
    static final int MAX = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] points = new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = sc.nextInt();
        }
        Arrays.sort(points);
        int idx = 0;
        int sum = 0;  //누적 점의 개수

        //prefix : 0부터 i까지 점의 개수 합
        //(a, b) 구간의 점의 개수를 구하려면
        //(0~b 까지의 점의 개수) - (0~a-1)의 점의 개수를 구해야 함 (0~a-1 점의 개수가 중복이므로)
        int[] prefix = new int[MAX+1];
        for(int i = 0; i<=MAX; i++) {
          //만약 점이 있는 위치이면 누적된 점의 개수+1을 해줌
            if (idx < n && i == points[idx]) {
                sum++;
                idx++;
            }
            prefix[i] = sum;
        }
        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == 0)
                System.out.println(prefix[b]);
            else
                System.out.println(prefix[b] - prefix[a - 1]);
        }
    }
}
