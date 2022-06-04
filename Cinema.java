package cinema;

import java.util.Scanner;

public class Cinema {

    //selection of menu
    public static void display_menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static int get_selection() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }

    //setting the pattern of the seats
    public static void array_seats_pattern(char[][] array, char letterOfSeats) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == 0 && j == 0) {
                    array[i][j] = ' ';
                } else if (i == 0) {

                    array[i][j] = Character.forDigit(j, 10);
                    ;
                } else if (j == 0) {

                    array[i][j] = Character.forDigit(i, 10);
                    ;
                } else {
                    array[i][j] = letterOfSeats;
                }
            }

        }
    }

    //showing array
    public static void array_show(char[][] array) {
        System.out.println("Cinema:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");

            }
            System.out.println();
        }
    }

    //cinema statistics
    public static void array_statistics(char[][] array, int rows1, int seats1) {
        int numOfTakenSeats = 0;
        int currentIncome = 0;
        int firstHalf = rows1 / 2;
        int places = rows1 * seats1;
        int maximumIncome = (places < 60) ? (10 * places) : (firstHalf * 10 * seats1 + (rows1 - firstHalf) * 8 * seats1);

        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                if (array[i][j] == 'B') {
                    numOfTakenSeats++;

                    if (places < 60) {
                        currentIncome += 10;
                    } else {

                        if (i <= firstHalf) {
                            currentIncome += 10;

                        } else {
                            currentIncome += 8;

                        }
                    }
                }

            }
        }
        double percentage = (numOfTakenSeats * 100.00 / places);
        System.out.println("Number of purchased tickets: " + numOfTakenSeats);
        System.out.printf("Percentage: %.2f%c\n", percentage, 37);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + maximumIncome);

    }

    //buying ticket
    public static void array_set_buy(char[][] array, int rows1, int seats1) {

        int enteredRow = 0;
        int enteredSeat = 0;
        do {
            int tempRow;
            int tempSeat;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a row number:");
            tempRow = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            tempSeat = scanner.nextInt();
            if (tempRow > rows1 || tempSeat > seats1) {
                System.out.println("Wrong input!");
                continue;
            }
            if (array[tempRow][tempSeat] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            enteredRow = tempRow;
            enteredSeat = tempSeat;
        } while (array[enteredRow][enteredSeat] != 'S');


        array[enteredRow][enteredSeat] = 'B';

        int places = rows1 * seats1;

        System.out.println("Ticket price: ");

        if (places < 60) {
            System.out.println("$" + 10);
        } else {

            if (enteredRow <= rows1 / 2) {
                System.out.println("$" + 10);
            } else {
                System.out.println("$" + 8);
            }

        }
    }


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();


        char[][] twoD_arr = new char[rows + 1][seats + 1];
        array_seats_pattern(twoD_arr, 'S');
        int choice;

        do { //display menu
            display_menu();
            choice = get_selection();

            switch (choice) {
                case 1:
                    array_show(twoD_arr);
                    break;
                case 2:
                    array_set_buy(twoD_arr, rows, seats);
                    break;
                case 3:
                    array_statistics(twoD_arr, rows, seats);
                    break;

            }

        } while (choice != 0);


    }

}