package knn;

/**
 * @author lmx
 * @date 2020-07-10 13:21
 * 最近邻样本对象,包含类别和得分
 */
public class KnnSample {
    private String typeId;//分类ID
    private double score;//该分类得分

    public KnnSample(String typeId, double score) {
        this.typeId = typeId;
        this.score = score;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
