package com.myorg.CardManagement.utility;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

@Component
public class QueryLoader {
    private final Map<String,String> queries = new HashMap<>();
    public QueryLoader(){
        loadQueries();
    }

    @PostConstruct
    private void loadQueries() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ClassPathResource("Query.xml").getInputStream());
            NodeList queryNodes = doc.getElementsByTagName("query");

            for (int i = 0; i < queryNodes.getLength(); i++) {
                Node node = queryNodes.item(i);
                String id = node.getAttributes().getNamedItem("id").getNodeValue();
                String queryText = node.getTextContent().trim();
                queries.put(id, queryText);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error loading queries from XML", e);
        }
    }

    public String getQuery(String id){
        return queries.get(id);
    }
}
