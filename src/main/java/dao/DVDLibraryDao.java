package dao;

import dto.DVD;

import java.util.Date;
import java.util.List;

public interface DVDLibraryDao {

    /**
     * Add DVD to library
     *
     * @param title the title of the dvd, used as ID
     * @param dvd   DVD object to add to library
     * @return DVD object in the library
     */
    DVD addDVD(String title, DVD dvd);

    /**
     * @return List of all DVDs
     */
    List<DVD> getAllDVDs();

    /**
     * Get DVD object
     *
     * @param title of DVD
     * @return DVD of title or null if not found
     */
    DVD getDVD(String title);

    /**
     * Remove DVD from libary
     *
     * @param title
     * @return
     */
    DVD removeDVD(String title);

    /**
     * Edit title of DVD
     *
     * @param oldTitle
     * @param newTitle
     * @return
     */
    DVD editDVDinfoTitle(String oldTitle, String newTitle);

    /**
     * Edit realease date
     *
     * @param title
     * @param releaseDate
     * @return
     */
    DVD editDVDinfoReleaseDate(String title, Date releaseDate);

    /**
     * Edit director of a DVD
     *
     * @param title
     * @param director
     * @return
     */
    DVD editDVDinfoDirector(String title, String director);


    /**
     * Edit user rating of a DVD
     *
     * @param title
     * @param score
     * @return
     */
    DVD editDVDinfoUserRating(String title, byte score);

    /**
     * Edit MPAA rating of a DVD
     *
     * @param title
     * @param rating
     * @return
     */
    DVD editDVDinfoMPAARating(String title, byte rating);

    /**
     * Edit studio of a DVD
     *
     * @param title
     * @param studio
     * @return
     */
    DVD editDVDinfoStudio(String title, String studio);
}
