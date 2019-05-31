package com.lucas.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookRepository {
    /*private static BookRepository ourInstance = new BookRepository ( );

    public static BookRepository getInstance() {
        return ourInstance;
    } */

    private static Map <Integer,Book> repository = new ConcurrentHashMap <> ();

    public static Map <Integer, Book> getRepository() {
        return repository;
    }

    public static void setRepository(Map <Integer, Book> repository) {
        BookRepository.repository = repository;
    }


}
