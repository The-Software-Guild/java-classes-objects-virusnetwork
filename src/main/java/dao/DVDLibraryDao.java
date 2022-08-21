package dao;

import dto.DVD;

import java.time.LocalDate;
import java.util.List;

/**
 * DAO interface for DVD library
 *
 * @author Miles Singleton
 */
public interface DVDLibraryDao {

    /**
     * Add DVD to library
     *
     * @param dvd DVD object to add to library
     * @return DVD object in the library
     */
    DVD addDVD(DVD dvd) throws DVDLibraryDaoException;

    /**
     * Get a list of all DVDs in library
     *
     * @return List of all DVDs
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    /**
     * Get specific DVD by title
     *
     * @param title of DVD, used for key in map
     * @return DVD object or null if not found
     */
    DVD getDVD(String title) throws DVDLibraryDaoException;

    /**
     * Remove given DVD from library
     *
     * @param title of DVD, used for key in map
     * @return DVD object or null if not found
     */
    DVD removeDVD(String title) throws DVDLibraryDaoException;

    /**
     * Edit title of specific DVD. Find DVD by old key, remove it, change it's title then re-add under new title
     *
     * @param oldTitle title used as key in map
     * @param newTitle new title to be changed to
     * @return DVD object with new name or null if DVD not found
     */
    DVD editDVDinfoTitle(String oldTitle, String newTitle) throws DVDLibraryDaoException;

    /**
     * Edit release date of given DVD
     *
     * @param title       of DVD, used for key in map
     * @param releaseDate new release date to change to
     * @return Changed DVD object or null if not found
     */
    DVD editDVDinfoReleaseDate(String title, LocalDate releaseDate) throws DVDLibraryDaoException;

    /**
     * Edit director of a DVD
     *
     * @param title    of DVD, used for key in map
     * @param director new director to change to
     * @return Changed DVD object or null if not found
     */
    DVD editDVDinfoDirector(String title, String director) throws DVDLibraryDaoException;


    /**
     * Edit user rating of a DVD
     *
     * @param title of DVD, used for key in map
     * @param score new user rating of DVD
     * @return Changed DVD object or null if not found
     */
    DVD editDVDinfoUserRating(String title, int score) throws DVDLibraryDaoException;

    /**
     * Edit MPAA rating of a DVD
     *
     * @param title  of DVD, used for key in map
     * @param rating new MPAA rating as int
     * @return Changed DVD object or null if not found
     */
    DVD editDVDinfoMPAARating(String title, int rating) throws DVDLibraryDaoException;

    /**
     * Edit studio of a DVD.
     *
     * @param title  of DVD, used for key in map.
     * @param studio new studio to change to.
     * @return Changed DVD object or null if not found.
     */
    DVD editDVDinfoStudio(String title, String studio) throws DVDLibraryDaoException;

    /**
     * Edit DVD note
     *
     * @param title of DVD, used for key in map
     * @param notes new notes to change to
     * @return Changed DVD object or null if not found
     */
    DVD editDVDNotes(String title, String notes) throws DVDLibraryDaoException;
}
