package view;

import dto.DVD;

import java.util.Date;
import java.util.List;

public class DVDLibraryView {
    private UserIO io;

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
        io.print("6.\tExit Program");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        Date releaseDate = io.readDate("Please enter release date");
        int mpaaRating = io.getMPAARating();
        String director = io.readString("Please enter Directors name");
        String studio = io.readString("Please enter film studio");
        int userRating = io.readInt("Please enter user's rating\nPlease only enter between 1 - 5\n1 being horrible, 5" +
                " being amazing", 1, 5);

        return new DVD(title, releaseDate, mpaaRating, director, studio, userRating);
    }

    public void displayDVDLibrary(List<DVD> dvdList) {
        for (DVD dvd : dvdList) {
            this.displayDVDInfo(dvd);
        }
    }

    public int getEditInfo(DVD dvd) {
        if (dvd != null) {
            return io.readInt("Please enter which info you want to change\n" +
                    "1.Title\n" +
                    "2.Release Date\n" +
                    "3.MPAA rating\n" +
                    "4.Director\n" +
                    "5.Studio\n" +
                    "6.User rating\n" +
                    "7.Notes");
        } else {
            io.print("DVD does not exist");
            return -1;
        }

    }

    private void displayDVDInfo(DVD dvd) {
        String dvdInfo = String.format("""
                        Title:\t%s
                        Release Date:\t%s
                        MPAA:\t%s
                        Director:\t%s
                        Studio:\t%s
                        User rating:\t%d/5""",
                dvd.getTitle(),
                dvd.getReleaseDate().toString(),
                dvd.getMPAARating(),
                dvd.getDirectorsName(),
                dvd.getStudio(),
                dvd.getUserRating()
        );
        io.print(dvdInfo);
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            this.displayDVDInfo(dvd);
        } else {
            io.print("No such DVD exists in library");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayRemovedDVDResult(DVD dvd) {
        if (dvd != null) {
            io.print("DVD successfully removed");
        } else {
            io.print("No such DVD exists in library");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllDVDBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
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
}
