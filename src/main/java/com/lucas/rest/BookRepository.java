package com.lucas.rest;
/** Library Solos Rest.
 * @author Lucas Napoli
 * @author https://github.com/lucasnapolilapenda/rest
 * @version 1.1
 * @since 1.0
 */

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents the concurrent collection data
 */

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
