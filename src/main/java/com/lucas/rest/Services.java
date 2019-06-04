package com.lucas.rest;
/** Library Solos Rest.
 * @author Lucas Napoli
 * @author https://github.com/lucasnapolilapenda/rest
 * @version 1.1
 * @since 1.0
 */

import javax.ws.rs.*;
import java.util.Set;

@Consumes("text/plain")
@Produces("text/plain")



@Path ("/books")
/**
 * Represents all the book services
 */
public class Services {


    /**
     * Post/Put Service to create or update a book
     * @param title Book Title
     * @param author Book Author
     * @param description Book Description
     * @param publisher Book Publisher
     * @param id Book ID - Important to manage delete and get
     * @param isbn Book Identification
     * @param put is to change to put or post
     * @return String error message or confirmation
     */

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

        return "Something wrong in the service process";
    }


    /**
     * Delete Service to create or update a book
     * @param id is to find the book
     * @return String error message or confirmation
     */

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

    /**
     * Post Service to create or update a book
     * @param id is to find the book
     * @return String ID and Book Title
     */


    @GET
    @Path ( "/get" )
    public String getPerson(@QueryParam ("id") int id) {
        return "Title: " + BookRepository.getRepository ().get(id).getTitle ().replace ( "%", " " )+ " / " + "ID: " + BookRepository.getRepository ().get(id).getId ();
    }

    /**
     * Post Service to create or update a book
     * @return String list of book with details
     */

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
            bookList = (            "Title: " + book.getTitle ().replace ( "%" , " " ) + " / " +
                                    "Publisher: " + book.getPublisher ().replace ( "%" , " " ) + " / " +
                                    "Author: " + book.getAuthor ().replace ( "%" , " " ) + " / " +
                                    "Description: " +  book.getDescription ().replace ( "%" , " " )+ " / " +
                                    " ID: " + book.getId () + " / " +
                                    "ISBN: " + book.getIsbn ()) +
                                    "\n".concat ( bookList );
            System.out.println ( " " );
        }


        return bookList;
    }
}

