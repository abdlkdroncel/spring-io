package mongo.controoler;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import mongo.model.User;
import mongo.repository.UserRepo;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserApi {

    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody  User user){
        return ResponseEntity.ok(userRepo.save(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> allUser(){
        return ResponseEntity.ok(userRepo.findAll());
    }

    @PostMapping("/test")
    public ResponseEntity<String> saveOther(@RequestParam int x){
        try(MongoClient mongoClient= MongoClients.create("mongodb://localhost")) {
            MongoDatabase sample=mongoClient.getDatabase("mongo-rest");
            MongoCollection<Document> gradeColl=sample.getCollection("user");

            Document user=new Document("_id",new ObjectId());
            user.append("name","test");
            user.append("surname","test1");
            gradeColl.insertOne(user);
        }
       return ResponseEntity.ok("Okey");
    }
}
