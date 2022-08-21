package dao;

import dto.DVD;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DVDLibraryDaoImpl implements DVDLibraryDao {
    private final String LIBRARY_FILE;
    private static final String DELIMITER = "::";
    Map<String, DVD> DVDLibrary = new HashMap<>();

    public DVDLibraryDaoImpl() {
        this.LIBRARY_FILE = "library.txt";
    }

    public DVDLibraryDaoImpl(String testFileLocation) {
        this.LIBRARY_FILE = testFileLocation;
    }

    /**
     * Add DVD to library
     *
     * @param title the title of the dvd, used as ID
     * @param dvd   DVD object to add to library
     * @return DVD object in the library
     */
    @Override
    public DVD addDVD(DVD dvd) throws DVDLibraryDaoException {
        String title = dvd.getTitle();
        this.loadDVDLibrary();
        DVD newDVD = DVDLibrary.put(title, dvd);
        writeDVDLibrary();
        return newDVD;
    }

    /**
     * Get a list of all DVDs in library
     *
     * @return List of all DVDs
     */
    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadDVDLibrary();
        return new ArrayList<>(DVDLibrary.values());
    }

    /**
     * Get specific DVD by title
     *
     * @param title of DVD, used for key in map
     * @return DVD object or null if not found
     */
    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        return DVDLibrary.get(title);
    }

    /**
     * Remove given DVD from library
     *
     * @param title of DVD, used for key in map
     * @return DVD object or null if not found
     */
    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD oldDVD = DVDLibrary.remove(title);
        writeDVDLibrary();
        return oldDVD;
    }

    /**
     * Edit title of specific DVD. Find DVD by old key, remove it, change it's title then re-add under new title
     *
     * @param oldTitle title used as key in map
     * @param newTitle new title to be changed to
     * @return DVD object with new name or null if DVD not found
     */
    @Override
    public DVD editDVDinfoTitle(String oldTitle, String newTitle) throws DVDLibraryDaoException {
        loadDVDLibrary();
        if (DVDLibrary.get(oldTitle) == null || DVDLibrary.get(newTitle) != null) {
            throw new DVDLibraryDaoException("DVD does not exist");
        }

        DVD editedDVD = DVDLibrary.remove(oldTitle);
        editedDVD.setTitle(newTitle);
        DVDLibrary.put(editedDVD.getTitle(),editedDVD);
        writeDVDLibrary();
        return DVDLibrary.put(newTitle, editedDVD);
    }

    /**
     * Edit release date of given DVD
     *
     * @param title       of DVD, used for key in map
     * @param releaseDate new release date to change to
     * @return Changed DVD object or null if not found
     */
    @Override
    public DVD editDVDinfoReleaseDate(String title, LocalDate releaseDate) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD editedDVD = DVDLibrary.get(title);
        editedDVD.setReleaseDate(releaseDate);
        writeDVDLibrary();
        return editedDVD;
    }

    /**
     * Edit director of a DVD
     *
     * @param title    of DVD, used for key in map
     * @param director new director to change to
     * @return Changed DVD object or null if not found
     */
    @Override
    public DVD editDVDinfoDirector(String title, String director) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD editedDVD = DVDLibrary.get(title);
        editedDVD.setDirectorsName(director);
        writeDVDLibrary();
        return editedDVD;
    }

    /**
     * Edit user rating of a DVD
     *
     * @param title title of DVD to change rating of
     * @param score new rating
     * @return DVD with new rating
     */
    @Override
    public DVD editDVDinfoUserRating(String title, int score) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD editedDVD = DVDLibrary.get(title);
        editedDVD.setUserRating(score);
        writeDVDLibrary();
        return editedDVD;

    }

    /**
     * Edit MPAA rating of a DVD
     *
     * @param title  of DVD, used for key in map
     * @param rating new MPAA rating as int
     * @return Changed DVD object or null if not found
     */
    @Override
    public DVD editDVDinfoMPAARating(String title, int rating) throws DVDLibraryDaoException {
        loadDVDLibrary();
        if (rating < 0 || rating > 5) {
            throw new DVDLibraryDaoException("Rating is outside of accepted bound");
        }
        DVD editedDVD = DVDLibrary.get(title);
        editedDVD.setMPAARating(rating);
        writeDVDLibrary();
        return editedDVD;
    }

    /**
     * Edit studio of a DVD.
     *
     * @param title  of DVD, used for key in map.
     * @param studio new studio to change to.
     * @return Changed DVD object or null if not found.
     */
    @Override
    public DVD editDVDinfoStudio(String title, String studio) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD editedDVD = DVDLibrary.get(title);
        editedDVD.setStudio(studio);
        writeDVDLibrary();
        return editedDVD;
    }

    /**
     * Edit DVD note
     *
     * @param title of DVD, used for key in map
     * @param notes new notes to change to
     * @return Changed DVD object or null if not found
     */
    @Override
    public DVD editDVDNotes(String title, String notes) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD editedDVD = DVDLibrary.get(title);
        editedDVD.setNotes(notes);
        writeDVDLibrary();
        return editedDVD;
    }

    /**
     * Method for translating string in expected format to new DVD object
     *
     * @param dvdAsText DVD object formatted as string
     * @return new DVD object
     */
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

    /**
     * Load DVDs from library.txt
     *
     * @throws DVDLibraryDaoException thrown when library.txt cannot be accessed
     */
    private void loadDVDLibrary() throws DVDLibraryDaoException {
        Scanner scan;

        try {
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
        while (scan.hasNextLine()) {
            currentLine = scan.nextLine();
            if (currentLine.equals("") || currentLine.equals(" ")) {
                continue;
            }

            currentDVD = this.unmarshallDVD(currentLine);

            this.DVDLibrary.put(currentDVD.getTitle(), currentDVD);
        }

        scan.close();
    }

    /**
     * Turn DVD object into formatted string
     *
     * @param dvd object to be translated
     * @return formatted string
     */
    private String marshallDVD(DVD dvd) {
                //title
        return dvd.getTitle() + DELIMITER +

                //release Date
                dvd.getReleaseDate() + DELIMITER +

                //MPAA rating
                dvd.getMPAARatingAsInt() + DELIMITER +

                //Directors name
                dvd.getDirectorsName() + DELIMITER +

                //Studio
                dvd.getStudio() + DELIMITER +

                //user ratings
                dvd.getUserRating() + DELIMITER +

                //notes
                dvd.getNotes();
    }

    /**
     * Write to library.txt DVD objects
     *
     * @throws DVDLibraryDaoException when library.txt cannot be found to accessed
     */
    private void writeDVDLibrary() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Couldn't open file");
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD dvd : dvdList) {
            dvdAsText = marshallDVD(dvd);

            out.println(dvdAsText);

            out.flush();
        }

        out.close();
    }
}
