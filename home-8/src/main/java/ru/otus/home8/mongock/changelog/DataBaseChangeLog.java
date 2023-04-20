package ru.otus.home8.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


@ChangeLog
public class DataBaseChangeLog {

    @ChangeSet(order = "002", id = "add_new_book", author = "nazim")
    public void insertOne(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("book");
        var doc = new Document().append("name", "new book").append("author", "Pushkin").append("genre", "skazka");
        myCollection.insertOne(doc);
    }


}
