package com.lmx.lmxlucenewriter.service;

import com.lmx.lmxlucenewriter.data.Car;
import com.lmx.lmxlucenewriter.data.TestData;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmx
 * @date 2021/1/13 11:27 上午
 */
@Slf4j
@Service
public class WriteService {

    final String INDEX_DIRECTORY = "lmx-lucene-writer/index-data";

    @Autowired
    TestData testData;

    @Autowired
    IndexWriter lmxIndexWriter;

    public void writerTestData() {

        //获取测试数据
        ArrayList<Car> cars = testData.getCars();
        //准备要写入索引的文档list
        List<Document> documents = new ArrayList<>();
        for (Car car : cars) {  //遍历测试数据
            Document document = new Document();
            document.add(new TextField("brand", car.getBrand(), Field.Store.YES));
            document.add(new TextField("version", car.getVersion(), Field.Store.YES));
            document.add(new NumericDocValuesField("price", car.getPrice()));

            documents.add(document);
        }
        try {
            lmxIndexWriter.addDocuments(documents); //批量写入文档,比addDocument的单独写入效率高
            lmxIndexWriter.commit();    //提交
            lmxIndexWriter.close(); //关闭
        } catch (IOException e) {
            e.printStackTrace();
            log.error("写文档失败->" + e.toString());
        }
        log.info("写文档完成");
    }

}
