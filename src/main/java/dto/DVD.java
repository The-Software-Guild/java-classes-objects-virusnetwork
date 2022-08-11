package dto;

import java.util.Date;

public class DVD {
    private String title;
    private Date releaseDate;
    private mpaaRating rating;
    private String directorsName;
    private String studio;
    private byte userRating;

    public DVD(String title, Date releaseDate, mpaaRating rating, String directorsName, String studio,
               byte userRating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.directorsName = directorsName;
        this.studio = studio;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public mpaaRating getRating() {
        return rating;
    }

    public void setMPAArating(byte rating) {
        switch (rating) {
            case 0 -> this.rating = mpaaRating.NR;
            case 1 -> this.rating = mpaaRating.G;
            case 2 -> this.rating = mpaaRating.PG;
            case 3 -> this.rating = mpaaRating.PG13;
            case 4 -> this.rating = mpaaRating.R;
            case 5 -> this.rating = mpaaRating.NC17;
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

    public byte getUserRating() {
        return userRating;
    }

    public void setUserRating(byte userRating) {
        this.userRating = userRating;
    }

    public enum mpaaRating {
        NR,
        G,
        PG,
        PG13,
        R,
        NC17
    }
}
