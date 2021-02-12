package lektion1;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

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

    @Autowired
    private PersonRepository personRepository;

    @Test
    void insertFindPlain() {

        String name = "John Doe " + System.currentTimeMillis();

        // Ein Document einfügen
        MongoCollection<Document> personen = mongoOps.getCollection("personen");
        Document johndoe = new Document();
        johndoe.append("name", name);
        personen.insertOne(johndoe);

        // Zuvor eingefügtes Document finden
        Document johndoeFound = personen.find(eq("name", name)).limit(1).iterator().tryNext();
        assertNotNull(johndoeFound);
        assertEquals(johndoe, johndoeFound);
    }

    @Test
    void insertFindSpring() {

        String name = "John Doe " + System.currentTimeMillis();

        // Ein Document einfügen
        Person johndoe = new Person();
        johndoe.name = name;
        mongoOps.insert(johndoe);

        // Zuvor eingefügtes Document finden
        Person johndoeFound = mongoOps.findOne(query(where("name").is(name)), Person.class);
        assertNotNull(johndoeFound);
        assertEquals(johndoe.id, johndoeFound.id);
    }

    @Test
    void insertFindSpringRepository() {

        String name = "John Doe " + System.currentTimeMillis();

        // Ein Document einfügen
        Person johndoe = new Person();
        johndoe.name = name;
        personRepository.save(johndoe);

        // Zuvor eingefügtes Document finden
        Person johndoeFound = personRepository.findFirstByName(name);
        assertNotNull(johndoeFound);
        assertEquals(johndoe.id, johndoeFound.id);
    }

}
