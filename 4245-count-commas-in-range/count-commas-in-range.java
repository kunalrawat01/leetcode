class Solution {
    public int countCommas(int n) {
        int count = 0;
        int i=1;
        while(i <= n){
            if(i >= 1000){
                count++;
            }
            i++;
        }
        return count;
    }
}