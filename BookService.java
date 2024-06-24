package com.example.bookcatalog;

import com.example.bookcatalog.models.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class BookService {
    public List<Book> getBooks(String jsonResponse) {
        Gson gson = new Gson();
        Type bookListType = new TypeToken<List<Book>>() {}.getType();
        return gson.fromJson(jsonResponse, bookListType);
    }
}