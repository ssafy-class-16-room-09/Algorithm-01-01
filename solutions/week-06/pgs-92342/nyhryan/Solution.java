import java.util.*;

class Solution {
    static int[] scores = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    
    static int[] apeachCnt;
    static int rounds;
        
    static int maxDiff;
    static int[] maxRyanCnt;
    
    
    public int[] solution(int n, int[] info) {
        apeachCnt = info;
        rounds = n;
        maxDiff = 0;
        maxRyanCnt = null;
        
        int[] ryanCnt = new int[11];
        dfs(0, rounds, ryanCnt);
        
        return maxRyanCnt == null ? new int[]{-1} : maxRyanCnt;
    }
    
    void dfs(int idx, int remaining, int[] ryanCnt) {
        // [기저] 10~1점과녁까지 다 쓰고 idx=10(0점 과녁)에 왔거나
        // 1점과녁까지 오지도 않았는데 화살을 다 쓴 경우
        if (idx == 10 || remaining == 0) {
            int temp = ryanCnt[10];
            
            ryanCnt[10] += remaining;   // 만약 화살이 남았다면 0점 과녁에 몰빵
            evaluate(ryanCnt);          // 점수 계산
            ryanCnt[10] = temp;         // 원복
            return;
        }
        
        // 케이스 1: idx 번째 과녁을 안쏘고, 낮은 점수를 배분해서 점수 먹어보기
        dfs(idx + 1, remaining, ryanCnt);
        
        // 케이스 2: 어피치+1 만큼 쏠 수 있으면 쏴서 점수를 먹고, 다음 단계 가기
        int required = apeachCnt[idx] + 1;
        if (remaining >= required) {
            int temp = ryanCnt[idx];
            
            ryanCnt[idx] = required;    // 이번 과녁을 required 개수 만큼 써보기
            dfs(idx + 1, remaining - required, ryanCnt);
            ryanCnt[idx] = temp;        // 원복
        }
    }
    
    void evaluate(int[] ryanCnt) {
        // 1. 점수 계산
        int aScore = 0, rScore = 0;
        // 10, 9, ..., 1, 0점까지 계산
        for (int i = 0; i < 11; i++) {
            if (apeachCnt[i] == 0 && ryanCnt[i] == 0) continue;
            
            int score = 10 - i;
            
            if (apeachCnt[i] >= ryanCnt[i]) aScore += score;
            else rScore += score;
        }
        
        // 라이언 패배
        if (rScore <= aScore) return;
        
        // 문제 결과: 라이언과 어피치의 점수 차가 제일 크게 하기
        int diff = rScore - aScore;
        if (diff > maxDiff) {
            maxDiff = diff;
            maxRyanCnt = ryanCnt.clone();
        }
        // 기존 최고기록과 같다면, 낮은 점수를 많이 맞춘 점수가 더 GOOD
        else if (diff == maxDiff) {
            if (compare(ryanCnt, maxRyanCnt)) { // 이번 ryanCnt가 더 GOOD이라면 갱신
                maxRyanCnt = ryanCnt.clone();
            }
        }
    }
    
    // curr과 other 과녁 맞춘 개수 배열 중 curr이 낮은 점수를 더 많이 맞췄다면 true
    boolean compare(int[] curr, int[] other) {
        for (int i = 10; i >= 0; i--) {
            if (curr[i] > other[i]) return true;
            if (curr[i] < other[i]) return false;
        }
        
        return false;
    }
}
