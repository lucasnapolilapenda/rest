package com.lucas.rest;

import javax.ws.rs.*;
import java.util.Set;



@Consumes("text/plain")
@Produces("text/plain")

@Path ("/books")
public class Services {

    @POST
    @Path ( "/add" )
    public String addBook(  @QueryParam ( "title") String title,
                            @QueryParam ( "author")String author,
                            @QueryParam ( "description" )String description,
                            @QueryParam ( "publisher" ) String publisher,
                            @QueryParam ( "id" ) String id,
                            @QueryParam ( "isbn") String isbn,
                            @QueryParam ( "put" ) String put) {

        Book book = new Book ();
        book.setTitle ( title);
        book.setAuthor ( author );
        book.setDescription ( description );
        book.setPublisher ( publisher );
        book.setId ( Integer.parseInt ( id ) );
        book.setIsbn ( Integer.parseInt ( isbn ) );

        if (!Boolean.parseBoolean (put )) {
            Response response = new Response ( );
            if (BookRepository.getRepository ( ).get ( book.getId ( ) ) != null) {
                response.setStatus ( false );
                response.setMessage ( "Book Already Exists" );
                return response.getMessage ( );
            }

            BookRepository.getRepository ( ).putIfAbsent ( book.getId ( ), book );
            response.setStatus ( true );
            response.setMessage ( "Book Created Succesfully" );

            return response.getMessage ( );
        }

        if (Boolean.parseBoolean ( put)) {
            Response response = new Response ( );
            if (BookRepository.getRepository ( ).get ( book.getId ( ) ) == null) {
                response.setStatus ( false );
                response.setMessage ( "Book does not exist" );
                return response.getMessage ( );
            }
            BookRepository.getRepository ( ).put ( book.getId ( ), book );
            response.setStatus ( true );
            response.setMessage ( "Book Updated Succesfully" );

            return response.getMessage ( );

        }

        return "Something run in the service process";
    }

    

    @DELETE
    @Path ( "/delete" )
    public String deletebook (@QueryParam ("id") int id) {
        Response response = new Response ();
        if (BookRepository.getRepository ().get ( id ) == null) {
            response.setStatus ( false );
            response.setMessage ( "Book Doest Exist" );
            return response.getMessage ();
        }
        BookRepository.getRepository ().remove ( id );
        response.setStatus ( true );
        response.setMessage ( "Book deleted successfully" );
        return response.getMessage ();
    }

    @GET
    @Path ( "/get" )
    public String getPerson(@QueryParam ("id") int id) {
        return "Title: " + BookRepository.getRepository ().get(id).getTitle ().replace ( "%", " " )+ " / " + "ID: " + BookRepository.getRepository ().get(id).getId ();
    }


    @GET
    @Path ( "/getAll" )
    public String getAllPersons ()  {
        String bookList = "";
        Set <Integer> ids = BookRepository.getRepository().keySet ();
        Book[] p = new Book[ids.size()];
        int i=0;
        for(Integer id : ids){
            p[i] = BookRepository.getRepository ().get(id);
            i++;
        }

        for (Book book : p) {
            bookList += (            "Title: " + book.getTitle ().replace ( "%" , " " ) + " / " +
                                    "Publisher: " + book.getPublisher ().replace ( "%" , " " ) + " / " +
                                    "Author: " + book.getAuthor ().replace ( "%" , " " ) + " / " +
                                    "Description: " +  book.getDescription ().replace ( "%" , " " )+ " / " +
                                    " ID: " + book.getId () + " / " +
                                    "ISBN: " + book.getIsbn ());
            System.out.println ( " " );
        }


        return bookList;
    }
}

