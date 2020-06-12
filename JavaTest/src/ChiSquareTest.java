/**
 * @author lmx
 * @date 2020-06-11 15:18
 * 卡方pearson测试,网摘的，期望计算方式与2行2列的有出入
 */
public class ChiSquareTest {

    public static void main(String[] args) {
        System.out.println("ChiSquare->"+ChiSquare(23,17,50));
    }

    public static double ChiSquare(double... values) {
        int n = values.length;
        double[] O = new double[n];
        double[] E = new double[n];
        double[] D = new double[n];
        double[] OESQ = new double[n];
        double[] OESQE = new double[n];

        double oSum = 0;
        double OESQESum = 0;
        for (int i = 0; i < n; i++) {
            O[i] = values[i];
            oSum = oSum + values[i];
        }
        for (int i = 0; i < n; i++) {
            E[i] = oSum / n;    //oSum=90,n=3
            D[i] = O[i] - (oSum / n);
            OESQ[i] = Math.pow((O[i] - (oSum / n)), 2);
            OESQE[i] = Math.pow((O[i] - (oSum / n)), 2) / (oSum / n);
            OESQESum = OESQESum + Math.pow((O[i] - (oSum / n)), 2) / (oSum / n);
        }
        return OESQESum;
    }

}
