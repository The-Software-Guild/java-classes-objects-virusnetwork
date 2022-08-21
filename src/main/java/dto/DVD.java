package dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class for DVDs
 *
 * @author Miles Singleton
 */
public class DVD {
    private String title;
    private LocalDate releaseDate;
    private mpaaRating mpaaRating;
    private String directorsName;
    private String studio;
    private int userRating;
    private String notes;

    /**
     * Constructor for DVD class
     *
     * @param title         Title of DVD
     * @param releaseDate   release date of DVD in yyyy-MM-dd format
     * @param mpaaRating    MPAA rating as int, set MPAA switch case
     * @param directorsName name of director
     * @param studio        studio which made
     * @param userRating    User's rating of film out of 5
     */
    public DVD(String title, LocalDate releaseDate, int mpaaRating, String directorsName, String studio,
               int userRating) {
        this(title, releaseDate, mpaaRating, directorsName, studio, userRating, "No Notes");
    }

    /**
     * Constructor of DVD
     *
     * @param title         Title of DVD
     * @param releaseDate   release date of DVD in yyyy-MM-dd format
     * @param mpaaRating    MPAA rating as int, set MPAA switch case
     * @param directorsName name of director
     * @param studio        studio which made
     * @param userRating    User's rating of film out of 5
     * @param notes         User's notes on DVD
     */
    public DVD(String title, LocalDate releaseDate, int mpaaRating, String directorsName, String studio,
               int userRating, String notes) {
        this.title = title;
        this.releaseDate = releaseDate;
        setMPAARating(mpaaRating);
        this.directorsName = directorsName;
        this.studio = studio;
        this.userRating = userRating;
        this.notes = notes;
    }

    /**
     * Get DVD's title
     *
     * @return DVD's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Change title of DVD
     *
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get release date
     *
     * @return DVD's release date
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Set release date of DVD
     *
     * @param releaseDate new release date
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Return MPAA rating in enum MPAA rating
     *
     * @return MPAA rating
     */
    public mpaaRating getMPAARating() {
        return mpaaRating;
    }

    /**
     * Set MPAA rating with int
     * 0 = No Rating
     * 1 = G – General Audiences
     * 2 = PG – Parental Guidance Suggested
     * 3 = PG-13 – Parents Strongly Cautioned
     * 4 = R – Restricted
     * 5 = NC-17 – Adults Only
     *
     * @param rating new rating as int
     */
    public void setMPAARating(int rating) {
        switch (rating) {
            case 0 -> this.mpaaRating = mpaaRating.NR;
            case 1 -> this.mpaaRating = mpaaRating.G;
            case 2 -> this.mpaaRating = mpaaRating.PG;
            case 3 -> this.mpaaRating = mpaaRating.PG13;
            case 4 -> this.mpaaRating = mpaaRating.R;
            case 5 -> this.mpaaRating = mpaaRating.NC17;
        }
    }

    /**
     * Get MPAA rating as string
     *
     * @return string presentation of MPAA rating
     */
    public String getMPAARatingAsString() {
        switch (this.mpaaRating) {
            case G -> {
                return "G";
            }
            case PG -> {
                return "PG";
            }
            case PG13 -> {
                return "PG13";
            }
            case R -> {
                return "R";
            }
            case NR -> {
                return "NR";
            }
            case NC17 -> {
                return "NC17";
            }
            default -> throw new IllegalArgumentException("Unknown rating for DVD MPAA rating: " + this.mpaaRating);
        }
    }

    /**
     * Get MPAA rating as int
     *
     * @return MPAA rating as int
     */
    public int getMPAARatingAsInt() {
        switch (this.mpaaRating) {
            case NR -> {
                return 0;
            }
            case G -> {
                return 1;
            }
            case PG -> {
                return 2;
            }
            case PG13 -> {
                return 3;
            }
            case R -> {
                return 4;
            }
            case NC17 -> {
                return 5;
            }
            default -> throw new IllegalArgumentException("Unknown rating for DVD MPAA rating: " + this.mpaaRating);
        }
    }

    /**
     * Get directors name
     *
     * @return directors name
     */
    public String getDirectorsName() {
        return directorsName;
    }

    /**
     * Change directors name
     *
     * @param directorsName new directors name
     */
    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    /**
     * Get studio
     *
     * @return DVD's studio
     */
    public String getStudio() {
        return studio;
    }

    /**
     * Change DVD studio
     *
     * @param studio new studio
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * Get User rating out of 5
     *
     * @return user rating
     */
    public int getUserRating() {
        return userRating;
    }

    /**
     * Change user rating
     *
     * @param userRating new user rating
     */
    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    /**
     * Get the DVD's notes
     *
     * @return DVD's notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Change DVD notes
     *
     * @param notes new notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DVD dvd = (DVD) o;

        if (userRating != dvd.userRating) return false;
        if (!Objects.equals(title, dvd.title)) return false;
        if (!Objects.equals(releaseDate, dvd.releaseDate)) return false;
        if (mpaaRating != dvd.mpaaRating) return false;
        if (!Objects.equals(directorsName, dvd.directorsName)) return false;
        if (!Objects.equals(studio, dvd.studio)) return false;
        return Objects.equals(notes, dvd.notes);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (mpaaRating != null ? mpaaRating.hashCode() : 0);
        result = 31 * result + (directorsName != null ? directorsName.hashCode() : 0);
        result = 31 * result + (studio != null ? studio.hashCode() : 0);
        result = 31 * result + userRating;
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    /**
     * ENUM for mpaa rating, simplifies internal coding of class
     */
    private enum mpaaRating {
        NR, G, PG, PG13, R, NC17
    }
}
