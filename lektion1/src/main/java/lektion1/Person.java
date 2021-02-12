package lektion1;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class Person {
    @MongoId ObjectId id;
    String name;
}