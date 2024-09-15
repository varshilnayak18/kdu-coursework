package org.example;

import java.util.Comparator;
public class PubDateDescComparator implements Comparator<Book> {

    @Override
    public int compare(Book book, Book book2) {
        int ret = book2.getYear() - book.getYear();
        if (ret == 0) {
            return book.getTitle().compareTo(book2.getTitle());
        }
        return ret;
    }
}