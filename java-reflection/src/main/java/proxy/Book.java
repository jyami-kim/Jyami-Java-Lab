package proxy;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Created by jyami on 2020/03/29
 */
public class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
