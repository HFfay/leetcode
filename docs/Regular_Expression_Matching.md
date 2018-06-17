# [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/description/)

Given an input string (`s`) and a pattern (`p`), implement regular expression matching with support for '`.`' and '`*`'.

    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.

The matching should cover the **entire** input string (not partial).

#### Note:

* `s` could be empty and contains only lowercase letters `a-z`.
* `p` could be empty and contains only lowercase letters `a-z`, and characters like `.` or `*`.

#### Example 4:

    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".

#### Example 5:

    Input:
    s = "mississippi"
    p = "mis*is*p*."
    Output: false



## 解题思路

最开始的想法是模拟：从头开始比较，当字符相同时，下标同时往前推进；当遇到`*`时，s的下标推进，p的下标直到不一样才推进。但是这种方式过不了某些用例，例如：`s = "aaa"，p = "a*a"`

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

关键在于`*`对应的字符需要重复的次数是不确定的，可能0次，可能1次，可能很多次。

把`s`、`p`都拆分成子串：

s<sub>i</sub> = s<sub>i-1</sub> + s[i-1]

p<sub>j</sub> = p<sub>j-1</sub> + p[j-1]


如果p[j-1]!='*'，因为s[i-1]!=p[j-1]的话，`s`、`p`就一定不匹配了，所以下面就默认s[i-1]==p[j-1]，那么s[i-1]、p[j-1]可以丢弃，可以转换成s<sub>i-1</sub>、p<sub>j-1</sub>的解

如果p[j-1]=='*'，那么要看p[j-2]的情况：

s[i-1]!=p[j-2]情况下，那么p[j-1]、p[j-2]丢弃，转换成s<sub>i</sub>、p<sub>j-2</sub>的解

s[i-1]==p[j-1]情况下：

* 出现0次，那么p[j-1]、p[j-2]丢弃，转换成s<sub>i</sub>、p<sub>j-2</sub>的解
* 出现1次，那么s[i-1]、p[j-1]、p[j-2]丢弃，转换成s<sub>i-1</sub>、p<sub>j-2</sub>的解
* 1次以上，那么s[i-1]丢弃，转换成s<sub>i-1</sub>、p<sub>j</sub>的解


可以看到，求`s`、`p`是否匹配的问题可以转换成求一定条件下子串是否匹配的问题，可以尝试递归求解


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



