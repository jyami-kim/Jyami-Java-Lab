package proxy;

/**
 * Created by jyami on 2020/03/29
 */
public class LibraryService {

    public void rentBook(Book book) {
        System.out.println("rent : " + book.getTitle());
    }

}
