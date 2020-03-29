package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by jyami on 2020/03/29
 */
public class CGlibTest {

    @Test
    public void cglibTest() {

        MethodInterceptor hanlder = new MethodInterceptor() {
            LibraryService libraryService = new LibraryService();
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("aaa");
                Object invoke = method.invoke(libraryService, args);
                System.out.println("aaa");
                return invoke;
            }
        };

        LibraryService libraryService = (LibraryService) Enhancer.create(LibraryService.class, hanlder);
        libraryService.rentBook(new Book("hello"));
    }
}
