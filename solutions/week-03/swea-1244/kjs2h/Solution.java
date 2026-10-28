package _1244;

import java.util.*;
import java.io.*;

class Solution {

    static int n, c;
    static int[] number;
    static int max;
    static Set<String> set;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TC; tc++) {
            sb.append("#"+tc+" ");
            st = new StringTokenizer(br.readLine()," ");
            String num = st.nextToken();
            n = Integer.parseInt(num);
            c = Integer.parseInt(st.nextToken());

            // 모든 숫자를 배열에 담음
            number = new int[num.length()];
            for(int i = 0; i < num.length(); i++){
                String s = num.charAt(i)+"";
                number[i] = Integer.parseInt(s);
            }

            // 가장 큰 값을 찾자
            max = 0;
            // 백트래킹 용 set를 선언
            set = new HashSet<>();
            dfs(0);
            sb.append(max).append("\n");

        }
        System.out.println(sb);
    }

    static void dfs(int depth) {
        // 백트래킹
        StringBuilder check = new StringBuilder();
        check.append(depth);
        for(int x : number) check.append(x);

        if (!set.add(check.toString())) return;

        // 기저조건
        if (depth == c) {
            StringBuilder sb = new StringBuilder();
            for (int x : number) sb.append(x);

            max = Math.max(max, Integer.parseInt(sb.toString()));
            return;
        }

        // 2개씩 뽑아서 dfs (i와 j)
        for (int i = 0; i < number.length - 1; i++) {
            for (int j = i + 1; j < number.length; j++) {

                // 교환
                int temp = number[i];
                number[i] = number[j];
                number[j] = temp;

                // 다음 탐색
                dfs(depth + 1);

                // 원복
                temp = number[i];
                number[i] = number[j];
                number[j] = temp;
            }
        }
    }
}
