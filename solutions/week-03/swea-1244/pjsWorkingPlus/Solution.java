// <접근 1>
// 1안 각 자리수 숫자를 객체로 만들어
// pq로 숫자가 클수록, 같다면 자릿수가 작을수로 우선 순위를 높임
// 숫자의 큰 자릿수부터 pq를 deque하여 교체함
//
// 문제점 1 : 문제 조건 상 교환 횟수가 전체 자릿수 보다 많다
// 문제점 2 : n번째 위치한 숫자를 같은 자리에 놓을 수 없다
// 문제점 1 해결 방안 : pq에 있는 모든 수를 내림차순으로 정렬하면 1의 자리 수와 10의 자리 수만 계속 교체한다.
// 문제점 2 해결 방안 : pq에서 뽑은 값이 위치한 자리와 현재 놓을려는 자리가 같다면 다음 pq를 뽑아 다음 자림에 둔다
//                  기저 조건으로 pq를 모두 뽑았다면 앞선 문제 1해결법을 실행한다.
//
// 1차 접근의 문제점(gpt 방향성 제시) : 정렬 도중에 배열에 대해 최대값을 보장하지 않음
//                  k가 정렬에 필요한 횟수보다 작은 경우, 입력 32888 1 예시

// <접근 2>
// 2차 접근
// 입력 32888 1을 수정하는 것을 목표 삼음
// <설계 방식>
// 1. 주어진 숫자의 앞에서 부터 선택 정렬을 시행
// 2. 맨 앞 index를 고르고 pq에서 숫자가 높은 값을 뽑음
// 3. 중복되는 숫자 ex)12333의 경우 pq에서 3을 3개 뽑아 후보Q에 넣음
// 4. 후보Q를 모두 돌려 교체한 모든 케이스를 확인, 32133 32313 32331 중 제일 큰 경우를 골라 해당 케이스로 교체 진행
// 5 .index에 대한 선택 정렬이 끝나면 다음 index에 대한 교체 진행 -> (1)
// 모든 자릿수의 정렬을 수행하면 남은 교체 기회에 대해서는 10의자리와 1의자리를 교체하거나 중복되는 수가 있다면 변경 없이 반환
//
// 문제점 : 해당 방식은 앞선 1차 접근과 마찬가지로 정렬 도중 배열에 대한 최댓값을 미제공함

// <최종 접근>
// 자릿수 최대 6, 교체 가능한 가짓수 15개를 재차 확인하여 조합과 dfs로 변경
// 현재 숫자(상태) 에서 두개 숫자를 조합으로 선택하고 교체 -> 다음 dfs 호출
// 총 chance만큼 완전 탐색을 하고 교체 완료한 숫자가 가장 큰 값인지 확인

// (a 상태) --(선택 1)-> (b 상태)
// (a 상태) --(선택 2)-> (b 상태)
// 이처럼 동일한 상태의 경우, chance 별 방문 처리로 중복 탐색을 막음


import java.util.*;
import java.io.*;

// 완전 탐색, dfs를 이용
// 방문처리를 이용하여 중복 탐색 경로를 줄임

class SolutionSwea1244
{
    // 완탐으로 찾은 결과 중 가장 큰 숫자
    static int maxNum;
    // 주어진 교체 횟수
    static int chance;
    // 자릿수
    static int strL;

    // 이미 만든 적 있는 숫자인지 확인 set
    static Set<String>[] visited;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        String numStr="";
        chance=-1;
        StringTokenizer st= null;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            maxNum=Integer.MIN_VALUE;
            st=new StringTokenizer(br.readLine());
            numStr=st.nextToken();
            strL=numStr.length();
            chance=Integer.parseInt(st.nextToken());

            // 3331에서 첫번째 3을 a 두번째 3을 b라고 둘 때,
            // ab31에 대해 1회 교체를 한다면
            // 3ba1(3331) 과 a3b1(3331)은 둘다 1회 교체를 했지만 같은 상태를 가짐
            // 즉 1회 교체시마다 중복되는 상태를 만드는 교체가 있고 이 중복 상태를 제거하지 않으면 경우의 수가 많아짐

