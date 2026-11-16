import java.util.*;
import java.io.*;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] nums;
    static int max;

    public static void main(String[] args) throws IOException{
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++){
                nums[i] = Integer.parseInt(st.nextToken());
            }

            max = -1;
            for(int i = 0; i < (N - 1); i++){
                for(int j = (i + 1); j < N; j++){
                    isAsc(nums[i] * nums[j]);
                }
            }
            sb.append("#"+tc+" "+max+"\n");
        }
        System.out.println(sb);
    }

    // 단조 증가하나요?
    public static void isAsc(int num){
        String str = Integer.toString(num);
        int len = str.length() - 1;
        int i = 1;


        // stack을 활용해서 peek로 확인하기 << 이거 말고는 똑같을듯요!
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.parseInt(str.charAt(0)+""));

        boolean rf = false;
        while(len-- > 0){
            int curr = Integer.parseInt(str.charAt(i)+"");
            if (stack.peek()<=curr) stack.push(curr);
            else{
                rf = true;
                break;
            }
            i++;
        }

        if (rf) return;

        max = Math.max(max, num);
    }
}
