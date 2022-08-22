import controller.DVDLibraryController;
import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dao.DVDLibraryDaoImpl;
import view.DVDLibraryView;
import view.UserIO;
import view.UserIOImpl;

public class Main {
    public static void main(String[] args) throws DVDLibraryDaoException {
        UserIO io = new UserIOImpl();
        DVDLibraryView view = new DVDLibraryView(io);
        DVDLibraryDao dao = new DVDLibraryDaoImpl();
        DVDLibraryController controller = new DVDLibraryController(view, dao);
        controller.run();
    }
}
