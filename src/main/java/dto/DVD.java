package dto;

import java.time.LocalDate;

public class DVD {
    private String title;
    private LocalDate releaseDate;
    private mpaaRating mpaaRating;
    private String directorsName;
    private String studio;
    private int userRating;
    private String notes;

    public DVD(String title, LocalDate releaseDate, int mpaaRating, String directorsName, String studio,
               int userRating) {
        this(title, releaseDate, mpaaRating, directorsName, studio, userRating, "No Notes");
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public mpaaRating getRating() {
        return mpaaRating;
    }

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

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private enum mpaaRating {
        NR, G, PG, PG13, R, NC17
    }
}
