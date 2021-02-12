package lektion1;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private MongoOperations mongoOps;

    @Test
    void insertFindTest() {
        // Ein Document einfügen
        MongoCollection<Document> personen = mongoOps.getCollection("personen");
        Document johndoe = new Document();
        johndoe.append("name", "John Doe");
        personen.insertOne(johndoe);

        // Zuvor eingefügtes Document finden
        Document johndoeFound = personen.find(eq("name", "John Doe")).limit(1).iterator().tryNext();
        assertNotNull(johndoeFound);
        assertEquals(johndoe, johndoeFound);
    }

}
