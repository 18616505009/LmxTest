/**
 * @author lmx
 * @date 2020-05-14 16:01
 */
public class LeetCodeTests {

    public static void main(String[] args) {
        System.out.println("" + reverse(-1534236469));
    }

    public static int reverse(int x) {

        int xcopy = Math.abs(x);
        int result = 0;

        int mi = (xcopy + "").length() - 1;
        for (; ; ) {
            result += (xcopy % 10) * Math.pow(10, mi);
            if(result>Integer.MAX_VALUE||result<Integer.MAX_VALUE)
                return 0;
            xcopy = xcopy / 10;
            if (xcopy == 0)
                break;
            mi--;
        }
        if (x < 0)
            result = -result;
        if (result > 2147483647 || result < -2147483648)
            result = 0;
        return result;
    }

    public static int reverseAsString(int x) {
        String str = x + "";

        boolean negativeNum = false;
        if (str.startsWith("-")) {
            negativeNum = true;
            str = str.substring(1, str.length());
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        if (negativeNum)
            str = "-" + sb.toString();
        else
            str = sb.toString();
        long result = Long.parseLong(str);
        if (result > 2147483647 || result < -2147483648)
            result = 0;
        return (int) result;
    }

    class Solution {
        public int reverse(int x) {

            int xcopy = Math.abs(x);
            int result = 0;

            int mi = (xcopy + "").length() - 1;
            for (; ; ) {
                result += (xcopy % 10) * Math.pow(10, mi);
                xcopy = xcopy / 10;
                if (xcopy == 0)
                    break;
                mi--;
            }
            if (x < 0)
                result = -result;
            return result;
        }
    }

}
