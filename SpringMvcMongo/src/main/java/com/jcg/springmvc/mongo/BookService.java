package com.jcg.springmvc.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcg.springmvc.mongo.factory.MongoFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("bookService")
@Transactional
public class BookService {

	static String db_name = "mydb", db_collection = "books2";
	private static Logger log = Logger.getLogger(BookService.class);

	// Fetch all books from the mongo database.
	public List<Book> getAll() {
		List<Book> book_list = new ArrayList<Book>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();	
		while(cursor.hasNext()) {			
			DBObject dbObject = cursor.next();

			Book book = new Book();
			book.setId(dbObject.get("id").toString());
			book.setTitle(dbObject.get("title").toString());
			book.setAuthor(dbObject.get("author").toString());
			book.setEditor(dbObject.get("editor").toString());
			book.setIsbn(dbObject.get("isbn").toString());


			// Adding the user details to the list.
			book_list.add(book);
		}
		log.debug("Total records fetched from the mongo database are= " + book_list.size());
		return book_list;
	}

	// Add a new book to the mongo database.
	public Boolean add(Book book) {
		boolean output = false;
		Random ran = new Random();
		log.debug("Adding a new book to the mongo database; Entered user_name is= " + book.getTitle());
		try {			
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new user details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("id", String.valueOf(ran.nextInt(100))); 
			doc.put("title", book.getTitle());
			doc.put("author", book.getAuthor());	
			doc.put("editor", book.getEditor());		
			doc.put("isbn", book.getIsbn());		

			// Save a new user to the mongo collection.
			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new book to the mongo database", e);			
		}
		return output;
	}

	// Update the selected book in the mongo database.
	public Boolean edit(Book book) {
		boolean output = false;
		log.debug("Updating the existing book in the mongo database; Entered user_id is= " + book.getId());
		try {
			// Fetching the user details.
			BasicDBObject existing = (BasicDBObject) getDBObject(book.getId());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and assign the updated details.
			BasicDBObject edited = new BasicDBObject();
			edited.put("id", book.getId()); 
			edited.put("title", book.getTitle());
			edited.put("author", book.getAuthor());
			edited.put("editor", book.getEditor());
			edited.put("isbn", book.getIsbn());

			// Update the existing user to the mongo database.
			coll.update(existing, edited);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error has occurred while updating an existing book to the mongo database", e);			
		}
		return output;
	}

	// Delete a book from the mongo database.
	public Boolean delete(String id) {
		boolean output = false;
		log.debug("Deleting an existing user from the mongo database; Entered user_id is= " + id);
		try {
			// Fetching the required user from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected user from the mongo database.
			coll.remove(item);
			output = true;			
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while deleting an existing user from the mongo database", e);			
		}	
		return output;
	}

	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected user_id to search.
		where_query.put("id", id);
		return coll.findOne(where_query);
	}

	// Fetching a single user details from the mongo database.
	public Book findBookById(String id) {
		Book b = new Book();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();
		where_query.put("id", id);

		DBObject dbo = coll.findOne(where_query);		
		b.setId(dbo.get("id").toString());
		b.setTitle(dbo.get("title").toString());
		b.setAuthor(dbo.get("author").toString());
		b.setEditor(dbo.get("editor").toString());
		b.setIsbn(dbo.get("isbn").toString());

		// Return book object.
		return b;
	}
}