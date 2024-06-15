package com.base.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.binding.Book;
import com.base.entity.BookEntity;

@Service
public interface BookService {
	
	
	
	public boolean addBook(Book book) ;
	
	public  List<BookEntity> getBooks();
	
	public BookEntity getBookById(Integer bookId) ;
	
	public 	boolean updateAllBookDetails(Integer bookId,String bookName,Double bookPrice,String bookAuthor);
	
    public boolean deleteBook(Integer id);
   
	boolean updateBookById(Integer bookId, Double price);

}
