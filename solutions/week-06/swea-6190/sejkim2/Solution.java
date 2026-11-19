import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int test = 1; test <= T; test++)
        {
            int N = Integer.parseInt(br.readLine());
            int[] ar = new int[N];
            int ans = -1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++)
                ar[i] = Integer.parseInt(st.nextToken());
            
            for(int i = 0; i<N; i++) {
                for(int j = i + 1; j<N; j++) {
                    int A = ar[i] * ar[j];
                    if (isIncrease(A))
                        ans = Math.max(ans, A);
                }
            }
            bw.write("#"+test+" "+ans+"");
            bw.newLine();
        }
        bw.flush();
    }
    
    //정수->문자열로 바꾸고 증가하는지 검사
    static boolean isIncrease(int A) {
        String str = new String(A+"");
        int before = str.charAt(0) - '0';
        for(int i = 1; i<str.length(); i++) {
            int cur = str.charAt(i) - '0';
            if (before > cur)
                return false;
            before = cur;
        }
        return true;
    }
}