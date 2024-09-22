public class LongestPalindrome {
    public static String longestPalindrome(String s) { //O(n*(2n)) T.C = O(n*n) T.C, O(1) S.C
        if(s.length() <= 1) return s;

        String palindrome = ""; //holds result
        for(int i = 0; i < s.length(); i++) { //O(n) T.C
            String odd = expand(i, i, s); //O(n) T.C, find largest odd length palindrome
            String even = expand(i, i+1, s); //O(n) T.C, largest even length palindrome

            if(odd.length() > palindrome.length()) palindrome = odd; //if odd is bigger than earlier palindrome
            if(even.length() > palindrome.length()) palindrome = even; //or even is bigger, assign new palindrome
        }
        return palindrome;
    }

    private static String expand(int left, int right, String s) {
        while(left >= 0 && right < s.length() //bounds check
                && s.charAt(left) == s.charAt(right)) { //character on each side is equal
            left--; //move towards left
            right++; //more towards right
        }
        //as we move left and right one step further after finding the palindrome, we need to offset the substring by 2
        return s.substring(left+1, right); //hence usual substring is (left, right+1) but remove 1 on each side
    }

    public static void main(String[] args) {
        String s = "pamidinitinkumar";

        System.out.println("The longest palindrome from the given String " + s + " is: " +
                longestPalindrome(s));
    }
}
