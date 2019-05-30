package com.lucas.rest;

import java.util.Scanner;


public class Menu {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        showMenu ( );

    }

    public static void showMenu() {
        int exit = 0;
        do {

            System.out.println ( "****** Welcome to Solo's Library ******" );
            System.out.println ( "" );
            System.out.println ( "Please, Select one Option" );
            System.out.println ( "1. Help" );
            System.out.println ( "2. List Books" );
            System.out.println ( "3. Display Book" );
            System.out.println ( "4. Add Book" );
            System.out.println ( "5. Update Book" );
            System.out.println ( "6. Delete Book" );
            System.out.println ( "0. Quit" );

            //Leer la respuesta del usuario
            Scanner sc = new Scanner ( System.in );
            int response = Integer.valueOf ( sc.nextLine ( ) );
            System.out.println ( response );
            switch (response) {
                case 0:
                    //salir
                    exit = 0;
                    break;
                case 1:
                    new Front ().help ();
                    break;
                case 2:
                    new Front ().getList ();
                    break;
                case 3:
                    new Front ().deleteGetBook (false);
                    break;
                case 4:
                    new Front ().bookCreation ();
                    break;
                case 5:
                    break;
                case 6:
                    new Front ().deleteGetBook (true);
                    break;

                default:
                    System.out.println ( );
                    System.out.println ( "Please, Select one Option" );
                    System.out.println ( );
                    break;
            }


        } while (exit != 0);
    }

}

    /*
    static ArrayList<Movie> movies = Movie.makeMoviesList();
    public static void showMovies() {
        int exit = 1;

        do {
            System.out.println();
            System.out.println(":: MOVIES ::");
            System.out.println();

            for (int i = 0; i < movies.size(); i++) { //1. Movie 1
                System.out.println(i+1 + ". " + movies.get(i).getTitle() + " Visto: " + movies.get(i).isViewed());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if(response == 0) {
                exit = 0;
                showMenu();
            }
            if (response > 0) {
                Movie movieSelected = movies.get(response-1);
                movieSelected.setViewed(true);
                Date dateI = movieSelected.startToSee(new Date());

                for (int i = 0; i < 100000; i++) {
                    System.out.println("..........");
                }

                //Termine de verla
                movieSelected.stopToSee(dateI, new Date());
                System.out.println();
                System.out.println("Viste: " + movieSelected);
                System.out.println("Por: " + movieSelected.getTimeViewed() + " milisegundos");
            }


        }while(exit !=0);

    }

    public static void showSeries() {
        int exit = 1;
        ArrayList<Serie> series = Serie.makeSeriesList();
        do {
            System.out.println();
            System.out.println(":: SERIES ::");
            System.out.println();

            for (int i = 0; i < series.size(); i++) { //1. Serie 1
                System.out.println(i+1 + ". " + series.get(i).getTitle() + " Visto: " + series.get(i).isViewed());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if(response == 0) {
                showMenu();
            }

            showChapters(series.get(response-1).getChapters());

        }while(exit !=0);
    }

    public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) {
        int exit = 0;

        do {
            System.out.println();
            System.out.println(":: CHAPTERS ::");
            System.out.println();


            for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
                System.out.println(i+1 + ". " + chaptersOfSerieSelected.get(i).getTitle() + " Visto: " + chaptersOfSerieSelected.get(i).isViewed());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if(response == 0) {
                showSeries();
            }

            Chapter chapterSelected = chaptersOfSerieSelected.get(response-1);
            chapterSelected.setViewed(true);
            Date dateI = chapterSelected.startToSee(new Date());

            for (int i = 0; i < 100000; i++) {
                System.out.println("..........");
            }

            //Termine de verla
            chapterSelected.stopToSee(dateI, new Date());
            System.out.println();
            System.out.println("Viste: " + chapterSelected);
            System.out.println("Por: " + chapterSelected.getTimeViewed() + " milisegundos");
        }while(exit !=0);
    }

    public static void showBooks() {
        int exit = 0;
        ArrayList<Book> books= Book.makeBookList();
        do {
            System.out.println();
            System.out.println(":: BOOKS ::");
            System.out.println();

            for (int i = 0; i < books.size(); i++) { //1. Movie 1
                System.out.println(i+1 + ". " + books.get(i).getTitle() + " Visto: " + books.get(i).isReaded());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            Scanner sc = new Scanner(System.in);
            int response = Integer.valueOf(sc.nextLine());

            if(response == 0) {
                showMenu();
            }

            Book bookSelected = books.get(response-1);
            bookSelected.setReaded(true);
            Date dateI = bookSelected.startToSee(new Date());

            for (int i = 0; i < 100000; i++) {
                System.out.println("..........");
            }

            //Termine de verla
            bookSelected.stopToSee(dateI, new Date());
            System.out.println();
            System.out.println("Viste: " + bookSelected);
            System.out.println("Por: " + bookSelected.getTimeReaded() + " milisegundos");
        }while(exit !=0);
    }

    public static void showMagazines() {
        int exit = 0;
        do {
            System.out.println();
            System.out.println(":: MAGAZINES ::");
            System.out.println();
        }while(exit !=0);
    }

    public static void makeReport() {

        Report report = new Report();
        report.setNameFile("reporte");
        report.setExtension("txt");
        report.setTitle(":: VISTOS ::");
        String contentReport = "";

        for (Movie movie : movies) {
            if (movie.getIsViewed()) {
                contentReport += movie.toString() + "\n";

            }
        }
        report.setContent(contentReport);
        report.makeReport();

    }

    public static void makeReport(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);
        Report report = new Report();

        report.setNameFile("reporte" + dateString);
        report.setExtension("txt");
        report.setTitle(":: VISTOS ::");
        String contentReport = "";

        for (Movie movie : movies) {
            if (movie.getIsViewed()) {
                contentReport += movie.toString() + "\n";

            }
        }
        report.setContent(contentReport);
        report.makeReport();
    } */






