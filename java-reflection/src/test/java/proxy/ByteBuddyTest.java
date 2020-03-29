package proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.isProtected;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Created by jyami on 2020/03/29
 */
public class ByteBuddyTest {
    @Test
    public void name() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends LibraryService> proxyClass = new ByteBuddy().subclass(LibraryService.class)
                .method(named("rentBook")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    LibraryService libraryService = new LibraryService();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("aaa");
                        Object invoke = method.invoke(libraryService, args);
                        System.out.println("bbb");
                        return invoke;
                    }
                }))
                .make().load(LibraryService.class.getClassLoader()).getLoaded();

        LibraryService libraryService = proxyClass.getConstructor(null).newInstance();

        libraryService.rentBook(new Book("sping"));
    }
}
