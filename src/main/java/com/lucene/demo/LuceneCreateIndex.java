package com.lucene.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;

import com.lucene.demo.config.LuceneConfigManager;

public class LuceneCreateIndex
{

    public static void main(String[] args) throws IOException, ParseException
    {
        LuceneConfigManager luceneConfigManager = LuceneConfigManager.getInstance();

        //======================== Create Index on file system(MMapDirectory) ======================================//
        IndexWriter writer = luceneConfigManager.createWriter();

        List<Document> documents = new ArrayList<>();
        Document document1 = luceneConfigManager.createDocument(1, "Lokesh", "Gupta", "howtodoinjava.com");
        documents.add(document1);
        Document document2 = luceneConfigManager.createDocument(2, "Brian", "Schultz", "example.com");
        documents.add(document2);

        //Let's clean everything first
        writer.deleteAll();

        writer.addDocuments(documents);
        writer.commit();
        writer.close();

    }
}
