# [10. Wildcard Matching](https://leetcode.com/problems/wildcard-matching/description/)

Given an input string (`s`) and a pattern (`p`), implement regular expression matching with support for `?` and `*`.

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).

The matching should cover the **entire** input string (not partial).

#### Note:

* `s` could be empty and contains only lowercase letters `a-z`.
* `p` could be empty and contains only lowercase letters `a-z`, and characters like `?` or `*`.



#### Example 5:

    Input:
    s = "babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab"
    p = "***bba**a*bbba**aab**b"
    Output: false



## 解题思路

类似 [10. Regular Expression Matching](/docs/Regular_Expression_Matching.md)，只是原来的`.`变成了`?`，`*`的意义变了。

一开始还是用之前的递归，不过在上面的用例中超时了，因为从末往前递归会计算很多重复的情况，所以需要一个中间表，同头开始，记录每个子串的情况，后续可以直接使用，用空间换时间。

dp[i][j] 保存s<sub>i</sub>与p<sub>j</sub>的匹配情况，i、j分别表示子串长度

p[j-1]!="*" && s[i-1]==p[j-1]， dp[i][j] = dp[i-1][j-1】

p[j-1]!="*":

* 出现0次，dp[i][j] = dp[i][j-1]
* 出现1次，dp[i][j] = dp[i-1][j-1]
* 1次以上，dp[i][j] = dp[i-1][j]

需要注意的是i、j为0的边界情况，最后返回原始长度的结果就行了


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


