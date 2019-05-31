package com.lucas.rest;
import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

public class Front {

    public void bookCreation(Boolean put) {
        System.out.println ( "***** Book Creation Module *****" );
        Book book = new Book ( );

        System.out.println ( "Please, Book Title, Remember only text accepted" );
        book.setTitle ( spacesManagementScanner ( false ).replace ( " ", "%" ));
        System.out.println ( "Please, Book Author, Remember only text accepted" );
        book.setAuthor ( spacesManagementScanner ( false ) .replace ( " ", "%" ));
        System.out.println ( "Please, Book Description, Remember only text accepted" );
        book.setDescription ( spacesManagementScanner ( false ).replace ( " ", "%" ));
        System.out.println ( "Please, Book Publisher, Remember only text accepted" );
        book.setPublisher ( spacesManagementScanner ( false ) .replace ( " ", "%" ));
        System.out.println ( "Please, Book ID, Remember only number accepted" );
        book.setId ( Integer.parseInt(spacesManagementScanner ( true )));
        System.out.println ( "Please, Book Isbn, Remember only number accepted" );
        book.setIsbn ( Integer.parseInt(spacesManagementScanner ( true )) );

        String putValidation = put.toString ();

        try {
            String service = "title="+book.title + "&"
                    + "author="+book.author + "&"
                    + "description="+book.description + "&"
                    + "publisher=" + book.publisher + "&"
                    + "id="+ book.id + "&"
                    + "isbn="+book.isbn + "&"
                    + "put="+putValidation;

            String encodedString = ("http://localhost:8080/rest_war/api/books/add?"+service);

            URL url = new URL ( encodedString );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection ( );
            conn.setRequestMethod ( "POST" );
            conn.setRequestProperty ( "Accept", "text/plain" );
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            conn.setRequestProperty ( "MediaType", "TEXT_PLAIN" );

            httpExceptionManager ( conn );


        } catch (Exception e) {
            System.out.println ( "Exception in NetClientGet:- " + e );

        }

        Menu.showMenu ( );

    }


    public void help() {
        System.out.println ( "***** Welcome to Solo's Library System *****" );
        System.out.println (    "\n In this System you can search, add and delete books. " +
                                "\n Additionally, you can work remotely with web services, you " +
                                "\n should be sure that TomCat is running and be sure all dependencies installed" +
                                "\n Thanks for using Lucas Software and enjoy it" );

        System.out.println ( "Please, type any KEY to come back to the Menu" );
        Scanner sc = new Scanner ( System.in );
        sc.next ( );
        Menu.showMenu ( );


    }

    public void getList() {
        System.out.println ( "***** Book List Module *****" );
            try {

                URL url = new URL ( "http://localhost:8080/rest_war/api/books/getAll" );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection ( );
                conn.setRequestMethod ( "GET" );
                conn.setRequestProperty ( "Accept", "text/plain");

                BufferedReader in = new BufferedReader ( new InputStreamReader ( conn.getInputStream () ) );
                Stream <String> bookList = in.lines ();
                bookList.forEach ( System.out::println );

                httpExceptionManager ( conn );

            } catch (Exception e) {
                System.out.println ( "Exception in Solo's System:- " + e );

            }

        Menu.showMenu ( );
    }

    public void deleteGetBook(Boolean delete) {

        String module = "";
        if (delete) { module = "Delete";} else {module = "Display";}
        System.out.println ( "***** " + module + " Book Module *****" );


        Scanner input = new Scanner ( System.in );
        System.out.println ( "Please, Type the Book's ID to be " + module.toLowerCase ());
        Integer id = input.nextInt ();
        String stringID = id.toString ();


        String requestMethod = "";
        String path = "";
        if (delete) {requestMethod = "DELETE";} else {requestMethod = "GET";}
        if (delete) {path = "delete";} else {path = "get";}

        try {
            URL url = new URL ( "http://localhost:8080/rest_war/api/books/"+path+"/?"+"id="+stringID );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection ( );
            conn.setRequestMethod ( requestMethod);
            conn.setRequestProperty ( "Accept", "text/plain" );
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            httpExceptionManager ( conn );

        } catch (Exception e) {
            System.out.println ( "Exception in Solo's System:- " + e );

        }

        Menu.showMenu ( );
    }



    public void httpExceptionManager (HttpURLConnection conn) throws IOException {

        if (conn.getResponseCode ( ) != 200) {
            throw new RuntimeException ( "Failed : HTTP Error code : "
                    + conn.getResponseCode ( ) );
        }
        InputStreamReader inu = new InputStreamReader ( conn.getInputStream ( ) );
        BufferedReader br = new BufferedReader ( inu );
        String output;
        while (( output = br.readLine ( ) ) != null) {
            System.out.println ( output );
        }
        conn.disconnect ( );

    }

    public String spacesManagementScanner (Boolean number) {

        try {
            Scanner scan = new Scanner(System.in);
            if (!number) {
                String name = "";

                name += scan.nextLine ( );

                return name;
            }

            Integer value = scan.nextInt ( );
            return value.toString ( );
        }catch (InputMismatchException | NumberFormatException ex ) {
            System.out.println ( "Please, check the input" );
            spacesManagementScanner (true  );
        }
        return "1";


    }



}


