import java.util.*;
import java.io.*;
import java.util.stream.IntStream;
 
public class Solution {
    public static void main(String[] args) {
        int T = MyReader.nextInt();
        IntStream.rangeClosed(1, T).forEach(tc -> {
            int res = solve();
            System.out.println("#" + tc + " " + res);
        });
    }
 
    static int N;
    static int[] arr;
 
    static int[] monoCache = new int[1000 * 1000];
 
    static boolean isMonotonic(int idx_a, int idx_b) {
        // 캐시에 1면 단조증가, 2이면 안 단조증가, 0이면 캐시에 없음
        int idx = idx_a * 1000 + idx_b;
        if (monoCache[idx] == 1 || monoCache[idx] == 2) return monoCache[idx] == 1;
        int num = arr[idx_a] * arr[idx_b];
 
        // 0으로 끝나는 수는 무조건 안단조증가
        if (num % 10 == 0) {
            monoCache[idx] = 2;
            return false;
        }
 
        char[] numStr = String.valueOf(num).toCharArray();
 
        // 곱한 숫자를 문자열로 바꿔서 앞에서부터 순차 탐색
        int i = 1;
        for (; i < numStr.length && numStr[i] - numStr[i - 1] >= 0; i++) ;
 
        monoCache[idx] = i == numStr.length ? 1 : 2;
        return monoCache[idx] == 1;
    }
 
    static int solve() {
        N = MyReader.nextInt();
        arr = MyReader.nextInts(N);

        // 입력 수열 오름차 순 정렬 
        Arrays.sort(arr);

        // 캐시 초기화
        Arrays.fill(monoCache, 0);
 
        // 정렬된 수열의 뒤쪽부터 2개씩 묶어 검사하기
        int ans = Integer.MIN_VALUE;
        for (int i = N - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int a = arr[j], b = arr[i];
                if (isMonotonic(j, i)) ans = Math.max(ans, a * b);
            }
        }

        // 못 찾았으면 -1
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
 
class MyReader {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static int nextInt() {
        try {
            return Integer.parseInt(readLine().trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static int[] nextInts(int size) {
        StringTokenizer st = new StringTokenizer(readLine().trim());
        if (size != st.countTokens()) {
            throw new IllegalArgumentException();
        }
 
        return Collections.list(st).stream().mapToInt(obj -> Integer.parseInt(obj.toString())).toArray();
    }
}