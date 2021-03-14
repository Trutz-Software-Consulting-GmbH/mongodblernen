package lektion;

import java.util.stream.Stream;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {

    @Autowired
    private MongoClient mongoClient;

    @Test
    void insertViaNativeAPI() {
        Document me = new Document();
        me.append("name", "Christian Trutz");
        MongoCollection<Document> personen = mongoClient.getDatabase("test").getCollection("personen");
        Stream.iterate(1, n -> n + 1).limit(10_000).forEach(i -> {
            personen.insertOne(me);
        });
    }

}