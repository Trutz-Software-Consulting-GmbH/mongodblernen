package lektion;

import com.mongodb.client.MongoClient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoClientAutowiredTest {

    @Autowired
    private MongoClient mongoClient;

    @Test
    void showDatabases() {
        mongoClient.listDatabases().forEach(System.out::println);
    }

}