package com.example.bookcatalog;

import com.example.bookcatalog.models.Book;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BookCatalog {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookAPIClient apiClient = new BookAPIClient();
    private static final BookService bookService = new BookService();
    private static final BookDAO bookDAO = new BookDAO();

    public static void main(String[] args) throws IOException {
        boolean running = true;
        while (running) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar libros");
            System.out.println("2. Ver libros guardados");
            System.out.println("3. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Ingrese el término de búsqueda:");
                    String query = scanner.nextLine();
                    String jsonResponse = apiClient.getBooks(query);
                    List<Book> books = bookService.getBooks(jsonResponse);
                    for (Book book : books) {
                        System.out.println("Título: " + book.getTitle() + ", Autor: " + book.getAuthor());
                        bookDAO.insert(book);
                    }
                    break;
                case 2:
                    List<Book> savedBooks = bookDAO.getAllBooks();
                    for (Book book : savedBooks) {
                        System.out.println("Título: " + book.getTitle() + ", Autor: " + book.getAuthor());
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}