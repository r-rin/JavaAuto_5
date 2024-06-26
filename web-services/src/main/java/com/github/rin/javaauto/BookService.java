package com.github.rin.javaauto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Service class to manage books.
 */
public final class BookService {

    /**
     * Singleton instance of a service.
     */
    private static BookService instance;

    /**
     * List of books.
     */
    private final List<Book> books = new ArrayList<>();

    /**
     * Index of the title in the book data array.
     */
    private static final int TITLE_INDEX = 0;

    /**
     * Index of the publish year in the book data array.
     */
    private static final int PUBLISH_YEAR_INDEX = 1;

    /**
     * Index of the author in the book data array.
     */
    private static final int AUTHOR_INDEX = 2;

    /**
     * Index of the description in the book data array.
     */
    private static final int DESCRIPTION_INDEX = 3;

    /**
     * Private constructor, that load test data from a file.
     */
    private BookService() {
        loadTestData();
    }

    /**
     * Method, that load test data from a file.
     */
    private void loadTestData() {
        try {
            Scanner scanner = new Scanner(new File(
                    "web-services/src/main/resources/testData.txt"
            ));
            while (scanner.hasNextLine()) {
                Book book = parseBook(scanner.nextLine());
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts a string into a book object.
     * @param bookString - string that contains info about a book.
     *
     * @return a book parsed from a string.
     */
    private Book parseBook(final String bookString) {
        String[] bookData = bookString.split(";");
        Book toReturn = new Book();

        toReturn.setTitle(bookData[TITLE_INDEX]);
        toReturn.setPublishYear(bookData[PUBLISH_YEAR_INDEX]);
        toReturn.setAuthor(bookData[AUTHOR_INDEX]);
        toReturn.setDescription(bookData[DESCRIPTION_INDEX]);

        return toReturn;
    }

    /**
     * Gets the single instance of the BookService.
     *
     * @return The instance of BookService.
     */
    public static synchronized BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    /**
     * Adds a book to the list.
     *
     * @param book The book to add.
     */
    public void addBook(final Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the list by its UUID.
     *
     * @param uuid The UUID of the book to remove.
     */
    public void removeBook(final UUID uuid) {
        Book bookToDelete = findBookByUUID(uuid);

        if (bookToDelete != null) {
            books.remove(bookToDelete);
        }
    }

    /**
     * Gets the list of books.
     *
     * @return The list of books.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Finds a book in the list by its UUID.
     *
     * @param uuid The UUID of the book to find.
     * @return The book with the specified UUID, or null if not found.
     */
    private Book findBookByUUID(final UUID uuid) {
        return books.stream()
                .filter(book -> book.getId().equals(uuid))
                .findFirst()
                .orElse(null);
    }
}
