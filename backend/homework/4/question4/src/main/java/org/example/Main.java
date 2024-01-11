package org.example;

import java.util.*;

public class Main {
    static Logging logger = new Logging();

    /**
     * sorts the books on the basis of the comparator (null -> natural asc/desc -> ascending/descending as per year
     * @param comparator comparator on how you want to sort
     */
    public static void treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>(comparator);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book : books) {
            logger.logInfo(book.toString());
        }
    }

    public static void main(String[] args) {
        logger.logInfo("natural ordering");
        treeSetDemo(null);
        logger.logInfo("ascending order");
        treeSetDemo(new PubDateAscComparator());
        logger.logInfo("descending order");
        treeSetDemo(new PubDateDescComparator());
    }

}