class Solution {
    static String[] alphabet = {"A","E","I","O","U"};
    static int answer= -1;
    static String target;
    // dfs 1회 찾을 때 마다 cnt+1번째 순서임을 나타냄
    static int cnt;
    static String[] strArr={"","","","",""};
    public int solution(String word) {
        target = word;
        int depth = 0;
        dfs(0);
        
        return answer;
    }
    
    // depth: n 번째 자리 단어 차례
    // strArr: 현재 탐색한 문자열 (인데 생각해보니까 전역 변수로 해도 됐을 듯)
    public void dfs(int depth){
        
        // System.out.println("depth"+ depth+ " : "+String.join("",strArr));
        
        // 주어진 word와 현재 탐색한 문자열이 같으면, 순서 찾음
        if(String.join("",strArr).equals(target)){
            answer = cnt;
            return;
        }
        
        // 5자리를 모두 채웠는데 word가 아니면, 돌아감
        if(depth ==5 )
            return;
        
        // a, e, i , o ,u  순서대로 단어를 완성해 나감
        for(int i=0 ;i<5;i++){
            strArr[depth] = alphabet[i];
            cnt++;
            dfs(depth+1);
            if(answer != -1){
                return;
            }
            strArr[depth] = "";
        }
            
    }
}