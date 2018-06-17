public class Wildcard_Matching {

    public static void main(String[] args){
//        String s = "aa";
//        String p = "*";

//        String s = "babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab";
//        String p = "***bba**a*bbba**aab**b";

//        String s = "adceb";
//        String p = "*a*b";

        String s = "aab";
        String p = "c*a*b";

//        String s = "mississippi";
//        String p = "mis*is*p*?";

        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        return isMatch2(s, p);
    }


    /**
     * 同 Regular Expression Matching
     */
    public static boolean isMatch(int i, int j, String s, String p){
        if(i < 0 || j <= 0) return false;
        System.out.println("i = " + i + ", j = " + j + ", s = " + s.substring(0, i) + ", p = " + p.substring(0, j));
        if(i == 0 && j == 0) return true;

        String s1 = i == 0 ? "" : s.substring(i-1, i);
        String p1 = p.substring(j-1, j);

        if(p1.equals("*")){
            return isMatch(i, j - 1, s, p)
                    || isMatch(i - 1, j - 1, s, p)
                    || isMatch(i - 1, j, s, p);
        } else {
            if(s1.equals(p1) || p1.equals("?")){
                return isMatch(i - 1, j - 1, s, p);
            } else {
                return false;
            }
        }
    }

    public static boolean isMatch2( String s, String p){

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        for(int i = 0; i <= s.length(); i++){
            for(int j = 0; j <= p.length(); j++){
                if(i == 0 && j == 0) dp[i][j] = true;
                else if(j == 0) dp[i][j] = false;
                else if(i == 0){
                    if( !p.substring(j - 1, j).equals("*")){
                        dp[i][j] = false;
                    } else {
                        dp[i][j] = dp[0][j - 1];
                    }
                } else {
                    String si = s.substring(i - 1, i);
                    String pj = p.substring(j - 1, j);
                    if(pj.equals("*")){
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j];
                    } else {
                        if(si.equals(pj) || pj.equals("?")){
                            dp[i][j] = dp[i - 1][j - 1];
                        } else {
                            dp[i][j] = false;
                        }
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
