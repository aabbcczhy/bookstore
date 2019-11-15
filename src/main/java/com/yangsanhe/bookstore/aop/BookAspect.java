package com.yangsanhe.bookstore.aop;

import com.yangsanhe.bookstore.bean.BookOnCart;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangsanhe
 * @date 2019-11-15 17:40:44
 */
@Component
@Aspect
public class BookAspect {

    @Before("execution(* com.yangsanhe.bookstore.controller.BookController.*(..))")
    public void checkCartValidate(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("cart")==null){
            Map<Integer, BookOnCart> bookOnCart = new HashMap<>();
            request.getSession().setAttribute("cart",bookOnCart);
        }
    }

}
