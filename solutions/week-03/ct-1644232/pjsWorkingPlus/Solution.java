import java.util.*;
import java.io.*;

public class Solution {

    static int[] rowD={1,0,-1,0};
    static int[] colD={0,1,0,-1};
    static int n;

    /*
        접근 방식
        1. 전체 계란 판을 순회하며 점선으로 연결되는 계란판들을 bfs로 탐색
        2. 좌표 row,col을 기준으로  범위 탐색 시작
        3. 범위 탐색 중 바꿔야할 좌표들을 (바꿀 좌표 리스트)에 저장
        4. 1개 좌표 범위 탐색(row, col 좌표 1개 탐색)을 끝내고 다른 계란 판과 연결된 것을 bfs 탐색 중 구한 eggCnt 변수를 통해 확인함
          4-1. eggCnt>=2이면 복수 개의 계란으로 이뤄지고 계란 이동이 필요함
          4-2. (바꿀 좌표 리스트)에 저장된 {row, col, 바꿀 계란}을 (전체 좌표 탐색이 끝나고 바꿀 좌표 리스트) 변수에 추가해둠
        5. 전체 계란 판에 대한 범위 처리 연산이 끝나면 (전체 좌표 탐색이 끝나고 바꿀 좌표 리스트)에 저장된 값들을 순회하며 계란 이동
        6. 이동이 일어나면 1번 과정 반복, 이동이 일어나지 않았다면 종료
    */

    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        // 문제 전달변수 입력
        n=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());

        // 계란 개수를 담는 배열과 탐색 여부를 담을 변수
        int[][] map=new int[n][n];
        int[][] visit=new int[n][n];

        // 계란판 입력
        for(int col=0;col<n;col++){

            st=new StringTokenizer(br.readLine(), " ");

            for(int row = 0; row < n; row++ ){

                map[col][row]=Integer.parseInt(st.nextToken());

            }

        }

        // 현재 계란판에서 이동이 필요한가?
        boolean isMove = true;
        // x,y 좌표의 기준에서 bfs 탐색을 위한 자료구조
        Queue<int[]> q=new ArrayDeque<>();
        // x,y 좌표를 기준으로 범위 탐색을 했을 때 계란 양에 변동이 필요한 좌표 모음 (바꿀 좌표 리스트)
        List<int[]> listToChange=new ArrayList<>();
        // 모든 x,y 좌표를 순회한 후 이동을 시킬 모든 계란 양 변동을 담은 (전체 좌표 탐색이 끝나고 바꿀 좌표 리스트)
        List<int[]> moveList=new ArrayList<>();

        // 범위 내의 계란합
        int subSum=0;
        // 범위 내 계란칸 갯수
        int eggCnt=0;
        // 1개 계란칸에 담긴 계란 양
        int curEgg=-1;
        // 탐색할 공간의 계란 양
        int nextEgg=-1;
        
        int nextRow=-1, nextCol=-1;
        
        // 합친 계란 양/계란 수
        int value=-1;

        int[] curPosition=null;

        // 이동 횟수
        int cnt=0;


        // 이번 순회에 움직임이 있다면 (처음 탐색을 위해 최초 1회 true)
        while(isMove){
            
            // 현재 움직임 없다고 초기 설정
            isMove=false;
  

            // 계란틀 범위 탐색 방문 여부 초기화
            for(int col=0;col<n;col++){
                Arrays.fill(visit[col],0);
            }
            // 이전 이동 내역 초기화
            moveList.clear();

            // 모든 계란칸에서 범위 넓히기, 계란칸별 기준으로 bfs 순회
            for(int col=0;col<n;col++){
                
                for(int row = 0; row < n; row++ ){

                    // 이전 계란칸 범위 탐색에서 사용한 변수 초기화
                    listToChange.clear();
                    q.clear();
                    subSum=0;
                    eggCnt=0;
                    value=0;

                    // 현재 계란칸이 이미 다른 계란칸과 점선으로 연결된 곳이라면 넘어감
                    if(visit[col][row]==1)
                        continue;
                    
                    // 현재 위치를 범위 합에 합산, 계란 수 +1
                    subSum+=map[col][row];
                    eggCnt++;

                    // bfs 설정 초기 설정
                    q.offer(new int[]{row,col});
                    visit[col][row]=1;
                    listToChange.add(new int[]{row,col,0});

                    // System.out.println("start"+col+" "+row);

                    // bfs로 범위 탐색
                    while(!q.isEmpty()){
                        
                        curPosition=q.poll();
                        curEgg=map[curPosition[1]][curPosition[0]];
                        // System.out.println("range "+curPosition[1]+" "+curPosition[0]);

                        // 4방 탐색
                        for(int dir=0;dir<4;dir++){

                            nextRow=curPosition[0]+rowD[dir];
                            nextCol=curPosition[1]+colD[dir];
                            // nextCol=curPosition[1]+colD[col];

                            // 맵 크기 초과 여부
                            if(!isRange(nextRow,nextCol)){
                                continue;
                            }

                            // 이미 점선으로 다른 계란칸과 묶였는지 여부 확인
                            if(visit[nextCol][nextRow]==1){
                                continue;
                            }

                            // 다음 탐색으로 연결된 계란칸의 계란 양 
                            nextEgg=map[nextCol][nextRow];

                            // L/R 조건 충족하면 범위 확장
                            if( Math.abs(curEgg-nextEgg) >= L && Math.abs(curEgg-nextEgg) <= R ){
                                visit[nextCol][nextRow]=1;
                                subSum+=nextEgg;
                                eggCnt++;
                                int[] temp=new int[]{nextRow,nextCol,0};
                                // 범위를 모두 확장하고 해당 범위 순회를 위한 좌표 저장 / (바꿀 좌표 리스트)에 추가
                                listToChange.add(temp);
                                q.offer(temp);
                            }
                                
                        }
                    }


                    // 확장을 못함
                    if(eggCnt==1){
                        continue;
                    }

                    // bfs 탐색을 통해 얻은 계란칸의 범위 합과 계란칸 갯수
                    value=subSum/eggCnt;
                    

                    // x,y 계란칸 기준으로 범위 확장이 끝남
                    // 범위에 속한 계란칸이 가질 value를 범위에 속한 좌표들에 value로 기록
                    for(int[] temp:listToChange){
                        // 교체 범위가 1칸이하면 범위 확장을 안함
                        if(listToChange.size()<=1)
                            break;
                        isMove=true;
                        temp[2]=value;
                        // 모든 x,y 탐색이 끝나고 이동을 해야할 리스트에 저장 / (전체 좌표 탐색이 끝나고 바꿀 좌표 리스트)에 추가
                        moveList.add(temp);
                    }
                }
            }
            // 전체 범위 연산 완료
            // 계란 이동이 존재하면
            if(isMove){
                cnt++;

                // 미리 저장해둔 이동 후보들 연산
                for(int[] temp:moveList){
                    map[temp[1]][temp[0]]=temp[2];
                    // System.out.printf("col/row %d/%d value %d\n",temp[1],temp[0],temp[2]);
                }
            }

            // for(int col=0;col<n;col++){
            //     for(int row=0;row<n;row++){
            //         System.out.print(map[col][row]+" ");
            //     }
            //     System.out.println();
            // }
            //                 System.out.println();
        }
        System.out.println(cnt);

    }

    // 전달된 파라미터가 맵 범위 내인지 도와주는 helper 메서드
    static boolean isRange(int r,int c){
        if( r >= 0 && r < n && c >= 0 && c < n )
            return true;

        return false;
    }
}
