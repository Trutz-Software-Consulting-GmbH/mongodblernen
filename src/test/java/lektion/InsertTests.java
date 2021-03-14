package lektion;

import java.util.stream.Stream;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
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

    @Test
    void insertViaNativeAPI() {
        MongoCollection<Document> personen = mongoClient.getDatabase("test").getCollection("personen");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("10.000 Dokumente mit Hilfe der nativen MongoDB Java API");
        Stream.iterate(1, n -> n + 1).limit(10_000).forEach(i -> {
            Document me = new Document();
            me.append("name", "Christian Trutz");
            personen.insertOne(me);
        });
        stopWatch.stop();
        LOGGER.debug("10.000 Dokumente mit Hilfe der nativen MongoDB Java API hinzugefügt.");
        LOGGER.debug(stopWatch.prettyPrint());
    }

}