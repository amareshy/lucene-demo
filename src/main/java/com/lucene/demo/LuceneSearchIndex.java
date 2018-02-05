package com.lucene.demo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.lucene.demo.config.LuceneConfigManager;

public class LuceneSearchIndex
{
    public static void main(String[] args) throws Exception
    {
        LuceneConfigManager luceneConfigManager = LuceneConfigManager.getInstance();
        IndexSearcher searcher = luceneConfigManager.createSearcher();

        //======================== Search the text in index ================================//

        //Search By Id.
        TopDocs foundDocs = searchById(1, searcher);
        System.out.println("Total results : :  " + foundDocs.totalHits);

        ScoreDoc scoreDocs[] = foundDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs)
        {
            Document d = searcher.doc(scoreDoc.doc);
            System.out.println(String.format(d.get("firstName")));
        }

        //Search by firstName.
        foundDocs = searchByFirstName("Brian", searcher);
        System.out.println("Total results : :  " + foundDocs.totalHits);

        for (ScoreDoc scoreDoc : foundDocs.scoreDocs)
        {
            Document d = searcher.doc(scoreDoc.doc);
            System.out.println(String.format(d.get("id")) + " first name : " + String.format(d.get("firstName")));
        }
    }

    private static TopDocs searchByFirstName(String firstName, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("firstName", new StandardAnalyzer());
        Query firstNameQuery = qp.parse(firstName);
        TopDocs hits = searcher.search(firstNameQuery, 10);
        return hits;
    }

    private static TopDocs searchById(Integer id, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("id", new StandardAnalyzer());
        Query idQuery = qp.parse(id.toString());
        TopDocs hits = searcher.search(idQuery, 10);

        return hits;
    }

}
