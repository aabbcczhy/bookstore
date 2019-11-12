package com.yangsanhe.bookstore.controller;

import com.yangsanhe.bookstore.bean.Book;
import com.yangsanhe.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangsanhe
 * @date 2019-11-11 14:52:05
 */
@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/getBookList")
    @ResponseBody
    public List<Book> getBookList(Integer page){
        return bookService.getBookList(page);
    }

    @RequestMapping("/addToCart/{bookid}")
    public void addToCart(@PathVariable Integer bookid){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        List<Book> bookList = (List<Book>) request.getSession().getAttribute("cart");
        Book book = bookService.getBookById(bookid);
        if(book!=null){
            bookList.add(book);
        }
        request.getSession().setAttribute("cart",bookList);
    }

    public List<Book> getCartList(HttpSession session){
        return (List<Book>) session.getAttribute("cart");
    }

    @RequestMapping("/book")
    public String bookPage(){
        //初始化购物车
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("cart")==null){
            List<Book> bookList = new ArrayList<>();
            request.getSession().setAttribute("cart",bookList);
        }
        return "book";
    }

}
