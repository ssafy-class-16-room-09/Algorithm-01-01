import java.util.*;

public class Main {
    static int n, m, k;
    static int[] nums;

    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        // 백트래킹 시작
        dfs(0, new int[k]);

        System.out.println(maxScore);
    }

    static void dfs(int turn, int[] pos) {
        // [기저] 턴을 끝까지 다 돈 경우
        if (turn >= n) {
            // 점수: 맨 끝(m-1)에 도착한 말의 개수를 세기
            int score = 0;
            for (int p: pos) {
                if (p == m - 1) score++;
            }
            // 최고 기록 저장
            maxScore = Math.max(maxScore, score);
            return;
        }

        // 현재 턴에서 앞으로 나아갈 칸 수
        int currentStep = nums[turn];

        // 플레이어 1 ~ K 까지 이번 턴에 가거나/말거나 백트래킹
        for (int player = 0; player < k; player++) {

            // 1. 앞으로 이동해도 맨 뒷 칸 밖으로 나가지 않는 경우
            if (pos[player] + currentStep < m) {
                pos[player] += currentStep; // 앞으로 가기
                dfs(turn + 1, pos);         // 다음 턴 탐색
                pos[player] -= currentStep; // 원복
            }
            // 2. 앞으로 더 나갈수 없는 경우
            else {
                int temp = pos[player];
                pos[player] = m - 1;
                dfs(turn + 1, pos);
                pos[player] = temp;
            }
        }
    }
}