package mongo.repository;

import mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepo extends MongoRepository<User,String> {

}
