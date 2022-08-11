package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
     * @param msg Instructions to user
     * @return Integer given by user
     */
    @Override
    public int readInt(String msg) {
        int num = 0;
        while(true)
        {
            try{
                String str = this.readString(msg);
                num = Integer.parseInt(str);
                return num;
            } catch (NumberFormatException e)
            {
                this.print("Only enter a integer value");
            }
        }
    }

    /**
     * Read integer between a min and max value
     *
     * @param msg Message to be printed to user
     * @param min minimum value accepted
     * @param max maximum value accepted
     * @return integer between given range
     */
    @Override
    public int readInt(String msg, int min, int max) {
        int num;
        while(true)
        {
            num = this.readInt(msg);
            if(num >= min && num <= max)
            {
                return num;
            } else {
                this.print("Only enter values between " + min + " and " + max);
            }
        }
    }

    /**
     * Read date
     *
     * @param msg Message to be printed to user
     * @return Date
     */
    @Override
    public LocalDate readDate(String msg) {
        System.out.println(msg);
        LocalDate newDate;
        while (true) {
            try {
                String str = scan.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                newDate = LocalDate.parse(str, formatter);
                return newDate;
            } catch (DateTimeParseException e) {
                this.print("Only enter dates formatted yyyy-MM-dd");
            }
        }
    }

    /**
     * Get line from user
     *
     * @param msg Message to tell user
     * @return String entered by user
     */
    @Override
    public String readString(String msg) {
        System.out.println(msg);
        return scan.next();
    }
}
