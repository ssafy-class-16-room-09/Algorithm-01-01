import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = foo7(foo6(foo5(foo4(foo3(foo2(foo1(new_id)))))));        
        return answer;
    }
    
    String foo1(String str){        
        return str.toLowerCase();
    }
    String foo2(String str){
        StringBuilder sb = new StringBuilder();
        for(char c: str.toCharArray()){
            if ( (97 <= c-0 && c-0 <= 122) || (48 <= c-0 && c-0 <= 57)
                || c=='-' || c=='_' || c=='.') sb.append(c);
        }
        return sb.toString();
    }
    String foo3(String str){
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for(char c: str.toCharArray()){
            if (c != '.'){
                stack.push(c); 
                sb.append(c);
                
            }else if (!stack.isEmpty()){
                char prev = stack.peek();
                if (prev != '.'){                    
                    stack.push('.');
                    sb.append(c);
                } 
                else continue;
            }
        }
        return sb.toString();        
    }
    String foo4(String str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (i == 0 || i == str.length()-1){
                if (c == '.') continue;
                else sb.append(c);
            }else{
                sb.append(c);
            }
        }                
        return sb.toString();        
    }
    String foo5(String str){
        if (str.length() == 0) return "a";
        else return str;
    }
    String foo6(String str){
        if (str.length() >= 16){
            String newstr = str.substring(0,15);              
            if (newstr.charAt(14) == '.') return str.substring(0,14);
            else return newstr;
        } 
        else return str;
    }
    String foo7(String str){
        StringBuilder sb = new StringBuilder();
        if (str.length() == 1){
            char c = str.charAt(0);
            sb.append(c);
            sb.append(c);
            sb.append(c);
        }else if(str.length() == 2){
            char s = str.charAt(0);
            char c = str.charAt(1);
            sb.append(s);
            sb.append(c);
            sb.append(c);
        }else return str;
        return sb.toString();
        
    }
}