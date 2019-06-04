package com.lucas.rest;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {

    public static void main(String[] args) {

        showMenu ( );

    }

    public static void showMenu() {
        try {
            int exit = 0;
            do {
                System.out.println ( "" );
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
                System.out.println ( "" );

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
                        new Front ( ).help ( );
                        break;
                    case 2:
                        new Front ( ).getList ( );
                        break;
                    case 3:
                        new Front ( ).deleteGetBook ( false );
                        break;
                    case 4:
                        new Front ( ).bookCreation ( false );
                        break;
                    case 5:
                        new Front ( ).bookCreation ( true );
                        break;
                    case 6:
                        new Front ( ).deleteGetBook ( true );
                        break;

                    default:
                        System.out.println ( );
                        System.out.println ( "Please, Select one Option" );
                        System.out.println ( );
                        break;
                }


            } while (exit != 0);
        } catch (InputMismatchException | NumberFormatException ex) {
            System.out.println ( "Please, check the input" );
            showMenu ();
        }
    }
}






