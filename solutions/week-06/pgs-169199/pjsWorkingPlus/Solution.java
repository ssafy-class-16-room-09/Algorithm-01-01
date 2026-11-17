import java.util.*;

class Solution {
    
    public int solution(String[] board) {
        
    
        int answer = 0;

        int colN = board.length;
        int rowN = board[0].length();
        int[] start = new int[2];
        int[] goal = new int[2];
        boolean[][] visited = new boolean[colN][rowN];
        
        // 각 행마다 벽의 row 좌표
        TreeSet<Integer>[] rows = new TreeSet[colN];
        // 각 열마다 벽의 col 좌표
        TreeSet<Integer>[] cols = new TreeSet[rowN];
        
        for(int i = 0 ; i < colN ; i ++){
            rows[i] = new TreeSet<>();
        }
        
        for(int i = 0 ; i < rowN ; i ++){
            cols[i] = new TreeSet<>();
        }
        
        
        String[][] map = new String[colN][rowN];
        // 출발지와 도착지를 검색
        // 벽의 위치를 rows와 cols treeSet에 기록
        for(int i = 0; i < colN ; i++){
        
            for(int j = 0 ; j < rowN ; j++){
                map[i][j] = board[i].charAt(j)+"";
                if(map[i][j].equals("R") ){
                    start[0] = j;
                    start[1] = i;
                }
                else if (map[i][j].equals("G") ){
                    goal[0] = j;
                    goal[1] = i;
                }
                else if (map[i][j].equals("D")){
                    rows[i].add(j);
                    cols[j].add(i);
                }
            }
        }
        
        // bfs 초기 세팅
        visited[start[1]][start[0]] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start[0],start[1],0));
        Node tempNode = null;
        
        // while에서 쓸 변수 선언
        int curRow = -1;
        int curCol = -1;
        int curCnt = -1;
        Integer nextRow = -1;
        Integer nextCol = -1;
        

        while(!q.isEmpty()){
            tempNode = q.poll();
            curRow = tempNode.row;
            curCol = tempNode.col;
            curCnt = tempNode.cnt;
            // System.out.println("cnt: " + curCnt +" x: "+curRow+" y: "+curCol);
            
            // 도착지에 도착하면 지금까지 이동한 횟수 반환
            if(curRow == goal[0] && curCol == goal[1]){
                System.out.println("reached");
                return curCnt;
            }
            
            /*
                기본 로직
                현재 좌표: curRow, curCol
                <오른쪽으로 이동을 하는 상황>
                
                - rows[curCol].higher(curRow)
                - curCol번째 행에서 row좌표가 curRow보다 큰 수를 확인
                
                1. 있다면 k 
                - k는 curRow보다 오른쪽에 위치하고
                첫번째로 마주하는 벽의 row 좌표임
                    - curRow -> k-1 좌표 이동 (k는 벽 이므로 벽 이전 좌표 k-1)
                
                2. 없다면 null
                - curRow의 오른쪽에 벽이 없다면 로봇은 게임판의 가장자리에 부딪힘
                    - curRow -> rowN-1로 이동
                
                
            */
            
            // 오른쪽
            nextRow = rows[curCol].higher(curRow);
            nextRow = nextRow == null ? rowN-1 : nextRow-1;
            if(!visited[curCol][nextRow]){
                visited[curCol][nextRow] = true;
                q.add(new Node(nextRow, curCol, curCnt+1));
            }
                
            
            // 아래
            nextCol = cols[curRow].higher(curCol);
            nextCol = nextCol == null ? colN-1 : nextCol-1;
            if(!visited[nextCol][curRow]){
                visited[nextCol][curRow] = true;
                q.add(new Node(curRow, nextCol, curCnt+1));
            }
                
            
            // 왼쪽
            nextRow = rows[curCol].lower(curRow);
            nextRow = nextRow == null ? 0 : nextRow+1;
            if(!visited[curCol][nextRow]){
                visited[curCol][nextRow] = true;
                q.add(new Node(nextRow, curCol, curCnt+1));
            }
                
            
            // 위
            nextCol = cols[curRow].lower(curCol);
            nextCol = nextCol == null ? 0 : nextCol+1;
            if(!visited[nextCol][curRow]){
                visited[nextCol][curRow]=true;
                q.add(new Node(curRow, nextCol, curCnt+1));
            }
                

        }
        // System.out.println(-1);
        // for(int i = 0 ; i < colN ; i++){
        //     for( int j = 0 ;j < rowN;j++){
        //         System.out.print(map[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        return -1;
    }  
}

class Node{
    int row;
    int col;
    int cnt;
    public Node(int r,int c, int cnt){
        row = r;
        col = c;
        this.cnt =cnt;
    }
}