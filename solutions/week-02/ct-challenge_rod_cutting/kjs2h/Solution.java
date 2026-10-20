import java.util.*;
import java.io.*;

public class Solution {

    static int N;
    static int[] P;   // 각각을 팔았을 때 얻을 수 있는 이익
    static int[] DP;  // 길이가 x일때의 최대값을 담을 동적 배열

    public static void main(String[] args) throws IOException{
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        

        N = Integer.parseInt(br.readLine());
        P = new int[N + 1];
        P[0]=0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) P[i] = Integer.parseInt(st.nextToken());        
        
        DP = new int[N+1];
        DP[0] = 0;
        DP[1] = P[1];

        for(int i = 2; i <= N; i++){
            int submax = 0;
            // 길이 4인 막대기 가격을 만드는 방법은
            // 길이 0인 막대기 가격 + 길이 4인 막대기 가격
            // 길이 1인 막대기 가격 + 길이 3인 막대기 가격
            // 길이 2인 막대기 가격 + 길이 2인 막대기 가격
            // 길이 3인 막대기 가격 + 길이 1인 막대기 가격
            for(int j = 0; j < i; j++) submax = Math.max(submax, DP[j] + P[i-j]);
            DP[i] = submax;
        }
        System.out.println(DP[N]);
    }

}