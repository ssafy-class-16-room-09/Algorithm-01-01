import java.util.*;
import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String readLine() {
        try {
            return br.readLine().trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[] nextInts() {
        StringTokenizer st = new StringTokenizer(readLine());
        return Collections.list(st).stream().mapToInt(o -> Integer.parseInt(o.toString())).toArray();
    }

    public static void main(String[] args) {
        int[] nq = nextInts();
        int n = nq[0], q = nq[1];
        int[] points = new int[n];
        {
            StringTokenizer st = new StringTokenizer(readLine());
            for (int i = 0; i < n; i++) {
                points[i] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(points);
        int maxP = points[points.length - 1];
        // cnt[i] = 범위 [0, i) 까지의 점의 개수
        int[] cnt = new int[1_000_002];

        for (int pIdx = 0, i = 0; i < cnt.length; i++) {
            // 점을 만나기 전까지는 현재 누적 점 개수
            if (pIdx < points.length && i <= points[pIdx] || pIdx >= points.length) cnt[i] = pIdx;
            else cnt[i] = ++pIdx; // 점을 만나면 카운트 + 1
        }


        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // Please write your code here.

            // a < b 순으로 하기
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            // [a, b] 점 개수 = [a, b+1) 점개수
            // = [0, b+1) - [0, a)
            System.out.println(cnt[b+1] - cnt[a]);
        }
    }
}