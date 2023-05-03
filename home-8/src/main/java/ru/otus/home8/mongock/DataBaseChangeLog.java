package ru.otus.home8.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;


@ChangeLog
public class DataBaseChangeLog {

    @ChangeSet(order = "001", id = "add_book", author = "nazim")
    public void insertOne(MongoDatabase db) {

        Document authorDoc = new Document("name", "Иван").append("lastName", "Тургенев");
        MongoCollection<Document> authorCollection = db.getCollection("author");
        authorCollection.insertOne(authorDoc);

        Document genreDoc = new Document("name", "Классика");
        MongoCollection<Document> genreCollection = db.getCollection("genre");
        genreCollection.insertOne(genreDoc);


        MongoCollection<Document> bookCollection = db.getCollection("book");

        var bookDoc = new Document("title", "Отцы и дети")
                .append("author", new DBRef("author", authorDoc.getObjectId("_id")))
                .append("genre", new DBRef("genre", genreDoc.getObjectId("_id")));

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
