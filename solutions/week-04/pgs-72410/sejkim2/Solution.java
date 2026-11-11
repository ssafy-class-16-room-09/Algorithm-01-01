class Solution {
    public String solution(String new_id) {
        //1
        String s1 = new_id.toLowerCase();
        
        //2
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            if (Character.isLowerCase(ch) || Character.isDigit(ch) || ch == '-' || ch == '_' || ch == '.')
                sb.append(ch+"");
        }
        String s2 = sb.toString();
        
        //3
        while (true) {
            if (s2.length() == 0)
                break;
            int beforeLen = s2.length();
            s2 = s2.replace("..", ".");
            if (s2.length() == beforeLen)
                break;
        }
        String s3 = s2;
        
        //4
        if (s3.length() >= 1 && s3.charAt(0) == '.')
           s3 = s3.substring(1, s3.length());
        if (s3.length() >= 1 && s3.charAt(s3.length() - 1) == '.')
           s3 = s3.substring(0, s3.length() - 1);
        String s4 = s3;
        
        //5
        if (s4.length() == 0)
            s4 += "a";
        String s5 = s4;
        
        //6
        if (s5.length() >= 16) {
            s5 = s5.substring(0, 15);
            if (s5.charAt(s5.length() - 1) == '.')
                s5 = s5.substring(0, s5.length() - 1);
        }
        String s6 = s5;
        
        //7
        if (s6.length() <= 2) {
            while (s6.length() != 3) {
                s6 += (s6.charAt(s6.length() - 1)+"");
            }
        }
        
        return s6;
    }
}