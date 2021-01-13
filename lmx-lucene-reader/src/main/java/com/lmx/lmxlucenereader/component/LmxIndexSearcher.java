package com.lmx.lmxlucenereader.component;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author lmx
 * @date 2021/1/13 1:57 下午
 */
@Component
public class LmxIndexSearcher {

    //索引文件存放目录
    final String INDEX_DIRECTORY = "lmx-lucene-writer/index-data";

    @Bean
    public IndexSearcher getIndexSearcher() {
        IndexSearcher indexSearcher = null;
        File indexDir = new File(INDEX_DIRECTORY);
        try {
            Directory directory = FSDirectory.open(indexDir.toPath());
            IndexReader indexReader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(indexReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexSearcher;
    }

}
