package view;

import dto.DVD;

import java.time.LocalDate;
import java.util.List;

/**
 * View interface for user
 *
 * @author Miles Singleton
 */
public class DVDLibraryView {
    private final UserIO io;

    /**
     * Constructor of view
     *
     * @param io UserIo class for interacting with user and getting data from them
     */
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1.\tAdd DVD to collection");
        io.print("2.\tRemove DVD from collection");
        io.print("3.\tEdit DVD info");
        io.print("4.\tDisplay info of specific DVD");
        io.print("5.\tSearch for DVD by title");
        io.print("6.\tPrint All DVDs in collection");
        io.print("7.\tExit Program");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    /**
     * Create new DVD
     *
     * @return new DVD
     */
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        LocalDate releaseDate = io.readDate("Please enter release date");
        int mpaaRating = io.readInt("""
                Please enter between 0 - 5 for MPAA rating
                0:\tNR
                1:\tG
                2:\tPG
                3:\tPG13
                4:\tR
                5:\tNC17""", 0, 5);
        String director = io.readString("Please enter Directors name");
        String studio = io.readString("Please enter film studio");
        int userRating = io.readInt("""
                Please enter user's rating
                Please only enter between 1 - 5
                1 being horrible, 5 being amazing""", 1, 5);

        return new DVD(title, releaseDate, mpaaRating, director, studio, userRating);
    }

    /**
     * Display entire DVD library
     *
     * @param dvdList list of all DVDs
     */
    public void displayDVDLibrary(List<DVD> dvdList) {
        if (dvdList == null || dvdList.size() == 0) {
            io.print("There are no DVDs in the collection");
        }
        assert dvdList != null;
        for (DVD dvd : dvdList) {
            this.displayDVDInfo(dvd);
        }
    }

    /**
     * Get which specfic field a user wants to edit of a DVD
     *
     * @param dvd DVD to edit
     * @return int of selection or -1 if DVD is null
     */
    public int getEditInfo(DVD dvd) {
        if (dvd != null) {
            return io.readInt("""
                    Please enter which info you want to change
                    1.Title
                    2.Release Date
                    3.MPAA rating
                    4.Director
                    5.Studio
                    6.User rating
                    7.Notes""", 1, 7);
        } else {
            io.print("DVD does not exist");
            return -1;
        }

    }

    /**
     * Get new dvd title
     *
     * @return new dvd title
     */
    public String editDVDTitle() {
        return io.readString("Please enter new title");
    }

    /**
     * Get new release date for dvd
     *
     * @return new release date for dvd
     */
    public LocalDate editDVDReleaseDate() {
        return io.readDate("Please enter new release date");
    }

    /**
     * Get new MPAA rating for dvd
     *
     * @return new MPAA rating for dvd
     */
    public int editMPAARating() {
        io.print("Enter new MPAA rating");
        return io.readInt("""
                Please enter between 0 - 5 for MPAA rating
                0:\tNR
                1:\tG
                2:\tPG
                3:\tPG13
                4:\tR
                5:\tNC17""", 0, 5);
    }

    /**
     * Get new director for DVD
     *
     * @return new director
     */
    public String editDirector() {
        return io.readString("Please enter new director");
    }

    /**
     * Get new studio for DVD
     *
     * @return new studio
     */
    public String editStudio() {
        return io.readString("Please enter new studio");
    }

    public int editUserRating() {
        return io.readInt("""
                Please enter new user's rating
                Please only enter between 1 - 5
                1 being horrible, 5 being amazing""", 1, 5);
    }

    public String newDVDNote() {
        return io.readString("Enter new DVD note");
    }

    private void displayDVDInfo(DVD dvd) {
        String dvdInfo = String.format("""
                        Title: %s
                        Release Date: %s
                        MPAA: %s
                        Director: %s
                        Studio: %s
                        User rating: %d/5
                        Notes: %s
                        """, dvd.getTitle(), dvd.getReleaseDate().toString(), dvd.getMPAARatingAsString(),
                dvd.getDirectorsName(), dvd.getStudio(), dvd.getUserRating(), dvd.getNotes());
        io.print(dvdInfo);
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            this.displayDVDInfo(dvd);
        } else {
            io.print("No such DVD exists in library");
        }
    }

    public void displayRemovedDVDResult(DVD dvd) {
        if (dvd != null) {
            io.print("DVD successfully removed");
        } else {
            io.print("No such DVD exists in library");
        }
    }

    public String findDVDByTitle() {
        return io.readString("Enter title to find DVD");
    }

    public void isDVDThere(DVD dvd) {
        if (dvd == null) {
            io.print("No such DVD by title");
        } else {
            String con = io.readString("We have that DVD would you like to know more info\nType Yes to find out more");
            if (con.equalsIgnoreCase("yes")) displayDVD(dvd);
        }
    }

    public void displayDisplayAllDVDBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.print("DVD successfully created");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public String getDVDTitle() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displaySearchDVDByTitleBanner() {
        io.print("=== Find DVD By Title ===");
    }
}
