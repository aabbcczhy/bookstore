package com.yangsanhe.bookstore.bean;

import java.math.BigDecimal;

/**
 * @author yangsanhe
 * @date 2019-11-15 11:48:23
 */
public class BookOnCart {
    private Integer bookid;

    private String bookname;

    private String bookurl;

    private Integer booknum;

    private BigDecimal price;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookurl() {
        return bookurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    public Integer getBooknum() {
        return booknum;
    }

    public void setBooknum(Integer booknum) {
        this.booknum = booknum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
