package com.lmx.lmxlucenewriter.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * @author lmx
 * @date 2021/1/13 11:18 上午
 */
@Slf4j
@Component
public class LmxIndexWriter {

    //索引文件存放目录
    final String INDEX_DIRECTORY = "lmx-lucene-writer/index-data";
    Directory directory;

    @Bean
    public IndexWriter initWriter() {
        IndexWriter indexWriter = null;
        try {
            File indexDirectory = new File(INDEX_DIRECTORY);

            //清空该目录下文件
            File[] otherFiles = indexDirectory.listFiles();
            for (File otherFile : otherFiles) {
                otherFile.delete();
            }

            //设置索引目录-使用FS文件目录
            directory = FSDirectory.open(indexDirectory.toPath());
            //设置索引写入配置->指定使用中文分词器
            IndexWriterConfig indexWriterConfig
                    = new IndexWriterConfig(new IKAnalyzer());
            indexWriter = new IndexWriter(directory, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("初始化索引写入器异常->" + e.toString());
        }
        return indexWriter;
    }

}
