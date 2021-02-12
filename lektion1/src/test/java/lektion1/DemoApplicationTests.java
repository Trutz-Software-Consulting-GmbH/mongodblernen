package lektion1;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootTest
class DemoApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    MongoClient mongoClient;

    @Autowired
    private MongoOperations mongoOps;

    @BeforeEach
    void beforeEach() {
        // Alle Datenbanken loggen
        mongoClient.listDatabaseNames().forEach(dbName -> LOG.info("Database {}", dbName));
    }

    @Test
    void insertFindTest() {
        // Ein Document einfügen
        MongoCollection<Document> personen = mongoOps.getCollection("personen");
        Document johndoe = new Document();
        johndoe.append("name", "John Doe");
        personen.insertOne(johndoe);

        // Zuvor eingefügtes Document finden
        personen.find(eq("name", "John Doe")).limit(1).iterator()
                .forEachRemaining(document -> LOG.info("name={}", document.get("name")));
    }

}
