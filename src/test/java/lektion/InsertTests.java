package lektion;

import java.util.stream.Stream;

import com.mongodb.WriteConcern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.StopWatch;

@SpringBootTest
public class InsertTests {

    @Autowired
    private MongoOperations mongoOps;

    @Test
    void insertViaMongoOps() {

        ((MongoTemplate) mongoOps).setWriteConcern(WriteConcern.UNACKNOWLEDGED);
        mongoOps.insert(new Person());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Insert via MongoTemplate");
        Stream.iterate(1, n -> n + 1).limit(10_000).forEach(i -> {
            Person me = new Person();
            me.name = "Christian Trutz";
            mongoOps.insert(me);
        });
        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
    }

}