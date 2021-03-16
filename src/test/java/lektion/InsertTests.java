package lektion;

import java.util.stream.Stream;

import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class InsertTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertTests.class);

    @Autowired
    private MongoClient mongoClient;

    private static StopWatch stopWatch;

    @BeforeAll
    static void beforeAll() {
        stopWatch = new StopWatch("InsertTests stop watch");
    }

    @AfterAll
    static void afterAll() {
        LOGGER.debug(stopWatch.prettyPrint());
    }

    @Test
    void insertViaNativeAPI() {
        MongoCollection<Document> personen = mongoClient.getDatabase("test").getCollection("personen");
        stopWatch.start(
                "10.000 Dokumente hinzugefügt, WriteConcern undefiniert = w:1, j:false");
        Stream.iterate(1, n -> n + 1).limit(10_000).forEach(i -> {
            Document me = new Document();
            me.append("name", "Christian Trutz");
            personen.insertOne(me);
        });
        stopWatch.stop();
    }

    @Test
    void insertViaNativeAPIAndWriteConcernUNACKNOWLEDGED() {
        MongoCollection<Document> personen = mongoClient.getDatabase("test").getCollection("personen")
                .withWriteConcern(WriteConcern.UNACKNOWLEDGED);
        stopWatch.start(
                "10.000 Dokumente hinzugefügt, WriteConcern = UNACKNOWLEDGED = w:0,j:false");
        Stream.iterate(1, n -> n + 1).limit(10_000).forEach(i -> {
            Document me = new Document();
            me.append("name", "Christian Trutz");
            personen.insertOne(me);
        });
        stopWatch.stop();
    }

    @Test
    void insertViaNativeAPIAndWriteConcernJOURNALED() {
        MongoCollection<Document> personen = mongoClient.getDatabase("test").getCollection("personen")
                .withWriteConcern(WriteConcern.JOURNALED);
        stopWatch.start(
                "10.000 Dokumente hinzugefügt, WriteConcern = JOURNALED = w:1,j:true");
        Stream.iterate(1, n -> n + 1).limit(10_000).forEach(i -> {
            Document me = new Document();
            me.append("name", "Christian Trutz");
            personen.insertOne(me);
        });
        stopWatch.stop();
    }

}