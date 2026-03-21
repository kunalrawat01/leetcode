class Solution {
    public boolean checkIfPangram(String sentence) {
        int count = 0;
        for(int ch = 'a';ch<='z';ch++){
            boolean found = false;
        for(int i=0;i<sentence.length();i++){
            char c = sentence.charAt(i);
            if (c >= 'A' && c <= 'Z'){
                c = (char)(c + 32);
            }
            if (c == ch){
                found = true;
                break;
               }
            }
             if (found){
                count++;
            }
        }
        if (count == 26){
            return true;
        }else{
            return false;
        }
    }
}
