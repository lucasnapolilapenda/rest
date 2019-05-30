package com.lucas.rest;

import java.util.concurrent.ConcurrentHashMap;

public class BookRepository {
    /*private static BookRepository ourInstance = new BookRepository ( );

    public static BookRepository getInstance() {
        return ourInstance;
    } */

    ConcurrentHashMap <Integer, Book> booksrepository = new ConcurrentHashMap <> (  );


}
