/*
시간복잡도 생각 안하고 그냥 생각나는대로 해봄
결과는 100억번 계산해야되어서 바로 터져버림
*/

// public class Solution {

//         static int n, q, a, b, count;
//         static Queue<Integer> qu = new LinkedList<>();
//         static int[] points;

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         n = sc.nextInt();
//         q = sc.nextInt();
//         points = new int[n];
//         for (int i = 0; i < n; i++) {
//             points[i] = sc.nextInt();
//         }

//         Arrays.sort(points);

//         for (int i = 0; i < q; i++) {
//             a = sc.nextInt();
//             b = sc.nextInt();

//             qu.offer(a);
//             qu.offer(b);
//         }
//         qu_function();
//     }

//     public static void qu_function() {
//         while(!qu.isEmpty()) {
//             count = 0;

//             int x = qu.poll();
//             int y = qu.poll();

//             for (int i = 0; i < n; i++) {
//                 int range = points[i];

//                 if ( x <= range && range <= y) {
//                     count++;
//                 }
//             }System.out.println(count);
//         }
//     }
// }
//

import java.util.*;
import java.io.*;

// 누적합 강의를 받고 난 이후

public class Solution {

    static int n, q, a, b;

    // 백만개를 미리 만들어둠
    static final int MAX_pos = 1000000;
    static int[] prefixSum = new int[MAX_pos + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        q = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int pos = sc.nextInt();

            // 점의 위치에 1을 추가
            prefixSum[pos]++;
        }

        // 현재 위치에 바로 이전의 위치의 값 누적합 진행
        for (int i = 1; i <= MAX_pos; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            // 뒤에서 앞에꺼 빼기
            // b가 무조건 큰 수이니 a의 이전 값은 다 날리면됨.
            // 다만 현재 a가 0일 때 -1 해버리면 런타임 에러가 터지니 0일 때 0으로
            int answer = prefixSum[b]  - (a == 0 ? 0 : prefixSum[a-1]);

            System.out.println(answer);
        }

    }
}
