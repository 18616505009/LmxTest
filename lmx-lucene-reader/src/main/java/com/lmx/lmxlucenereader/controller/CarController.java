package com.lmx.lmxlucenereader.controller;

import com.lmx.lmxlucenewriter.data.Car;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmx
 * @date 2021/1/13 2:09 下午
 */
@Slf4j
@RestController
public class CarController {

    @Autowired
    IndexSearcher indexSearcher;

    //模糊查询接口
    @GetMapping("/fuzzy")
    public List<Car> getCars(@RequestParam(value = "brand", required = false) String brand) {
        ArrayList<Car> cars = new ArrayList<>();

        //查询词语-指定查询品牌属性
        Term term = new Term("brand", brand);
        //根据term词语对象 创建模糊查询
        FuzzyQuery fuzzyQuery = new FuzzyQuery(term);
        try {
            //指定模糊查询的最大返回结果数-10
            TopDocs topDocs = indexSearcher.search(fuzzyQuery, 10);
            //获取到匹配度最高的评分文件,包含评分、文档id
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for (ScoreDoc scoreDoc : scoreDocs) {
                //通过scoreDoc.doc提取文档id,再由indexSearcher根据文档id获取到文档对象
                Document document = indexSearcher.doc(scoreDoc.doc);
                String matchBrand = document.get("brand");
                String matchVersion = document.get("version");

                //无法索引long类型
                //打印每个索引文档的字段，发现long类型未被存储，无法查询到
                List<IndexableField> fields = document.getFields();
                for (IndexableField field : fields) {
                    log.info("field name=>" + field.name());
                }

                Car car = new Car(matchBrand, matchVersion, -1);
                cars.add(car);
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("模糊查询异常->" + e.toString());
        }
        return cars;
    }

}
