package com.github.rin.javaauto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookService {
    private static BookService instance;

    private List<Book> books = new ArrayList<>();

    private BookService() {
        loadTestData();
    }

    private void loadTestData() {
        try{
            Scanner scanner = new Scanner(new File("web-services/src/main/resources/testData.txt"));
            while (scanner.hasNextLine()) {
                Book book = parseBook(scanner.nextLine());
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private Book parseBook(String bookString) {
        String[] bookData = bookString.split(";");
        Book toReturn = new Book();

        toReturn.setTitle(bookData[0]);
        toReturn.setPublishYear(bookData[1]);
        toReturn.setAuthor(bookData[2]);
        toReturn.setDescription(bookData[3]);

        return toReturn;
    }

    public static BookService getInstance()
    {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(UUID uuid) {
        Book bookToDelete = findBookByUUID(uuid);

        if(bookToDelete != null)
        {
            books.remove(bookToDelete);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    private Book findBookByUUID(UUID uuid) {
        return books.stream()
                .filter(book -> book.getId().equals(uuid))
                .findFirst()
                .orElse(null);
    }
}
