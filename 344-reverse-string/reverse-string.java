class Solution {
    public void reverseString(char[] s) {
        String result = "";
        for(int i=s.length-1;i>=0;i--){
            result += s[i];
        }
        for(int i=0;i<s.length;i++){
            s[i] = result.charAt(i);
        }
    }
}