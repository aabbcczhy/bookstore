package com.yangsanhe.bookstore.controller;

import com.yangsanhe.bookstore.bean.Book;
import com.yangsanhe.bookstore.bean.BookOnCart;
import com.yangsanhe.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public void addToCart(@PathVariable Integer bookid,Integer booknum){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Map<Integer, BookOnCart> cart = (Map<Integer, BookOnCart>) request.getSession().getAttribute("cart");
        BookOnCart bookFromCart = cart.get(bookid);
        if(bookFromCart==null){
            Book book = bookService.getBookById(bookid);
            BookOnCart bookOnCart = this.convertFromBook(book,booknum);
            cart.put(bookid,bookOnCart);
        }else{
            bookFromCart.setBooknum(bookFromCart.getBooknum()+booknum);
            cart.put(bookid,bookFromCart);
        }
        request.getSession().setAttribute("cart",cart);
    }

    @PostMapping("/removeFromCart")
    @ResponseBody
    public String removeFromCart(Integer bookid){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Map<Integer, BookOnCart> cart = (Map<Integer, BookOnCart>) request.getSession().getAttribute("cart");
        BookOnCart bookFromCart = cart.get(bookid);
        if(bookFromCart!=null){
            cart.remove(bookid);
        }
        return "success";
    }

    @RequestMapping("/cart")
    public String getCartList(HttpSession session,ModelMap modelMap){
        Map<Integer, BookOnCart> cart = (Map<Integer, BookOnCart>) session.getAttribute("cart");
        modelMap.addAttribute("cart",cart);
        return "cart";
    }

    @RequestMapping("/book")
    public String bookPage(){
        return "book";
    }

    @RequestMapping("/bookdetails/{bookid}")
    public String getBookDetail(@PathVariable Integer bookid, ModelMap modelMap){
        Book book = bookService.getBookById(bookid);
        modelMap.addAttribute("book",book);
        return "bookdetails";
    }

    /**
     * 将图书类转化为购物车中的图书类
     * @param book 图书
     * @param booknum 购买数量
     * @return 购物车中的图书
     */
    private BookOnCart convertFromBook(Book book, Integer booknum){
        if(book==null || booknum==null){
            return null;
        }
        BookOnCart bookOnCart = new BookOnCart();
        bookOnCart.setBookid(book.getBookid());
        bookOnCart.setBookname(book.getBookname());
        bookOnCart.setBookurl(book.getBookurl());
        bookOnCart.setBooknum(booknum);
        bookOnCart.setPrice(book.getPrice());
        return bookOnCart;
    }
}
