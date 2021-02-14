package lektion;

import com.mongodb.client.MongoClients;

import org.junit.jupiter.api.Test;

public class MongoClientsTest {

    @Test
    void showDatabases() {
        MongoClients.create("mongodb://localhost:27017/test") //
                .listDatabases() //
                .forEach(System.out::println);
    }

}