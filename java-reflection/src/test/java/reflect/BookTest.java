package reflect;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jyami on 2020/03/22
 */
public class BookTest {
    @Test
    public void name() throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();
        Class<?> aClass1 = Class.forName("reflect.Book");
    }

    @Test
    public void reflectionFunction() {
        Class<Book> bookClass = Book.class;

        Method[] methods = bookClass.getMethods();

        Constructor<?>[] constructors = bookClass.getConstructors();
        Constructor<?>[] declaredConstructors = bookClass.getDeclaredConstructors();

        Class<? super Book> superclass = bookClass.getSuperclass();

        Class<?>[] interfaces = bookClass.getInterfaces();

        Annotation[] declaredAnnotations = bookClass.getDeclaredAnnotations();
        Annotation[] annotations = bookClass.getAnnotations();

    }

    @Test
    public void reflectionFieldMethod() throws IllegalAccessException {
        Book book = new Book();
        Class<Book> bookClass = Book.class;

        Field[] fields = bookClass.getFields();
        Field[] declaredFields = bookClass.getDeclaredFields();

        Field field = fields[0];
        field.setAccessible(true);
        field.get(book);
        field.set(book, "2");

        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
    }

    @Test
    public void reflectionMethodFunction() throws InvocationTargetException, IllegalAccessException {
        Class<Book> bookClass = Book.class;
        Book book = new Book();
        Method[] methods = bookClass.getMethods();
        Method method = methods[0];
        method.invoke(book, "hello");
        Class<?>[] parameterTypes = method.getParameterTypes();
    }

    @Test
    public void reflectionInstance() {
    }
}