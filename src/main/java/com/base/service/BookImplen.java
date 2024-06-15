package com.base.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.base.binding.Book;
import com.base.bookrepo.BookRepository;
import com.base.entity.BookEntity;

@Service
public class BookImplen implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookImplen.class);

    @Autowired
    private BookRepository repository;

    @Override
    public boolean addBook(Book book) {
       
        BookEntity entity = new BookEntity();
        BeanUtils.copyProperties(book, entity);
        BookEntity save = repository.save(entity);
        boolean isBookAdded = save.getBookId() != null;
        logger.info("Book added: {}", isBookAdded);
        return isBookAdded;
    }

    @Override
    public List<BookEntity> getBooks() {
        logger.info("Fetching all books");
        return repository.findAll();
    }

    @Override
    public boolean updateBookById(Integer bookId, Double price) {
        logger.info("Updating book with ID {}: New price - {}", bookId, price);
        Optional<BookEntity> findById = repository.findById(bookId);
        if (findById.isPresent()) {
            BookEntity entity = findById.get();
            entity.setBookPrice(price);
            repository.save(entity);
            logger.info("Book with ID {} updated successfully", bookId);
            return true;
        } else {
            logger.warn("Book with ID {} not found for update", bookId);
            return false;
        }
    }

    @Override
    public boolean deleteBook(Integer id) {
        try {
            logger.info("Deleting book with ID: {}", id);
            repository.deleteById(id);
            logger.info("Book with ID {} deleted successfully", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting book with ID {}", id, e);
        }
        return false;
    }

    @Override
    public BookEntity getBookById(Integer bookId) {
        logger.info("Retriving book with ID: ", bookId);
        return repository.findById(bookId).orElse(null);
    }

	@Override
	public boolean updateAllBookDetails(Integer bookId, String bookName, Double bookPrice, String bookAuthor) {
		logger.info("finding the books with bookId");
		Optional<BookEntity> findById = repository.findById(bookId);
		
		if(findById.isPresent()) {
			BookEntity bookEntity = findById.get();
         logger.info("Retrieving all books with bookId");
		if(bookName !=null) {
			bookEntity.setBookName(bookName);
			
		}if(bookPrice !=null) {
			bookEntity.setBookPrice(bookPrice);
		
		}if(bookAuthor !=null) {
			bookEntity.setBookAuthor(bookAuthor);	
		}
		repository.save(bookEntity);
		logger.info("Books Updated Successfully with bookId");
			return true;
		}else {
			logger.error("Book not updated......! with bookId ");
		    return false;
	}
}
	
	
	
	
}