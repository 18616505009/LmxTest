package mlknn;

import java.util.*;

/**
 * @author lmx
 * @date 2020-07-10 15:06
 */
public class Dot2D {

    final int k = 3;
    ArrayList<Dot> sampleList;

    public static void main(String[] args) {

        Dot2D dot2D = new Dot2D();
        dot2D.sampleList = new ArrayList() {{
            add(new Dot(0.2, 1.5, "R"));
            add(new Dot(0.5, 0.5, "R"));
            add(new Dot(0.6, 0.4, "R"));

            add(new Dot(1, 1.5, "G"));
            add(new Dot(2, 1.5, "G"));
            add(new Dot(3, 1.5, "G"));

            add(new Dot(1.5, 0.5, "B"));
            add(new Dot(2, 0.9, "B"));
            add(new Dot(2, 0.3, "B"));
            add(new Dot(3, 0.3, "B"));
        }};

        List<Dot> nearestDots = dot2D.getNearestDot(new Dot(3, 2), dot2D.sampleList);

        int rCount = 0;
        int gCount = 0;
        int bCount = 0;

        for (Dot nearDot : nearestDots) {
            System.out.println("邻近点-->" + nearDot.getX() + "," + nearDot.getY() + "," + nearDot.getDotType());
            switch (nearDot.getDotType()) {
                case "R":
                    rCount++;
                    break;
                case "G":
                    gCount++;
                    break;
                case "B":
                    bCount++;
                    break;
                default:
                    break;
            }
        }

        int max = Math.max(Math.max(rCount, gCount), bCount);
        String type = "unknown";
        if (max == rCount) {
            type = "R";
        } else if (max == gCount) {
            type = "G";
        } else if (max == bCount) {
            type = "B";
        }

        System.out.println("目标样本所属类->" + type);

    }

    public List<Dot> getNearestDot(Dot targetDot, List<Dot> sampleList) {
        ArrayList<Dot> nearestDotList = new ArrayList();
        for (Dot sampleDot : sampleList) {
            double distance = diff(sampleDot, targetDot);

            //邻近点总数<要求邻近样本个数k时,直接添加样本
            if (nearestDotList.size() < k) {
                nearestDotList.add(sampleDot);
            } else {

                //找出目前邻近列表中差距最大的(最远点/相似度最低点)
                Dot farDot = nearestDotList.get(0);
                double farDiff = diff(targetDot, farDot);
                for (int i = 1; i < nearestDotList.size(); i++) {
                    double diff_i = diff(targetDot, nearestDotList.get(i));
                    if (farDiff < diff_i) { //更新最远点/最远距离
                        farDiff = diff_i;
                        farDot = nearestDotList.get(i);
                    }
                }

                //比较样本点相似度是否高于目前邻近点列表中相似度最低的，如果是，则替换
                if (farDiff > distance) {
                    nearestDotList.remove(farDot);
                    nearestDotList.add(sampleDot);
                }
            }
        }
        return nearestDotList;
    }

    //计算两点的差异,此处逻辑为判断距离(省略方根)
    public double diff(Dot dot1, Dot dot2) {
        return (Math.pow(dot1.getX() - dot2.getX(), 2) + Math.pow(dot1.getY() - dot2.getY(), 2));
    }

}

//点类
class Dot {

    String dotType;
    //横纵坐标
    private double x;
    private double y;

    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Dot(double x, double y, String dotType) {
        this.x = x;
        this.y = y;
        this.dotType = dotType;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getDotType() {
        return dotType;
    }
}

