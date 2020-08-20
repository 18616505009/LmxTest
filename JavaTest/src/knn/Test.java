package knn;

/**
 * @author lmx
 * @date 2020-07-10 13:30
 */
public class Test extends KnnClassification<Integer> {
    /**
     * 计算两个样本值的相似度,返回相似度得分
     *
     * @param o1 样本值1
     * @param o2 样本值2
     * @return 相似度得分
     */
    @Override
    public double similarScore(Integer o1, Integer o2) {
        return -1 * Math.abs(o1 - o2);
    }

    /**
     * @param args
     * @Author:lulei
     * @Description:
     */
    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 1; i < 10; i++) {
            test.addRecord(i, i > 5 ? "0" : "1");   //给KnnClassification-dataArray添加数据
        }
        System.out.println(JsonUtil.parseJson(test.getTypeId(10)));

    }
}
