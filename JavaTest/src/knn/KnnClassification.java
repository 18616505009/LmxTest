package knn;

import java.util.*;

/**
 * @author lmx
 * @date 2020-07-10 13:28
 */
@SuppressWarnings({"rawtypes"})
public abstract class KnnClassification<T> {
    private List<KnnValueBean> dataArray;
    private int K = 3;

    public int getK() {
        return K;
    }

    public void setK(int K) {
        if (K < 1) {
            throw new IllegalArgumentException("K must greater than 0");
        }
        this.K = K;
    }

    /**
     * @param value
     * @param typeId
     * @Author:lulei
     * @Description: 向模型中添加记录
     */
    public void addRecord(T value, String typeId) {
        if (dataArray == null) {
            dataArray = new ArrayList<KnnValueBean>();
        }
        dataArray.add(new KnnValueBean<T>(value, typeId));
    }

    /**
     * @param targetValue 目标样本的值
     * @return
     * @Author:lulei
     * @Description: KNN分类判断value的类别
     */
    public String getTypeId(T targetValue) {
        KnnSample[] array = getKType(targetValue);  //获取目标targetValue的k个邻近样本

        for (KnnSample sample : array) {
            System.out.println("nearest sample-->" + sample.getTypeId() + "," + sample.getScore());
        }

        System.out.println(JsonUtil.parseJson(array));
        HashMap<String, Integer> map = new HashMap<String, Integer>(K);

        //检查k个最近样本中最多的类
        for (KnnSample bean : array) {
            if (bean != null) {
                if (map.containsKey(bean.getTypeId())) {
                    map.put(bean.getTypeId(), map.get(bean.getTypeId()) + 1);
                } else {
                    map.put(bean.getTypeId(), 1);
                }
            }
        }
        String maxTypeId = null;
        int maxCount = 0;
        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            if (maxCount < entry.getValue()) {
                maxCount = entry.getValue();
                maxTypeId = entry.getKey();
            }
        }
        return maxTypeId;
    }

    /**
     * @param value
     * @return
     * @Author:lulei
     * @Description: 获取距离最近的K个邻近样本
     */
    private KnnSample[] getKType(T value) {
        int index = 0;
        //初始化样本数组,长度为K-近邻样本数(3个样本的数组)
        KnnSample[] sampleArr = new KnnSample[K];


        for (KnnValueBean<T> bean : dataArray) {
            //计算每一个样本与value值的相似度得分,样本值与目标值的相似度得分方法可以自定义
            double score = similarScore(bean.getValue(), value);
            if (index == 0) {
                //数组中的记录个数为0是直接添加
                sampleArr[index] = new KnnSample(bean.getTypeId(), score);
                index++;
            } else {
                if (!(index == K && score < sampleArr[index - 1].getScore())) {
                    int i = 0;
                    //找到要插入的点
                    for (; i < index && score < sampleArr[i].getScore(); i++) ;
                    int j = index - 1;
                    if (index < K) {
                        j = index;
                        index++;
                    }
                    for (; j > i; j--) {
                        sampleArr[j] = sampleArr[j - 1];
                    }
                    sampleArr[i] = new KnnSample(bean.getTypeId(), score);
                }
            }
        }
        return sampleArr;
    }

    /**
     * @param o1
     * @param o2
     * @return
     * @Author:lulei
     * @Description: o1 o2之间的相似度
     */
    public abstract double similarScore(T o1, T o2);
}
