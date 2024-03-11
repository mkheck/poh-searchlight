package com.thehecklers.pohsearchlight;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SearchLightController {
    private final VectorStore vectorStore;

    public SearchLightController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping
    public String hello() {
        return "Hello, POH Searchlight!";
    }

    @GetMapping("/search")
    //public Iterable<Document> search(@RequestParam(defaultValue = "Airspeeds") String query) {
    //public String search(@RequestParam(defaultValue = "Airspeeds") String query) {
    public Iterable<String> search(@RequestParam(defaultValue = "Airspeeds") String query) {
        System.out.println("Query: " + query);
        var documents = vectorStore.similaritySearch(query);
        System.out.println("Number of documents retrieved: " + documents.size());
        System.out.println("------------------------------------");

        // Convert to list of strings
        var docList = new ArrayList<String>();
        documents.forEach(d -> docList.add(d.getFormattedContent()));
        return docList;
    }
}
