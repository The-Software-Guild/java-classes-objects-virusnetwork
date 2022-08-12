package dao;

import dto.DVD;

import java.io.*;
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
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        this.loadRoaster();
        DVD newDVD = DVDLibrary.put(title, dvd);
        writeDVDCollection();
        return newDVD;
    }

    /**
     * @return List of all DVDs
     */
    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadRoaster();
        return new ArrayList<>(DVDLibrary.values());
    }

    /**
     * Get DVD object
     *
     * @param title of DVD
     * @return DVD of title or null if not found
     */
    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadRoaster();
        return DVDLibrary.get(title);
    }

    /**
     * Remove DVD from libary
     *
     * @param title title of DVD to remove
     * @return DVD added to library
     */
    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadRoaster();
        DVD oldDVD = DVDLibrary.remove(title);
        writeDVDCollection();
        return oldDVD;
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
        DVDLibrary.get(title).setMPAARating(rating);
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
            if(currentLine.equals("") || currentLine.equals(" "))
            {
                continue;
            }

            currentDVD = this.unmarshallDVD(currentLine);

            this.DVDLibrary.put(currentDVD.getTitle(),currentDVD);
        }

        scan.close();
    }

    private String marshallDVD(DVD dvd)
    {
        //Title is index 0
        //release date is index 1
        //mpaa rating is index 2
        //director is index 3
        //studio is index 4
        //user rating is index 5
        //notes is index 6

        StringBuilder str = new StringBuilder();

        //title
        str.append(dvd.getTitle()).append(DELIMITER);

        //release Date
        str.append(dvd.getReleaseDate()).append(DELIMITER);

        //get MPAA rating
        str.append(dvd.getMPAARatingAsInt()).append(DELIMITER);

        //Get directors name
        str.append(dvd.getDirectorsName()).append(DELIMITER);

        //Get studio
        str.append(dvd.getStudio()).append(DELIMITER);

        //get user ratings
        str.append(dvd.getUserRating()).append(DELIMITER);

        //get notes
        str.append(dvd.getNotes());

        return str.toString();
    }

    private void writeDVDCollection() throws DVDLibraryDaoException
    {
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Couldn't open file");
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for(DVD dvd : dvdList)
        {
            dvdAsText = marshallDVD(dvd);

            out.println(dvdAsText);

            out.flush();
        }

        out.close();
    }
}
