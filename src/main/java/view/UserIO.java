package view;

import java.util.Date;

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
     * @param msg
     * @return Interger given by user
     */
    int readInt(String msg);

    /**
     * Read integer between a min and max value
     *
     * @param msg Messeage to be printed to user
     * @param min minimun value accepted
     * @param max maximum value accepted
     * @return interger between given range
     */
    int readInt(String msg, int min, int max);

    /**
     * Read date
     *
     * @param msg Messeage to be printed to user
     * @return Date
     */
    Date readDate(String msg);

    /**
     * Get line from user
     *
     * @param msg Messeage to tell user
     * @return String entered by user
     */
    String readString(String msg);

    /**
     * Gets int for MPAA rating
     * Specfic function is specfic enough to justify own method
     * @return int between 0 - 5
     */
    int getMPAARating();
}
