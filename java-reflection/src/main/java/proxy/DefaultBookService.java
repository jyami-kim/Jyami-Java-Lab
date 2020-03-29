package proxy;

/**
 * Created by jyami on 2020/03/29
 */

public class DefaultBookService implements BookService {

    @Override
    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("return : " + book.getTitle());
    }
}
