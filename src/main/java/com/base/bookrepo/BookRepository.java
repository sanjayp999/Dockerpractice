package com.base.bookrepo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity,Serializable> {

}
