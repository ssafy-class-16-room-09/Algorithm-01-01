import java.util.*;

class Solution {
    
    String id;
    
    public String solution(String new_id) {
        // 1단계 대문자를 소문자로 변환
        id = new_id.toLowerCase(Locale.ROOT);

        // 2단계 허용되지 않는 문자 제거
        id = id.replaceAll("[^a-z0-9._-]", "");

        // 3단계 연속된 마침표를 하나로 변경
        id = id.replaceAll("\\.{2,}", ".");

        // 4단계 처음과 끝의 마침표 제거
        id = id.replaceAll("^\\.|\\.$", "");

        // 5단계 빈 문자열이면 "a" 대입
        if (id.isEmpty()) {
            id = "a";
        }

        // 6단계 최대 15자로 자르고, 끝의 마침표 제거
        if (id.length() > 15) {
            id = id.substring(0, 15);
        }
        id = id.replaceAll("\\.$", "");

        // 7단계 길이가 3이 될 때까지 마지막 문자 반복
        while (id.length() < 3) {
            id += id.charAt(id.length() - 1);
        }

        return id;
    }
}