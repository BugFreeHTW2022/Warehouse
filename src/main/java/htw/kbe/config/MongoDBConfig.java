package htw.kbe.config;



import htw.kbe.repository.ComponentRepository;
import htw.kbe.repository.ProductRepository;
import com.mongodb.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@EnableMongoRepositories(basePackageClasses = { ComponentRepository.class, ProductRepository.class})
@Configuration
public class MongoDBConfig {

    @EventListener(ApplicationReadyEvent.class)
    void test() {
        initializeProductsDB();
        initializeComponentsDB();
    }

    public static void initializeProductsDB(){
            MongoClient mongoClient = new MongoClient("mongoDB", 27017);
            DB database = mongoClient.getDB("WarehouseDB");
            DBCollection collection = database.getCollection("Products");
            List<String> list = read("/app/Products.CSV");
            list.remove(0);
            for (String product:list) {
                String [] arr = product.split(";");
                BasicDBObject query = new BasicDBObject("_id", new BasicDBObject("$eq",Integer.parseInt(arr[0])));
                if( collection.findOne(query) == null){
                    BasicDBObject document = new BasicDBObject();
                    document.put("_id",Integer.parseInt(arr[0]));
                    document.put("name",arr[1]);
                    document.put("size",arr[2]);
                    document.put("dough",arr[3]);
                    document.put("fill",arr[4]);
                    document.put("glasur",arr[5]);
                    document.put("extras",arr[7]);
                    document.put("description",arr[6]);
                    //document.put("vegan",Boolean.parseBoolean(arr[7]));
                    document.put("userName",arr[8]);
                    collection.insert(document);
                }

                //userRepository.save(new Users(1, "Peter", "Development", 3000L));
                //userRepository.save(new Users(2, "Sam", "Operations", 2000L));
            }
        }

    public static void initializeComponentsDB(){
        MongoClient mongoClient = new MongoClient("mongoDB", 27017);
        DB database = mongoClient.getDB("WarehouseDB");
        DBCollection collection = database.getCollection("Components");
        List<String> list = read("/app/Components.CSV");
        list.remove(0);
        for (String product:list) {
            String [] arr = product.split(";");
            BasicDBObject query = new BasicDBObject("_id", new BasicDBObject("$eq",Integer.parseInt(arr[0])));
            if( collection.findOne(query) == null){
                BasicDBObject document = new BasicDBObject();
                document.put("_id",Integer.parseInt(arr[0]));
                document.put("name",arr[1]);
                document.put("description",arr[2]);
                document.put("nutriscore",arr[3]);
                document.put("componentGroup",arr[4]);
                document.put("vegan",Boolean.parseBoolean(arr[5]));
                document.put("price",Integer.parseInt(arr[6]));
                collection.insert(document);
            }

            //userRepository.save(new Users(1, "Peter", "Development", 3000L));
            //userRepository.save(new Users(2, "Sam", "Operations", 2000L));
        }
    }

    public static List<String> read(String csvFile) {
        List<String> list = new ArrayList<>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            while ((line = br.readLine()) != null) {
                list.add(line);
                System.out.println(line);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
return list;
    }

}
