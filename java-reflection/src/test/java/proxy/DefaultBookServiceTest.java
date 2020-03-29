package proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/03/29
 */
public class DefaultBookServiceTest {

    @Test
    public void name() {

        BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(),
                new Class[]{BookService.class},
                new InvocationHandler() {
                BookService bookService = new DefaultBookService();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        if(method.getName().equals("rent")){
                            System.out.println("aaa");
                            Object invoke = method.invoke(bookService, args);
                            System.out.println("bbbb");
                            return invoke;
                        }
                        return method.invoke(bookService,args);
                    }
                });

        bookService.rent(new Book("hello"));
    }
}