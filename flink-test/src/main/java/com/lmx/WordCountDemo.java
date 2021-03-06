package com.lmx;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author lmx
 * @date 2020-05-27 21:46
 */
public class WordCountDemo {

    public static final String IN_FILE_PATH = "/Users/limengxiao/Desktop/空模版.txt";
    public static final String OUT_FILE_PATH = "/Users/limengxiao/Desktop/空模版2.csv";


    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<String> text = env.readTextFile(IN_FILE_PATH);

        DataSet<Tuple2<String, Integer>> counts =
                // 把每一行文本切割成二元组，每个二元组为: (word,1)
                text.flatMap(new Tokenizer())
                        // 根据二元组的第“0”位分组，然后对第“1”位求和
                        .groupBy(0)
                        .sum(1);

        counts.writeAsCsv(OUT_FILE_PATH, "\n", " ");

    }

    // 自定义函数
    public static class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // 统一大小写并把每一行切割为单词
            String[] tokens = value.toLowerCase().split("\\W+");

            // 消费二元组
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }


}
