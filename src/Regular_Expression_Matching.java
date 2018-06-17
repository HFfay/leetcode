public class Regular_Expression_Matching {

    public static boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        boolean _f1 = false, _f2 = false;
        while (i < s.length() && j < p.length()){
            String s1 = s.substring(i, i + 1);
            String p1 = p.substring(j, j + 1);
            _f2 = false;
            if(j + 1 < p.length()){
                String p2 = p.substring(j + 1, j + 2);
                _f2 = p2.equals("*");
            }
            if(_f2){
                if(s1.equals(p1) || p1.equals(".")){
                    i++;
                } else {
                    j += 2;
                }
            } else {
                if(s1.equals(p1) || p1.equals(".")){
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }
        j += _f1 && _f2 ? 2 : 0;
        if(i == s.length() && j == p.length()) {
            return true;
        }
        return false;
    }



    public static void main(String[] args){
//        String s = "aa";
//        String p = "a*";

        String s = "aaa";
        String p = "a*a";

//        String s = "ab";
//        String p = ".*c";

//        String s = "aab";
//        String p = "c*a*b";

//        String s = "mississippi";
//        String p = "mis*is*p*.";

        System.out.println(isMatch2(s, p));
    }





    public static boolean isMatch2(String s, String p) {
        return isMatch(s.length(), p.length(), s, p);
    }


    public static boolean isMatch(int i, int j, String s, String p){
        if(i == 0 && j == 0) return true;
        if(i < 0 || j <= 0) return false;

        String s1 = i == 0 ? "" : s.substring(i-1, i);
        String p1 = p.substring(j-1, j);

        if(p1.equals("*")){
            String p2 = p.substring(j-2, j-1);
            if(s1.equals(p2) || p2.equals(".")){
                return isMatch(i, j - 2, s, p)
                        || isMatch(i - 1, j - 2, s, p)
                        || isMatch(i - 1, j, s, p);
            } else {
                return isMatch(i, j - 2, s, p);
            }
        } else {
            if(s1.equals(p1) || p1.equals(".")){
                return isMatch(i - 1, j - 1, s, p);
            } else {
                return false;
            }
        }
    }

}
