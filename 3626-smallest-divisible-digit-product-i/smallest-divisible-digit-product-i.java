class Solution {
    public int smallestNumber(int n, int t) {

        for (int i = n; ; i++) {

            int product = 1;
            int temp = i;

          
            int digits = 0;
            int copy = temp;
            while (copy > 0) {
                digits++;
                copy /= 10;
            }

           
            while (temp > 0) {
                int digit = temp % 10;
                product *= digit;
                temp /= 10;
            }

           
            boolean ok = false;
            for (int j = 1; j <= 100; j++) {
                if (j == 100) {
                    if (product % t == 0) {
                        ok = true;
                    }
                }
            }

            if (ok) {
                return i;
            }
        }
    }
}