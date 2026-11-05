import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minus = new PriorityQueue<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num >= 0) plus.offer(num);
            else minus.offer(num);
        }

        int plusSize = plus.size();
        int minusSize = minus.size();

        // 결과가 양수인 경우: +++, +-- << 절대값을 최대로
        // 결과가 음수인 경우: ---, ++- << 절대값을 최소로        

        long ans = Long.MIN_VALUE;
        
        List<Integer> p = new ArrayList<>();
        List<Integer> m = new ArrayList<>();

        while (!plus.isEmpty()) p.add(plus.poll());
        while (!minus.isEmpty()) m.add(minus.poll());

        // +++
        if (plusSize >= 3) {
            long sum = p.get(0) * p.get(1) * p.get(2);
            ans = Math.max(ans, sum);
        }

        // +--
        if (plusSize >= 1 && minusSize >= 2) {
            long sum = p.get(0) * m.get(0) * m.get(1);
            ans = Math.max(ans, sum);
        }

        // ---
        if (minusSize >= 3) {
            // 절댓값이 가장 작은 음수 3개
            long sum = 1L * m.get(minusSize - 1) * m.get(minusSize - 2) * m.get(minusSize - 3);
            ans = Math.max(ans, sum);
        }

        // ++-
        if (plusSize >= 2 && minusSize >= 1) {
            // 곱이 음수이므로 절댓값이 가장 작은 조합
            long sum = 1L * p.get(plusSize - 1) * p.get(plusSize - 2) * m.get(minusSize - 1);
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}
