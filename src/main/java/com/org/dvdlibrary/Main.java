package com.org.dvdlibrary;

import com.org.dvdlibrary.controller.DVDLibraryController;
import com.org.dvdlibrary.dao.DVDLibraryDao;
import com.org.dvdlibrary.dao.DVDLibraryDaoException;
import com.org.dvdlibrary.dao.DVDLibraryDaoImpl;
import com.org.dvdlibrary.view.DVDLibraryView;
import com.org.dvdlibrary.view.UserIO;
import com.org.dvdlibrary.view.UserIOImpl;

public class Main {
    public static void main(String[] args) throws DVDLibraryDaoException {
        UserIO io = new UserIOImpl();
        DVDLibraryView view = new DVDLibraryView(io);
        DVDLibraryDao dao = new DVDLibraryDaoImpl();
        DVDLibraryController controller = new DVDLibraryController(view, dao);
        controller.run();
    }
}
