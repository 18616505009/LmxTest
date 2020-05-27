package com.lmx;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author lmx
 * @date 2020-05-26 21:55
 * <p>
 * 滑动窗口计算
 * <p>
 * 根据B站Flink入门实战
 * 通过socket 模拟产生单词数据
 * Flink对数据进行统计计算
 * 需要实现：
 * 每隔一秒，对最近两秒内的数据进行汇总计算
 */
public class SocketWindowMaker {

    public static void main(String[] args) throws Exception {

        String delimiter = "\n";
        int port = 9000;
        ParameterTool pt = ParameterTool.fromPropertiesFile("flink-test/src/main/resources/application.properties");
        port = pt.getInt("socket.port");   //控制台传参，一般读取配置就ok

        //1.获取flink运行环境，flink支持流式处理和批处理，当前需求需要使用流式处理，因此运行环境指明流式处理环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        //flink三大组建：source、transformation、sink
        //2.指定数据源，此处为socket，需要指定socket连接host、port和分隔符
        String hostname = "localhost";
        DataStream<String> socketTextStream = environment.socketTextStream(hostname, port, delimiter);

        //3.定义算子计算
        SingleOutputStreamOperator<WordWithCount> dataStream =
                socketTextStream.flatMap(new FlatMapFunction<String, WordWithCount>() {
                    @Override
                    public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                        String words[] = value.split(delimiter);
                        for (String word : words) {
                            out.collect(new WordWithCount(word, 1L));
                        }
                    }
                })
                        .keyBy("word")
                        .timeWindow(Time.seconds(2), Time.seconds(1))    //指定时间窗口大小-2sec，指定时间间隔1sec
                        .sum("count");

        //4.输出到sink，这里就打印到控制台了
        //将数据打印到控制台，并设置并行度
        dataStream.print().setParallelism(1);

        //5.执行程序
        environment.execute("socketWindowCount");
    }

    public static class WordWithCount {

        String word;
        long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "WordWithCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

}
