package com.lucas.rest;



import java.io.*;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

public class Front {

    public void bookCreation() {

        Book book = new Book ( );
        Scanner input = new Scanner ( System.in );
        System.out.println ( "Please, Book Title, Remember only text accepted" );
        book.setTitle ( input.next ( ) );
        System.out.println ( "Please, Book Author, Remember only text accepted" );
        book.setAuthor ( input.next ( ) );
        System.out.println ( "Please, Book Description, Remember only text accepted" );
        book.setDescription ( input.next ( ) );
        System.out.println ( "Please, Book Publisher, Remember only text accepted" );
        book.setPublisher ( input.next ( ) );
        System.out.println ( "Please, Book ID, Remember only number accepted" );
        book.setId ( input.nextInt ( ) );
        System.out.println ( "Please, Book Isbn, Remember only number accepted" );
        book.setIsbn ( input.nextInt ( ) );

        try {
            String in = "title="+book.title + "&"
                    + "author="+book.author + "&"
                    + "description="+book.description + "&"
                    + "publisher=" + book.publisher + "&"
                    + "id="+ book.id + "&"
                    + "isbn="+book.isbn;

            String encodedString = ("http://localhost:8080/rest_war/api/books/add?"+in);
            System.out.println ( encodedString );


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

            try {

                URL url = new URL ( "http://localhost:8080/rest_war/api/books/getAll" );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection ( );
                conn.setRequestMethod ( "GET" );
                conn.setRequestProperty ( "Accept", "text/plain");

                httpExceptionManager ( conn );

            } catch (Exception e) {
                System.out.println ( "Exception in Solo's System:- " + e );

            }
    }

    public void deleteGetBook(Boolean delete) {

        Scanner input = new Scanner ( System.in );
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



}


