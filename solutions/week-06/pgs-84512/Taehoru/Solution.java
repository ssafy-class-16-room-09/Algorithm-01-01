import java.util.*;
import java.io.*;

// 제한 사항이 작으니
// 하나씩 다 꺼내보면서 하면 됨

// A부터 UUUUUU까지 다 돌릴려면 재귀

// 단어가 다 합쳐도 3905개밖에 안 돼서 그냥 전부 만들어 보면서 세면 됨
// 끝까지 파고들었다가 막히면 돌아와서 다음 글자로 바꿈
// 그래서 빈 문자열에서 시작해 AEIOU 순서로 한 글자씩 붙여 가며 dfs 돌리고
// 5글자 되면 return
// 방문할 때마다 count 올리다가 찾는 단어 나오면 그때 count를 answer에 저장했음

class Solution {
    
    int result = 0;
    int count = 0;
    String target;
    String[] alphabet = {"A", "E", "I", "O", "U"};

    public int solution(String word) {
        target = word;
        dfs("");
        return result;
    }

    
    public void dfs(String depth) {
        // 찾으면 멈춤
        if (depth.equals(target)) {
            result = count;
            return;
        }
        
        // 카운트 업
        count++;
        
        // 끝에 닿으면 
        if (depth.length() == 5) {
            return;
        }
        
        // 재귀재귀재귀
        for (int i = 0; i < 5; i++) {
            dfs(depth + alphabet[i]);
        }
    }
}