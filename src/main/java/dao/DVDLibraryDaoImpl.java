package dao;

import dto.DVD;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

public class DVDLibraryDaoImpl implements DVDLibraryDao {
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    Map<String, DVD> DVDLibrary = new HashMap<>();

    /**
     * Add DVD to library
     *
     * @param title the title of the dvd, used as ID
     * @param dvd   DVD object to add to library
     * @return DVD object in the library
     */
    @Override
    public DVD addDVD(String title, DVD dvd) {
        return DVDLibrary.put(title, dvd);
    }

    /**
     * @return List of all DVDs
     */
    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList<>(DVDLibrary.values());
    }

    /**
     * Get DVD object
     *
     * @param title of DVD
     * @return DVD of title or null if not found
     */
    @Override
    public DVD getDVD(String title) {
        return DVDLibrary.get(title);
    }

    /**
     * Remove DVD from libary
     *
     * @param title title of DVD to remove
     * @return DVD added to library
     */
    @Override
    public DVD removeDVD(String title) {
        return DVDLibrary.remove(title);
    }

    /**
     * @param oldTitle old title, used as key to find DVD
     * @param newTitle new title for DVD
     * @return DVD with new title
     */
    @Override
    public DVD editDVDinfoTitle(String oldTitle, String newTitle) {
        if (DVDLibrary.get(oldTitle) == null || DVDLibrary.get(newTitle) != null) {
            //throw new Exception();
            return null;
        }

        DVD temp = DVDLibrary.remove(oldTitle);
        temp.setTitle(newTitle);
        return DVDLibrary.put(newTitle, temp);
    }

    /**
     * Edit realease date
     *
     * @param title       title of DVD to change release date of
     * @param releaseDate new release date
     * @return DVD with new release date
     */
    @Override
    public DVD editDVDinfoReleaseDate(String title, LocalDate releaseDate) {
        DVD temp = DVDLibrary.get(title);
        temp.setReleaseDate(releaseDate);
        return temp;
    }

    /**
     * Edit director of a DVD
     *
     * @param title    title of DVD to change
     * @param director new director
     * @return DVD with new director
     */
    @Override
    public DVD editDVDinfoDirector(String title, String director) {
        DVD temp = DVDLibrary.get(title);
        temp.setDirectorsName(director);
        return temp;
    }

    /**
     * Edit user rating of a DVD
     *
     * @param title title of DVD to change rating of
     * @param score new rating
     * @return DVD with new rating
     */
    @Override
    public DVD editDVDinfoUserRating(String title, int score) {
        DVDLibrary.get(title).setUserRating(score);
        return DVDLibrary.get(title);

    }

    /**
     * Edit MPAA rating of a DVD
     *
     * @param title  title of DVD to change MPAA rating
     * @param rating new rating
     * @return DVD with new rating
     */
    @Override
    public DVD editDVDinfoMPAARating(String title, int rating) {
        if (rating < 0 || rating > 5) {
            //throw exception
        }
        DVDLibrary.get(title).setMPAArating(rating);
        return DVDLibrary.get(title);
    }

    /**
     * Edit studio of a DVD
     *
     * @param title  title of DVD
     * @param studio new studio
     * @return DVD with new studio
     */
    @Override
    public DVD editDVDinfoStudio(String title, String studio) {
        DVDLibrary.get(title).setStudio(studio);
        return DVDLibrary.get(title);
    }

    @Override
    public DVD editDVDNotes(String title, String notes) {
        DVDLibrary.get(title).setNotes(notes);
        return DVDLibrary.get(title);
    }

    private DVD unmarshallDVD(String dvdAsText) {
        /*
        Expected in format <title>::<release date (yyyy-MM-dd)>::<MPARATING>::<Director>::<Studio>::<User rating>::Note
         */

        String[] dvdToken = dvdAsText.split(DELIMITER);

        //Title is index 0
        //release date is index 1
        //mpaa rating is index 2
        //director is index 3
        //studio is index 4
        //user rating is index 5
        //notes is index 6

        return new DVD(dvdToken[0], LocalDate.parse(dvdToken[1]), Integer.parseInt(dvdToken[2]), dvdToken[3],
                dvdToken[4], Integer.parseInt(dvdToken[5]), dvdToken[6]);
    }

    private void loadRoaster() throws DVDLibraryDaoException {
        Scanner scan;

        try{
            scan = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("Could not load file");
        }

        //current line holds most recent line read from file
        String currentLine;

        //currentDVD holds most recent unmarashalled dvd
        DVD currentDVD;

        /*
        Go through LIBRARY FILE line by line
        decpdomg each lime into DVD
        process till we run out of files
         */
        while(scan.hasNextLine())
        {
            currentLine = scan.nextLine();

            currentDVD = this.unmarshallDVD(currentLine);

            this.addDVD(currentDVD.getTitle(),currentDVD);
        }

        scan.close();
    }
}
