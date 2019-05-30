package com.lucas.rest;

import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



@Consumes("text/plain")
@Produces("text/plain")

@Path ("/books")
public class Services {

    private static Map<Integer,Book> repository = new HashMap<Integer,Book>();



    @POST
    @Path ( "/add" )
    public String addBook(  @QueryParam ( "title") String title,
                            @QueryParam ( "author")String author,
                            @QueryParam ( "description" )String description,
                            @QueryParam ( "publisher" ) String publisher,
                            @QueryParam ( "id" ) String id,
                            @QueryParam ( "isbn") String isbn)  {

        Book book = new Book ();
        book.setTitle ( title);
        book.setAuthor ( author );
        book.setDescription ( description );
        book.setPublisher ( publisher );
        book.setId ( Integer.parseInt ( id ) );
        book.setIsbn ( Integer.parseInt ( isbn ) );


        Response response = new Response ();
        if (repository.get(book.getId () ) != null) {
            response.setStatus ( false );
            response.setMessage ( "Book Already Exists" );
            return response.getMessage ();
        }

        repository.putIfAbsent (book.getId (),book);
        response.setStatus ( true );
        response.setMessage ( "Book Created Succesfully" );

        return response.getMessage ();
    }
    
    

    @DELETE
    @Path ( "/delete" )
    public String deletebook (@QueryParam ("id") int id) {
        Response response = new Response ();
        if (repository.get ( id ) == null) {
            response.setStatus ( false );
            response.setMessage ( "Book Doest Exist" );
            return response.getMessage ();
        }
        repository.remove ( id );
        response.setStatus ( true );
        response.setMessage ( "Book deleted successfully" );
        return response.getMessage ();
    }

    @GET
    @Path ( "/get" )
    public String getPerson(@QueryParam ("id") int id) {
        return "Title: " + repository.get(id).getTitle ()+ " / " + "ID: " + repository.get(id).getId ();
    }


    @GET
    @Path ( "/getAll" )
    public String getAllPersons ()  {
        String bookList = "";
        Set <Integer> ids = repository.keySet();
        Book[] p = new Book[ids.size()];
        int i=0;
        for(Integer id : ids){
            p[i] = repository.get(id);
            i++;
        }

        for (Book book : p) {
            bookList += (            "Title: " + book.getTitle () + " / " +
                                    "Publisher: " + book.getPublisher () + " / " +
                                    "Author: " + book.getAuthor () + " / " +
                                    "Description: " +  book.getDescription ()+ " / " +
                                    "ID: " + book.getId () + " / " +
                                    "ISBN: " + book.getIsbn ());
            System.out.println ( " " );
        }


        return bookList;
    }
}

