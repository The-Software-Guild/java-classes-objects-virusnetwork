package controller;

import dao.DVDLibraryDao;
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

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                //get menu selection

                switch (menuSelection) {
                    case 1 -> createDVD();
                    case 2 -> removeDVD();
                    //case 3 -> TODO: edit DVD info
                    case 4 -> viewDVD();
                    // case 5 -> TODO: Search for DVD by title
                    case 6 -> keepGoing = false;
                    default -> unknownCommand();
                }
            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void createDVD() {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    public void listDVDs() {
        view.displayDisplayAllDVDBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDLibrary(dvdList);
    }

    public void viewDVD() {
        view.displayDisplayDVDBanner();
        view.displayDVD(dao.getDVD(view.getDVDTitle()));
    }

    public void removeDVD() {
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