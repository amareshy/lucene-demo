package com.lucene.demo.config;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;

public class LuceneConfigManager
{
    private static LuceneConfigManager instance = null;
    static final String INDEX_DIR = "C:/User_Drive/Others/Study/Git_Hub/lucene-demo/lucene6index";

    public static LuceneConfigManager getInstance()
    {
        if (instance == null)
        {
            instance = new LuceneConfigManager();
        }
        return instance;
    }

    public IndexWriter createWriter() throws IOException
    {
        FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);

        return writer;
    }

    public Document createDocument(Integer id, String firstName, String lastName, String website)
    {
        Document document = new Document();
        document.add(new StringField("id", id.toString(), Field.Store.YES));
        document.add(new TextField("firstName", firstName, Field.Store.YES));
        document.add(new TextField("lastName", lastName, Field.Store.YES));
        document.add(new TextField("website", website, Field.Store.YES));

        return document;
    }

    public IndexSearcher createSearcher() throws IOException
    {
        FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        return searcher;
    }
}
