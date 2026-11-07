import java.util.*;

public class Solution {

    Solution(){}

    static int[][] INFO;
    static int N, M, min, depth;
    static int[][] dp;

    public int solution(int[][] info, int n, int m) {
        // 전역변수화, 초기화
        N=n;
        M=m;
        min = Integer.MAX_VALUE;
        depth = info.length;
        INFO = info;

        // b가 남긴 흔적의 최대 값은 M임
        dp = new int[depth][M];
        for(int i = 0; i < depth; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 가치지기 1) 0이나 -1을 return 해야하는 경우 중 일부는 미리 알 수 있다
        int sumB = 0;
        for (int[] item : info) {
            sumB += item[1];
            // 만약 어떤 item에 들어있는 두 값이 모두 n, m보다 크면 그냥 -1
            if (item[0] >= n && item[1] >= m) return -1;
        }
        // b만 선택하는 경우가 가능하면 0
        if (sumB < m) return 0;

        // dfs 시작
        dfs(0,0,0);

        return min==Integer.MAX_VALUE?-1:min;
    }

    public void dfs(int a, int b, int idx){

        // 가지치기 2) 이미 A도둑의 최소 흔적개수를 넘겼거나, 문제 조건에 위배라면 return
        if (a >= min || a >= N || b >= M) return;

        // 기저조건 : 모든 물건을 훔쳤다면 최소 비교 후 return
        if(idx==depth){
            min = Math.min(min,a);
            return;
        }

        // 매우 중요
        // 가지치기 3) 메모이제이션: 다른 선택을 했서 왔더라도 저장된 값보다 크면 더 탐색할이유가 없음
        // 어떤 선택을 통해 (bbbbaaaaaa)
        // 10번째까지 누적하여 b가 남긴 흔적이 6, a가 남긴 흔적이 8이라고 가정해보자

        // 저렇게 분기가 진행되다가 백트래킹되어 돌아온 다음
        // 이런 선택이 있다고 해보자 (aaaabbbbbb)
        // 이 때 우연히 b가 남긴 흔적의 누적이 6이라고 하면
        // a가 8보다 작을 경우만 유의미한 탐색이 진행된다

        // a가 8이상이면 return하고
        if (a >= dp[idx][b]) return;
        // a가 8미만이면 dp를 수정해주고 탐색을 진행한다.

        dp[idx][b] = a;

        // 백트래킹
        dfs(a + INFO[idx][0], b, idx + 1);
        dfs(a, b + INFO[idx][1], idx + 1);
    }
}
