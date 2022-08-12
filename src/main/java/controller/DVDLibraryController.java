package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dto.DVD;
import view.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {
    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() throws DVDLibraryDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
           menuSelection = this.getMenuSelection();

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
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void editDVDInfo() throws DVDLibraryDaoException {
        String dvdTitle = view.getDVDTitle();
        int editChoice = view.getEditInfo(dao.getDVD(dvdTitle));
        switch (editChoice)
        {
            case 1 -> editDVDTitle(dvdTitle);
            case 2 -> editReleaseDate(dvdTitle);
            case 3 -> editMPAARating(dvdTitle);
            case 4 -> editDirector(dvdTitle);
            case 5 -> editStudio(dvdTitle);
            case 6 -> editUserRating(dvdTitle);
            case 7 -> editDVDNotes(dvdTitle);
        }

    }

    public void searchForDVDByTitle() throws DVDLibraryDaoException {
        view.displaySearchDVDByTitleBanner();
        DVD dvd = dao.getDVD(view.findDVDByTitle());
        view.isDVDThere(dvd);
    }

    public void editDVDTitle(String oldTitle)
    {
        dao.editDVDinfoTitle(oldTitle, view.editDVDTitle());
    }

    public void editReleaseDate(String title)
    {
        dao.editDVDinfoReleaseDate(title,view.editDVDReleaseDate());
    }

    public void editMPAARating(String title)
    {
        dao.editDVDinfoMPAARating(title,view.editMPAARating());
    }

    public void editDirector(String title)
    {
        dao.editDVDinfoDirector(title,view.editDirector());
    }

    public void editStudio(String title)
    {
        dao.editDVDinfoStudio(title,view.editStudio());
    }

    public void editUserRating(String title)
    {
        dao.editDVDinfoUserRating(title,view.editUserRating());
    }

    public void editDVDNotes(String title)
    {
        dao.editDVDNotes(title,view.newDVDNote());
    }

    public void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    public void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllDVDBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDLibrary(dvdList);
    }

    public void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        view.displayDVD(dao.getDVD(view.getDVDTitle()));
    }

    public void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        view.displayRemovedDVDResult(dao.removeDVD(view.getDVDTitle()));
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}