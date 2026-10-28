import java.util.*;

// GPT에 물어본 것들
// 문자열과 배열 인덱스의 관계 - 연습이 필요해 보임
// 람다식과 Comparator - 연습이 필요해보임


class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

        String[] columns = {"code", "date", "maximum", "remain"};

        int extIndex = 0;
        int sortIndex = 0;

        // 1. 문자열을 인덱스로 변환
        for (int i = 0; i < 4; i++) {
            if (columns[i].equals(ext)) {
                extIndex = i;
            }

            if (columns[i].equals(sort_by)) {
                sortIndex = i;
            }
        }

        // 2. 조건에 맞는 데이터만 추출
        List<int[]> list = new ArrayList<>();

        for (int[] row : data) {
            if (row[extIndex] < val_ext) {
                list.add(row);
            }
        }

        // 3. 오름차순 정렬
        final int index = sortIndex;
        list.sort((a, b) -> Integer.compare(a[index], b[index]));

        // 4. 배열로 변환해서 반환
        return list.toArray(new int[0][]);
    }
}