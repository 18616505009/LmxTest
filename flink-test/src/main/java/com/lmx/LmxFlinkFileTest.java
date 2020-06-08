package com.lmx;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author lmx
 * @date 2020-05-27 23:10
 */
public class LmxFlinkFileTest {

    public static final String IN_FILE_PATH = "/Users/limengxiao/Desktop/空模版.txt";
    public static final String OUT_FILE_PATH = "/Users/limengxiao/Desktop/空模版2.csv";

    public static void main(String[] args) throws Exception {

        // the port to connect to
        final int port = 9000;

        // get the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // get input data by connecting to the socket
//        DataStream<String> text = env.socketTextStream("localhost", port, "\n");
        DataStream<String> text = env.readTextFile(IN_FILE_PATH);

        // parse the data, group it, window it, and aggregate the counts
        DataStream<WordWithCount> windowCounts = text
                .flatMap(flatMapFunc)
                .keyBy("word")
                .countWindow(10)
//                .timeWindow(Time.seconds(5), Time.seconds(1))
                .reduce(reduceFunc);

        // print the results with a single thread, rather than in parallel
        windowCounts.print().setParallelism(1);

        env.execute("Socket Window WordCount");
    }

    static FlatMapFunction flatMapFunc = new FlatMapFunction<String, WordWithCount>() {
        @Override
        public void flatMap(String value, Collector<WordWithCount> out) {
            System.out.println("value-" + value);
            for (String word : value.split(",")) {
                out.collect(new WordWithCount(word, 1L));
            }
        }
    };

    static ReduceFunction reduceFunc = new ReduceFunction<WordWithCount>() {
        @Override
        public WordWithCount reduce(WordWithCount a, WordWithCount b) {
            System.out.println("a-" + a + "\t\tb-" + b);
            return new WordWithCount(a.word, a.count + b.count);
        }
    };

    // Data type for words with count
    public static class WordWithCount {

        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }
}

