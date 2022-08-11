package dao;

import dto.DVD;

import java.util.*;

public class DVDLibraryDaoImpl implements DVDLibraryDao {
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
     * @param title title of DVD to change release date of
     * @param releaseDate new release date
     * @return DVD with new release date
     */
    @Override
    public DVD editDVDinfoReleaseDate(String title, Date releaseDate) {
        DVD temp = DVDLibrary.get(title);
        temp.setReleaseDate(releaseDate);
        return temp;
    }

    /**
     * Edit director of a DVD
     *
     * @param title title of DVD to change
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
    public DVD editDVDinfoUserRating(String title, byte score) {
        DVDLibrary.get(title).setUserRating(score);
        return DVDLibrary.get(title);

    }

    /**
     * Edit MPAA rating of a DVD
     *
     * @param title title of DVD to change MPAA rating
     * @param rating new rating
     * @return DVD with new rating
     */
    @Override
    public DVD editDVDinfoMPAARating(String title, byte rating) {
        if (rating < 0 || rating > 5) {
            //throw exception
        }
        DVDLibrary.get(title).setMPAArating(rating);
        return DVDLibrary.get(title);
    }

    /**
     * Edit studio of a DVD
     *
     * @param title title of DVD
     * @param studio new studio
     * @return DVD with new studio
     */
    @Override
    public DVD editDVDinfoStudio(String title, String studio) {
        DVDLibrary.get(title).setStudio(studio);
        return DVDLibrary.get(title);
    }
}
