package lektion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.junit.jupiter.api.Test;

public class MongoClientsTest {

    @Test
    void showDatabases() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost");
        mongoClient.listDatabases().forEach(System.out::println);
    }

}