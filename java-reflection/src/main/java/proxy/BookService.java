package proxy;

/**
 * Created by jyami on 2020/03/29
 */
public interface BookService {
    void rent (Book book);
    void returnBook(Book book);
}