            // 중복되는 상태를 처리하기 위한 set
            // visited[t]는 t회 교체에 중복되는 숫자 상태를 보여주는 변수
            visited = new HashSet[chance + 1];

            for (int i = 0; i <= chance; i++) {
                visited[i] = new HashSet<>();
            }

            // 처음 주어진 상태에서 교체 시작
            dfs(0,numStr);

            System.out.println("#"+test_case+" "+maxNum);
        }
    }

    // dfs(현재까지 진행한 교체 횟수, 현재 숫자 상태)
    static void dfs(int changeCnt, String numS) {

        // 동일한 교환 횟수에서 이미 탐색한 숫자라면 종료 (겹치는 탐색 경로)
        if (visited[changeCnt].contains(numS)) {
            return;
        }

        // 현재 상태를 visited[changeCnt]에 상태를 등록
        // 다음 탐색 시 (changeCnt-1)->(changeCnt)로 탐색 진행시
        // 중복 탐색을 막음
        visited[changeCnt].add(numS);

        // 주어진 교체 횟수 모두 이용
        if (changeCnt == chance) {
            maxNum = Math.max(maxNum, Integer.parseInt(numS));
            return;
        }

        // 문자열의 두개 문자 교체를 편하게 하기 위한 변환
        char[] arr = numS.toCharArray();

        // 조합으로 2개 수를 골라 교환
        // 교환하여 완성된 수를 dfs에 넣음
        for (int i = 0; i < strL - 1; i++) {
            for (int j = i + 1; j < strL; j++) {

                // 두 자리 교환
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                dfs(changeCnt + 1, new String(arr));

                // 원상 복구
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}

// 실패한 내 방식
// 현재 선택이 다음 선택을 하는데 있어서 최소를 보장한다는 확신이 없음
// <설계 방식>
// 주어진 숫자의 앞에서 부터 선택 정렬을 시행
// 맨 앞 index를 고르고 pq에 숫자가 높은 값을 뽑음
// 중복되는 숫자 ex)12333의 경우 pq에서 3을 3개 뽑아 후보Q에 넣음
// 후보Q를 모두 돌려 교체한 모든 케이스를 확인, 32133 32313 32331 중 제일 큰 경우를 골라 해당 케이스로 교체 진행

// 모든 자릿수의 정렬을 수행하면 남은 교체 기회에 대해서는 10의자리와 1의자리를 교체하거나 중복되는 수가 있다면 변경 없이 반환


//import java.util.*;
//import java.io.*;
//
//class SolutionSwea1244
//{
//    public static void main(String args[]) throws Exception
//    {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        int T;
//
//        T=Integer.parseInt(br.readLine());
//        int inputData=-1;
//        int chance=-1;
//        StringBuilder answerStr= null;
//
//        PriorityQueue<Num> pq=new PriorityQueue<>((a,b)-> Integer.compare(b.num,a.num));
//        Queue<Num> candidatesForChange=new ArrayDeque<>();
//
//
//        LinkedList<Num> dataList=new LinkedList<>();
//
//        int temp=-1;
//        Num tempNum=null;
//        int numSize=0;
//        int index=-1;
//
//        StringTokenizer st=null;
//
//        for(int test_case = 1; test_case <= T; test_case++)
//        {
//            candidatesForChange.clear();
//            pq.clear();
//            dataList.clear();
//            answerStr = new StringBuilder();
//
//            st=new StringTokenizer(br.readLine()," ");
//
//            inputData=Integer.parseInt(st.nextToken());
//            chance=Integer.parseInt(st.nextToken());
//
//            index=Integer.toString(inputData).length()-1;
//            while(inputData!=0){
//                temp=inputData%10;
//                tempNum=new Num(temp,index);
//                dataList.addFirst(tempNum);
//                pq.offer(tempNum);
//                inputData=inputData/10;
//                index--;
////                System.out.println("make list");
//            }
//
//            numSize=dataList.size();
//            Num lastNum=null;
//            Num firstNum=null;
//
//            int tryCnt=0;
//            // 1. 선택 정렬, 가장 크고/자릿수가 낮은 숫자를 가장 높은 자릿수로 가져온다
//            int sortIdx=0;
//
//            boolean isSorted=false;
//
//            // 정렬 반복
//            // 횟수를 다 썼거나
//            // 정렬을 완료 했거나
//            while( tryCnt<chance && sortIdx<numSize-1 && !pq.isEmpty()){
//
//                isSorted=false;
//                // 1. pq에서 해당 위치에 들어올 후보군 뽑기
//                firstNum=dataList.get(sortIdx);
//
//                tempNum=pq.poll();
//
//                if (tempNum == firstNum) {
//                    sortIdx++;
//                    continue;
//                }
//
//                candidatesForChange.offer(tempNum);
//
//                // 중복되는 수들 뽑기
//                while (!pq.isEmpty()
//                        && !candidatesForChange.isEmpty()
//                        && candidatesForChange.peek().num == pq.peek().num){
//
//                    tempNum=pq.poll();
//
//                    // 뽑은 후보군이 정렬할 위치에 존재하는 수라면
//                    // 다른 후보군들을 다음 정렬위치에 사용
//                    if(tempNum==firstNum) {
//                        sortIdx++;
//                        isSorted=true;
//                        while(!candidatesForChange.isEmpty())
//                            pq.offer(candidatesForChange.poll());
//                        break;
//                    }
//
//                    candidatesForChange.offer(tempNum);
//
//                }
//
//                if( isSorted ){
//                    continue;
//                }
//
//
//                // 2. 정렬할 위치와 후보군 위치를 교환하고 해당 방식이 가장 큰 수로 교체를 보장하는지 확인
//                temp=0;
//                String str="";
//                TreeMap<Integer, Num> maxMap=new TreeMap<>();
//                for (Num num : candidatesForChange) {
//                    str="";
//                    tempNum = num;
//                    str+=tempNum.num;
//                    for(int i=sortIdx+1;i<numSize;i++){
//                        if(i== tempNum.idx)
//                            str+=firstNum.num;
//                        else
//                            str+=dataList.get(i).num;
//                    }
//                    maxMap.put(Integer.parseInt(str),tempNum);
//                }
//
//                Num topNum=maxMap.get(maxMap.lastKey());
//
//                // 3. 가장 큰 수로 교체를 보장하는 수와 정렬할 위치를 바꾸고, 선택되지 않은 후보 군들을 pq에 다시 넣음
//                // case 3: 다름 선택 정렬
//                firstNum.idx=topNum.idx;
//                dataList.set(topNum.idx, firstNum);
//
//                topNum.idx= sortIdx;
//                dataList.set(sortIdx, topNum);
//                while(!candidatesForChange.isEmpty()){
//                    tempNum=candidatesForChange.poll();
//                    if(tempNum==topNum)
//                        continue;
//                    pq.offer(tempNum);
//                }
//
//                // 정렬 완료
//                tryCnt++;
//                sortIdx++;
//            }
//
//            boolean isDuplicated=false;
//            Set<Integer> doubleNumMap=new HashSet<>();
//            // 중복 여부 확인
//            for(int i=0;i<numSize;i++){
//                if(doubleNumMap.contains(dataList.get(i).num)){
//                    isDuplicated=true;
//                    break;
//                }
//                doubleNumMap.add(dataList.get(i).num);
//            }
//
//            // 정렬을 마친 후에 기회가 남는다면
//            if (tryCnt < chance
//                    && sortIdx >= numSize - 1
//                    && !isDuplicated
//                    && (chance - tryCnt) % 2 == 1){
//                lastNum=dataList.getLast();
//                lastNum.idx=dataList.size()-2;
//                tempNum=dataList.get(dataList.size()-2);
//                tempNum.idx=dataList.size()-1;
//
//                dataList.set(dataList.size()-1,tempNum);
//                dataList.set(dataList.size()-2,lastNum);
//            }
//
//            for(int i=0;i<dataList.size();i++){
//                answerStr.append(dataList.get(i).num);
////                System.out.println("make string");
//            }
//
//            System.out.printf("#%d %s\n",test_case, answerStr);
//
////            System.out.println("end test_case");
//        }
//    }
//}
//
//class Num{
//    int num;
//    int idx;
//    public Num(int n,int idx) {
//        this.num = n;
//        this.idx = idx;
//    }
//}



//// 1안 각 자리수 숫자를 객체로 만들어
//// pq로 숫자가 클수록, 같다면 자릿수가 작을수로 우선 순위를 높임
//// 숫자의 큰 자릿수부터 pq를 deque하여 교체함
//// 문제점 1 : 문제 조건 상 교환 횟수가 전체 자릿수 보다 많다
//// 문제점 2 : n번째 위치한 숫자를 같은 자리에 놓을 수 없다
//
//// 문제점 1 해결 방안 : pq에 있는 모든 수를 내림차순으로 정렬하면 1의 자리 수와 10의 자리 수만 계속 교체한다.
//// 문제점 2 해결 방안 : pq에서 뽑은 값이 위치한 자리와 현재 놓을려는 자리가 같다면 다음 pq를 뽑아 다음 자림에 둔다
////                  기저 조건으로 pq를 모두 뽑았다면 앞선 문제 1해결법을 실행한다.
//
//import java.util.*;
//import java.io.*;
//
//class SolutionSwea1244
//{
//    public static void main(String args[]) throws Exception
//    {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        int T;
//
//        T=Integer.parseInt(br.readLine());
//        int inputData=-1;
//        int chance=-1;
//        StringBuilder answerStr= null;
//
//        PriorityQueue<Num> pq=new PriorityQueue<>((a,b)->{
//            if(a.num!=b.num){
//                return Integer.compare(b.num,a.num);
//            }
//            else{
//                return Integer.compare(b.idx,a.idx);
//            }
//        });
//
//        LinkedList<Num> dataList=new LinkedList<>();
//        int temp=-1;
//        Num tempNum=null;
//        int size=0;
//        int index=-1;
//        System.out.println(T);
//        StringTokenizer st=null;
//        for(int test_case = 1; test_case <= T; test_case++)
//        {
//            pq.clear();
//            answerStr = new StringBuilder();
//            dataList.clear();
//            st=new StringTokenizer(br.readLine()," ");
//            inputData=Integer.parseInt(st.nextToken());
//            chance=Integer.parseInt(st.nextToken());
//
//            index=Integer.toString(inputData).length()-1;
//            while(inputData!=0){
//                temp=inputData%10;
//                tempNum=new Num(temp,index);
//                dataList.addFirst(tempNum);
//                pq.add(tempNum);
//                inputData=inputData/10;
//                index--;
////                System.out.println("make list");
//            }
//
//            size=dataList.size();
//            Num lastNum=null;
//            Num firstNum=null;
//
//            int tryCnt=-1;
//            // 1. 선택 정렬, 가장 크고/자릿수가 낮은 숫자를 가장 높은 자릿수로 가져온다
//            int sortIdx=0;
//
//            for(tryCnt = 0; tryCnt < size-1 && tryCnt < chance && pq.size()>1 && sortIdx <size-1; tryCnt++){
////                System.out.println("debugging");
//                tempNum=pq.poll();
//                firstNum=dataList.get(sortIdx);
//
//                // 정렬하기 위한 가장 큰 값을 뽑음
//                // case 1: 정렬할 자릿 수와 현재 이동 시킬려고 선택한 숫자의 자릿 수가 같음
//                // >> continue; + tryCnt--(이동을 안함으로)
//                // case 2: 정렬할 위치에 존재한 숫자와 현재 이동 시킬려고 선택한 숫자의 숫자가 같음
//                // >> pq에 현재 뽑은 숫자를 다시 넣음, continue, tryCnt--(이동을 안함으로)
//
//                if(tempNum.idx==sortIdx){
//                    tryCnt--;
//                    sortIdx++;
//                    continue;
//                }
//
//                if(tempNum.num==firstNum.num){
//                    pq.offer(tempNum);
//                    sortIdx++;
//                    tryCnt--;
//                    continue;
//                }
//
//                // case 3: 다름 선택 정렬
//                firstNum.idx=tempNum.idx;
//                dataList.set(tempNum.idx, firstNum);
//
//                tempNum.idx= sortIdx;
//                dataList.set(sortIdx, tempNum);
////                System.out.println("selection Sort");
//                sortIdx++;
//            }
//
//            // 2. chance가 전체 정렬을 마친 후에도 남을 경우 10의 자리와 1의 자리만 교체한다.
//            while(tryCnt<chance){
//                lastNum=dataList.getLast();
//                lastNum.idx=dataList.size()-2;
//                tempNum=dataList.get(dataList.size()-2);
//                tempNum.idx=dataList.size()-1;
//
//                dataList.set(dataList.size()-1,tempNum);
//                dataList.set(dataList.size()-2,lastNum);
//                tryCnt++;
//
////                System.out.println("repeat move");
//            }
//
//
//
//            for(int i=0;i<dataList.size();i++){
//                answerStr.append(dataList.get(i).num);
////                System.out.println("make string");
//            }
//
//            System.out.printf("#%d %s\n",test_case, answerStr.toString());
//
////            System.out.println("end test_case");
//        }
//    }
//}
//
//class Num{
//    int num;
//    int idx;
//    public Num(int n,int idx){
//        this.num=n;
//        this.idx=idx;
//    }
//}



//
//// 문제점 발견, 변경하는 횟수는 전체 자릿수 보다 길 수 있음
//// 기존 방식은 가장 큰 자릿수->1의 자리까지 1회 순회 함
//
//// pq에서 하나씩 뽑아서 이동, 모든 pq를 모두 뽑으면 정렬이 완료됨,
//                for(int i = 0; i<chance; i++){
//        // 이미 모든 것을 정렬한 상황이라면 가장 작은 자릿수와 10의 자릿수만 정렬
//        if( i >= size-1 || pq.size() == 1 ){
//
//        }
//
//
//
//// pq에서 뽑은 숫자의 자리와 현재 자릿수가 같으면 넘어감 (이미 정렬 완료 상태)
//// pq 크기가 1이면 정렬 완료된 상태
////                while(firstNum != tempNum  && pq.size() > 1 ){
////                    tempNum=pq.poll();
////                    firstNum=dataList.get(idxForSort++);
////                }
//
//// 정렬할 앞자리와 현재 pq에서 나온 번호의 자릿수가 같고,
//firstNum = dataList.get(idxForSort);
//
//                while (pq.size() > 1) {
//tempNum = pq.poll();
//
//                    if (tempNum != firstNum) {
//        break;
//        }
//        }
//
//        if (tempNum == null) {
//i--;
//        continue;
//        }
//
//
//        System.out.println("#chance "+i+" sortIdx "+idxForSort);
//                System.out.println("firstNum num idx "+firstNum.idx+" "+firstNum.num);
//                System.out.println("tempNum num idx "+tempNum.idx+" "+tempNum.num);
//
//
//
//
//
//
//
//
//// 가장 큰 숫자 객체가 있던 자리에 MSB 위치 숫자를 배정
//// MSB-.....-가장 큰 숫자를 가진 자릿수<할당-.....  -LSB
//firstNum.idx=tempNum.idx;
//                dataList.set(tempNum.idx,firstNum);
//
//// MSB<할당-.....-가장 큰 숫자를 가진 자릿수<할당-.....  -LSB
//tempNum.idx=idxForSort;
//                dataList.set(idxForSort,tempNum);
//
//idxForSort+=1;
//        }