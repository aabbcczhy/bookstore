package com.yangsanhe.bookstore.service;

import com.yangsanhe.bookstore.bean.Book;
import com.yangsanhe.bookstore.dao.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangsanhe
 * @date 2019-11-11 16:56:11
 */
@Service
public class BookService {
    private final BookMapper mapper;

    @Autowired
    public BookService(BookMapper mapper) {
        this.mapper = mapper;
    }

    public List<Book> getBookList(Integer page){
        if(page<=0){
            return null;
        }
        return mapper.getBookListByPage((page-1)*10);
    }

    public Book getBookById(Integer bookid){
        return mapper.selectByPrimaryKey(bookid);
    }
}
