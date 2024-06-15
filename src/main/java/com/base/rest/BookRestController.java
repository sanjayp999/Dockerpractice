package com.base.rest;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.binding.Book;
import com.base.entity.BookEntity;
import com.base.service.BookService;

@RestController
public class BookRestController {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(BookRestController.class);

	@Autowired
	private BookService service;

	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@RequestBody Book book) {

		boolean addBook = service.addBook(book);
		if (addBook) {
			logger.info("Book saved successfully");
			return new ResponseEntity<>("Book saved successfully", HttpStatus.CREATED);
		} else {
			logger.error("Failed to save book");
			return new ResponseEntity<>("Book not saved....!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books")
	public ResponseEntity<List<BookEntity>> getBooks() {
		logger.info("retrieving all books");
		List<BookEntity> books = service.getBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@PutMapping("/books/{bookId}/{price}")
	public ResponseEntity<String> updateBook(@PathVariable Integer bookId, @PathVariable Double price) {

		boolean updateBooks = service.updateBookById(bookId, price);
		if (updateBooks) {
			logger.info("Book updated successfully");
			return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
		} else {
			logger.error("Failed to update book with ID {}", bookId);
			return new ResponseEntity<>("Not Updated.....!", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<String> deleteBooks(@PathVariable Integer bookId) {

		boolean deleteBook = service.deleteBook(bookId);
		if (deleteBook) {
			logger.info("Book deleted successfully");
			return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
		} else {
			logger.error("Failed to delete book with ID {}", bookId);
			return new ResponseEntity<>("Not deleted......!", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/{bookId}")
	public BookEntity getDataById(@PathVariable Integer bookId) {
		logger.info("Fetching book by ID: {}", bookId);
		return service.getBookById(bookId);

	}

	@PutMapping("/updateall/{bookId}/{bookName}/{bookPrice}/{bookAuthor}")
	public ResponseEntity<String> updateAllBookDetails(Integer bookId, String bookName, Double bookPrice,
			String bookAuthor) {
		boolean allBookDetails = service.updateAllBookDetails(bookId, bookName, bookPrice, bookAuthor);

		if (allBookDetails) {
			logger.info("updated Successfully with bookId");
			return new ResponseEntity<>("updated Succesfully", HttpStatus.OK);
		} else {
            logger.error("not Updated Successfully");
			return new ResponseEntity<>("not updated......", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	
	
	
	
	
}