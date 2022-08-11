package view;

import java.time.LocalDate;

public interface UserIO {
    /**
     * Print given message
     *
     * @param msg message to be printed
     */
    void print(String msg);

    /**
     * Read given int
     *
     * @param msg Instructions to user on reading int
     * @return Integer given by user
     */
    int readInt(String msg);

    /**
     * Read integer between a min and max value
     *
     * @param msg Message to be printed to user
     * @param min minimum value accepted
     * @param max maximum value accepted
     * @return integer between given range
     */
    int readInt(String msg, int min, int max);

    /**
     * Read date
     *
     * @param msg Message to be printed to user
     * @return Date
     */
    LocalDate readDate(String msg);

    /**
     * Get line from user
     *
     * @param msg Message to tell user
     * @return String entered by user
     */
    String readString(String msg);
}
