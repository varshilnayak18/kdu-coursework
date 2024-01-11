package org.example;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book> {

    @Override
    public int compare(Book book, Book book2) {
        int ret = book.getYear() - book2.getYear();
        if(ret == 0){
            return book.getTitle().compareTo(book2.getTitle());
        }
        return ret;
    }
}
