import com.mongodb.client.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.Array;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;

public class MongoTest {

    private MongoDatabase database;
    private MongoCursor<Document> cursor;

    @BeforeClass
    public void Test(){
        MongoClient mongoClient = MongoClients.create("mongodb://techno:ee4CvCRPhor5@185.97.114.201:27118/?authSource=cloud-school");

        database = mongoClient.getDatabase("cloud-school");
    }

    @Test
    public void Test2(){
        MongoCollection<Document> collection = database.getCollection("school_city");

        System.out.println(collection.countDocuments());
    }

    @Test
    public void FindOne(){
        MongoCollection<Document> collection = database.getCollection("school_city");
        Document actual = collection.find().first();
        System.out.println(actual);

    }

    @Test
    public void FindAll(){
        MongoCollection<Document> collection = database.getCollection("school_city");
        cursor = collection.find().iterator();
            while (cursor.hasNext()){
                System.out.println(cursor.next());
            }
            cursor.close();
        };

    @Test
    public void FindOneUsingFilters(){
        MongoCollection<Document> collection = database.getCollection("school_city");
        Document actual = collection.find(eq("name","Newyork")).first();
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
        cursor.close();
    };



    @Test
    public void FindAllUsingFilters(){
        MongoCollection<Document> collection = database.getCollection("school_city");
        cursor = collection.find().iterator();
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
        cursor.close();
    };


    @Test
    public void findStudentsInSection(){
        MongoCollection<Document> collection = database.getCollection("school_student");
        long count = collection.countDocuments(or(
                eq("section","1305000"),
                eq("section","0111000")
        ));
        System.out.println(count);

        ArrayList<String> sections = new ArrayList<String>();
        sections.add("1305000");
        sections.add("0111000");
        long alternativecount = collection.countDocuments(in("section",sections));
        Assert.assertEquals(count,alternativecount);
    }













}





