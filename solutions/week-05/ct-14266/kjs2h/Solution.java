import java.util.*;
import java.io.*;

public class Solution {

    static int n, m, k, max;
    static int[] nums, mals;

    public static void main(String[] args) throws IOException{

        // IO, 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nums = new int[n];
        // 각 말들의 현재 위치, 초기값은 1이다. 0이라고 하면 답이 안나옴 ㅋㅋ;;
        mals = new int[k];
        Arrays.fill(mals,1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());        
        
        max = 0;
        // 인덱스와 골인한 말의 수를 매개하자
        dfs(0,0);
        System.out.println(max);
    }

    static void dfs(int idx, int goal){

        // 기저조건
        // 끝까지 탐색했거나, 모든 말이 돌아오면 끝, 사실 goal이 k면 그냥 프로그램 종료하고싶은데 그걸 못하겠어
        if (idx == n || goal == k){                        
            max = Math.max(max, goal);                        
            return;
        }

        // 분기처리
        for(int i = 0; i < k; i++){
            
            // 이미 골인한 말이면 선택하지 마
            if (mals[i] >= m) continue;

            // i번째 말을 선택해서 이동시켜
            mals[i] += nums[idx];                        

            // 이번 선택으로 골인 했다면 goal 했다고 알려줘
            if (mals[i] >= m) dfs(idx + 1, goal + 1);                

            // 골인 못했으면 그냥 보내
            else dfs(idx + 1, goal);

            // 돌아와선 백트래킹 해줘
            mals[i] -= nums[idx];                                                            
        }

    }
}
