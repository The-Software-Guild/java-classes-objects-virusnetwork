package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserIOImpl implements UserIO {
    Scanner scan = new Scanner(System.in);

    /**
     * Print given message
     *
     * @param msg message to be printed
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Read given int
     *
     * @param msg
     * @return Interger given by user
     */
    @Override
    public int readInt(String msg) {
        System.out.println(msg);
        int num = 0;
        boolean validInput = false;
        do {
            try {
                num = scan.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("You may only enter an int");
            }
        } while (!validInput);
        return num;
    }

    /**
     * Read integer between a min and max value
     *
     * @param msg Messeage to be printed to user
     * @param min minimun value accepted
     * @param max maximum value accepted
     * @return interger between given range
     */
    @Override
    public int readInt(String msg, int min, int max) {
        System.out.println(msg);
        int num = 0;
        boolean validInput = false;
        do {
            try {
                num = scan.nextInt();
                if (num > min || num < max) {
                    validInput = true;
                } else {
                    System.out.println("Only enter a integer between " + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("You may only enter an int");
            }
        } while (!validInput);
        return num;
    }

    /**
     * Read date
     *
     * @param msg Messeage to be printed to user
     * @return Date
     */
    @Override
    public Date readDate(String msg) {
        System.out.println(msg);
        Date newDate = new Date();
        boolean validInput = false;
        do {
            String nextDate = scan.next();
            if (nextDate.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                try {
                    newDate = new SimpleDateFormat("yyyy-mm-dd").parse("1998-12-12");
                    validInput = true;
                } catch (Exception e) {

                }
            } else {
                System.out.println("Date must be formatted yyyy-mm-dd");
            }

        } while (!validInput);

        return newDate;
    }

    /**
     * Get line from user
     *
     * @param msg Messeage to tell user
     * @return String entered by user
     */
    @Override
    public String readString(String msg) {
        System.out.println(msg);
        return scan.next();
    }

    /**
     * Gets int for MPAA rating
     * Specfic function is specfic enough to justify own method
     *
     * @return int between 0 - 5
     */
    @Override
    public int getMPAARating() {
        this.print("Please enter between 0 - 5 for MPAA rating");
        return readInt("0:\tNR\n1:\tG\n2:\tPG\n3:\tPG13\n4:\tR\n5:\tNC17",0,5);
    }
}
