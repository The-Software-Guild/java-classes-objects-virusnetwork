package dao;

import dto.DVD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DVDLibraryDaoImplTest {
    static DVDLibraryDao testDao;

    @BeforeEach
    void setUp() throws IOException {
        String testFile = "testDVD.txt";
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoImpl(testFile);
    }

    @Test
    void addDVD() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;

        DVD testDVD = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);
        testDao.addDVD(testDVD);
        DVD addedDVD = testDao.getDVD(title);

        assertEquals(testDVD.getTitle(), addedDVD.getTitle(), "checking DVD title");
        assertEquals(testDVD.getReleaseDate(), addedDVD.getReleaseDate());
        assertEquals(testDVD.getDirectorsName(), addedDVD.getDirectorsName());
        assertEquals(testDVD.getStudio(), addedDVD.getStudio());
        assertEquals(testDVD.getMPAARatingAsInt(), addedDVD.getMPAARatingAsInt());
        assertEquals(testDVD.getMPAARatingAsString(), addedDVD.getMPAARatingAsString());
        assertEquals(testDVD.getNotes(), addedDVD.getNotes());
        assertEquals(testDVD.getMPAARating(), addedDVD.getMPAARating());
    }

    @Test
    void getAllDVDs() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        //City of God::2003-01-03::4::Fernando Meirelles::Miramax::5::No Notes
        title = "City of God";
        releaseDate = LocalDate.parse("2003-01-03");
        mpaaRating = 4;
        director = "Fernando Meirelles";
        studio = "Miramax";
        userRating = 5;
        DVD testDVD2 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);
        testDao.addDVD(testDVD2);

        List<DVD> dvdList = testDao.getAllDVDs();

        assertNotNull(dvdList);
        assertEquals(dvdList.size(), 2);
        assert (dvdList.contains(testDVD1));
        assert (dvdList.contains(testDVD2));

    }

    @Test
    void getDVD() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);

        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());

        assertNotNull(retrievedDVD);
        assertEquals(retrievedDVD, testDVD1);
    }

    @Test
    void removeDVD() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);

        DVD retrievedDVD = testDao.removeDVD(testDVD1.getTitle());

        assertNotNull(retrievedDVD);
        assertEquals(retrievedDVD, testDVD1);

        retrievedDVD = testDao.getDVD(testDVD1.getTitle());
        assertNull(retrievedDVD);
    }

    @Test
    void editDVDinfoTitle() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        String newtitle = "new title";
        testDao.addDVD(testDVD1);
        testDao.editDVDinfoTitle(testDVD1.getTitle(), newtitle);

        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());
        assertNull(retrievedDVD);

        retrievedDVD = testDao.getDVD(newtitle);
        assertNotEquals(retrievedDVD, testDVD1);
        assertEquals(retrievedDVD.getTitle(), newtitle);
    }

    @Test
    void editDVDinfoReleaseDate() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);

        LocalDate newDate = LocalDate.parse("1998-12-12");
        testDao.editDVDinfoReleaseDate(testDVD1.getTitle(), newDate);
        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());
        assertEquals(retrievedDVD.getReleaseDate(), newDate);
    }

    @Test
    void editDVDinfoDirector() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);
        String newDirector = "new director";
        testDao.editDVDinfoDirector(testDVD1.getTitle(), newDirector);
        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());

        assertEquals(retrievedDVD.getDirectorsName(), newDirector);
    }

    @Test
    void editDVDinfoUserRating() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);
        int newUserRating = 3;
        testDao.editDVDinfoUserRating(testDVD1.getTitle(), newUserRating);
        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());

        assertEquals(retrievedDVD.getUserRating(), newUserRating);
    }

    @Test
    void editDVDinfoMPAARating() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);
        int newMPAARating = 3;
        testDao.editDVDinfoMPAARating(testDVD1.getTitle(), newMPAARating);
        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());

        assertEquals(retrievedDVD.getMPAARatingAsInt(), newMPAARating);
    }

    @Test
    void editDVDinfoStudio() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);
        String newNotes = "new notes";
        testDao.editDVDNotes(testDVD1.getTitle(), newNotes);
        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());

        assertEquals(retrievedDVD.getNotes(), newNotes);
    }

    @Test
    void editDVDNotes() throws DVDLibraryDaoException {
        String title = "Forbidden Planet";
        LocalDate releaseDate = LocalDate.parse("1956-03-15");
        int mpaaRating = 1;
        String director = "Fred M. Wilcox";
        String studio = "Metro-Goldwyn-Mayer";
        int userRating = 4;
        DVD testDVD1 = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        testDao.addDVD(testDVD1);
        String newStudio = "new studio";
        testDao.editDVDinfoStudio(testDVD1.getTitle(), newStudio);
        DVD retrievedDVD = testDao.getDVD(testDVD1.getTitle());

        assertEquals(retrievedDVD.getStudio(), newStudio);
    }
}