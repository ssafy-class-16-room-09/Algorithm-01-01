import java.util.*;
import java.io.*;

public class Solution{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, Q;
    static boolean[] dots;
    static int[] prefix;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        dots = new boolean[1000001];
        prefix = new int[1000001];

        for(int i = 0; i < N; i++){
            dots[arr[i]] = true;
        }
        prefix[0] = dots[0]?1 : 0;

        for(int i = 1; i <= 1000000; i++){
            if(dots[i]){
                prefix[i] = prefix[i-1]+1;
            }else{
                prefix[i] = prefix[i-1];
            }
        }
        /*
        for(int i = 0; i < 10; i++){
            System.out.print(prefix[i]+" ");
        }
        */
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int ans = 0;
            if (start != 0) ans = Math.max(0, prefix[end] - prefix[start-1]);
            else ans = prefix[end];
            sb.append(ans+"\n");
        }
        System.out.println(sb);
    }
}
