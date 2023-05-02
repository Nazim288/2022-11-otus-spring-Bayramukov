package ru.otus.home8.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;


@ChangeLog
public class DataBaseChangeLog {

    @ChangeSet(order = "003", id = "add_book_pushkin", author = "nazim")
    public void insertOne(MongoDatabase db) {
        MongoCollection<Document> bookCollection = db.getCollection("book");
        var bookDoc = new Document()
                .append("name", "Золотая рыбка")
                .append("author", "Пушкин")
                .append("genre", "Повесть")
                .append("comments", new ArrayList<>());
        bookCollection.insertOne(bookDoc);

        ObjectId bookId = bookDoc.getObjectId("_id");

        MongoCollection<Document> commentCollection = db.getCollection("book_comment");
        var commentDoc = new Document()
                .append("value", "new comment")
                .append("bookId", bookId.toString());
        commentCollection.insertOne(commentDoc);

        bookCollection.updateOne(eq("_id", bookId), push("comments", commentDoc));

    }


}
