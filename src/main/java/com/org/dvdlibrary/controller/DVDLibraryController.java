package com.org.dvdlibrary.controller;

import com.org.dvdlibrary.dao.DVDLibraryDao;
import com.org.dvdlibrary.dao.DVDLibraryDaoException;
import com.org.dvdlibrary.dto.DVD;
import com.org.dvdlibrary.view.DVDLibraryView;

/**
 * Controller class for DVD library
 *
 * @author Miles Singleton
 */
public class DVDLibraryController {
    private final DVDLibraryView view;
    private final DVDLibraryDao dao;

    /**
     * Constructor for DVD library controller
     *
     * @param view used to interact with the view layer
     * @param dao  used to interact with the data access object
     */
    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    /**
     * Runs main program loop
     */
    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        while (keepGoing) {
            menuSelection = this.getMenuSelection();

            try {
                switch (menuSelection) {
                    case 1 -> createDVD();
                    case 2 -> removeDVD();
                    case 3 -> editDVDInfo();
                    case 4 -> viewDVD();
                    case 5 -> searchForDVDByTitle();
                    case 6 -> listDVDs();
                    case 7 -> keepGoing = false;
                    default -> unknownCommand();
                }

            } catch (DVDLibraryDaoException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        exitMessage();
    }

    /**
     * Used to print menu and get user selection
     *
     * @return 1-7 to select menu in run
     */
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    /**
     * Edit specific DVD info. Ask user what specific field then call function to edit that field.
     *
     * @throws DVDLibraryDaoException if library.txt cannot be accessed
     */
    private void editDVDInfo() throws DVDLibraryDaoException {
        String dvdTitle = view.getDVDTitle();
        int editChoice = view.getEditInfo(dao.getDVD(dvdTitle));
        switch (editChoice) {
            case 1 -> editDVDTitle(dvdTitle);
            case 2 -> editReleaseDate(dvdTitle);
            case 3 -> editMPAARating(dvdTitle);
            case 4 -> editDirector(dvdTitle);
            case 5 -> editStudio(dvdTitle);
            case 6 -> editUserRating(dvdTitle);
            case 7 -> editDVDNotes(dvdTitle);
        }

    }

    /**
     * Method for searching DVD by title
     * 6. Allow the user to search for a DVD by title on requirements for assessment
     *
     * @throws DVDLibraryDaoException is thrown when library.txt cannot be found/accessed/written to
     */
    private void searchForDVDByTitle() throws DVDLibraryDaoException {
        view.displaySearchDVDByTitleBanner();
        DVD dvd = dao.getDVD(view.findDVDByTitle());
        view.isDVDThere(dvd);
    }

    /**
     * Method for editing title of a DVD
     *
     * @param oldTitle old title used for key in hash map in DAO
     */
    private void editDVDTitle(String oldTitle) throws DVDLibraryDaoException {
        String newTitle = view.editDVDTitle();
        dao.editDVDinfoTitle(oldTitle, newTitle);
    }

    /**
     * Method for changing release date of a DVD
     *
     * @param title used for key in hash map in DAO
     */
    private void editReleaseDate(String title) throws DVDLibraryDaoException {
        dao.editDVDinfoReleaseDate(title, view.editDVDReleaseDate());
    }

    /**
     * Method for editing MPAA rating of a DVD
     *
     * @param title used for key in hash map in DAO
     * @throws DVDLibraryDaoException thrown if file cannot be found
     */
    private void editMPAARating(String title) throws DVDLibraryDaoException {
        dao.editDVDinfoMPAARating(title, view.editMPAARating());
    }

    /**
     * Method for editing director's name on a DVD
     *
     * @param title used for key in hash map in DAO
     */
    private void editDirector(String title) throws DVDLibraryDaoException {
        dao.editDVDinfoDirector(title, view.editDirector());
    }

    /**
     * Method for editing the studio of a DVD
     *
     * @param title used for key in hash map in DAO
     */
    private void editStudio(String title) throws DVDLibraryDaoException {
        dao.editDVDinfoStudio(title, view.editStudio());
    }

    /**
     * Edit user ratings for a DVD
     *
     * @param title used for key in hash map in DAO
     */
    private void editUserRating(String title) throws DVDLibraryDaoException {
        dao.editDVDinfoUserRating(title, view.editUserRating());
    }

    /**
     * Edit the notes of a DVD
     *
     * @param title used for key in hash map in DAO
     */
    private void editDVDNotes(String title) throws DVDLibraryDaoException {
        dao.editDVDNotes(title, view.newDVDNote());
    }

    /**
     * Create a new DVD object and add to DVD collection in DAO
     *
     * @throws DVDLibraryDaoException thrown if file cannot be accessed
     */
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD);
        view.displayCreateSuccessBanner();
    }

    /**
     * Lists all DVDs in DAO collection
     *
     * @throws DVDLibraryDaoException thrown if file cannot be accessed
     */
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllDVDBanner();
        view.displayDVDLibrary(dao.getAllDVDs());
    }

    /**
     * View a specific DVD
     *
     * @throws DVDLibraryDaoException thrown if file cannot be accessed
     */
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        view.displayDVD(dao.getDVD(view.getDVDTitle()));
    }

    /**
     * Remove given DVD
     *
     * @throws DVDLibraryDaoException thrown if file cannot be accessed
     */
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        view.displayRemovedDVDResult(dao.removeDVD(view.getDVDTitle()));
    }

    /**
     * Used to inform user of unknown command
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * Used to give user exit message
     */
    private void exitMessage() {
        view.displayExitBanner();
    }
}