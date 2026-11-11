import java.util.*;
import java.io.*;

public class Solution{

    // 사방 탐색 배열
    static int[] rowD={1,0,-1,0};
    static int[] colD={0,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 맵과 방문처리 배열
        int[][] rooms = new int[n][n];
        int[][] visited =  new int[n][n];

        StringTokenizer st = null;
        String str=null;

        // 입력 처리
        for(int col = 0; col < n; col++){
            str = br.readLine();
            for(int row = 0; row < n ; row++){
                rooms[col][row] = str.charAt(row)-'0';
            }
        }

        // 검정 방을 적게 지나가야함 -> 검정방을 가장 적게 지나는 경로부터 찾아 나간다.
        // 짧은 거리 탐색인 bfs + 검정 방을 적게 지나는 경로(짧은 거리의 기준)를 우선 탐색 -> priorityQueue
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            return Integer.compare(a.blackCnt,b.blackCnt);
        });

        // 우선순위 큐 세팅
        pq.offer(new Node(0, 0, 0));
        Node tempNode=null;
        int curRow = -1;
        int curCol = -1;
        int curBlack = -1;
        int nextRow = -1;
        int nextCol = -1;
        int nextBlack = -1;
        visited[0][0]=1;


        // 끝방에 도착하면 검정방을 가장 적게 거친 방에 도착한 경우가 나온 것
        while(!pq.isEmpty()){
            tempNode = pq.poll();
            curRow = tempNode.row;
            curCol = tempNode.col;
            curBlack = tempNode.blackCnt;

            // System.out.println(curCol+" "+curRow+" "+curBlack);
            // 기저조건
            if(curRow == n-1 && curCol == n-1){
                System.out.println(curBlack);
                return;
            }

            // 사방 탐색
            for(int i = 0 ;i < 4; i++){
                nextRow = curRow + rowD[i];
                nextCol = curCol + colD[i];

                // 배열 범위 조건
                if(!(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n )){
                    continue;
                }
                
                // 방문 조건
                if(visited[nextCol][nextRow] == 1){
                    continue;
                }

                // 다음 방이 검은 방이면 현재까지 거쳐간 검은 방 계수에 +1 추가
                nextBlack = curBlack + (rooms[nextCol][nextRow] == 0 ? 1 : 0);
                // 다음 방 방문 처리
                visited[nextCol][nextRow] = 1;

                // 다음 방으로 이동
                pq.offer(new Node(nextBlack, nextRow, nextCol));
            }
            
        }

        
    }
}

class Node{
    int blackCnt;
    int row;
    int col;

    public Node(int blackCnt, int row, int col){
        this.blackCnt = blackCnt;
        this.row = row;
        this.col = col;
    }
}