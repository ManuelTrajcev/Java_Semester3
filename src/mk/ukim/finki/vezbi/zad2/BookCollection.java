package mk.ukim.finki.vezbi.zad2;

import java.util.*;

public class BookCollection {
    private Book[] books;

    public BookCollection() {
        books = new Book[0];
    }

    public void addBook(Book book) {
        Book[] newBooks = new Book[books.length + 1];
        for (int i = 0; i < books.length; i++) {
            newBooks[i] = books[i];
        }
        newBooks[books.length] = book;
        books = newBooks;
    }

    public void printByCategory(String category) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getCategory() == category)
                System.out.println(books[i].equals(category));
        }
    }

    public List<Book> getCheapestN(int n) {
        if (n <= books.length) {
            return Arrays.asList(books);
        } else {
            List<Book> list = new ArrayList<Book>();
            list = Arrays.asList(books);
            list.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    if (o1.getPrice() > o2.getPrice())
                        return 1;
                    else
                        return 0;
                }
            });
            return list.subList(0,n);
        }
    }

}
