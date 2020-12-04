package loginsystem.entity;

public class Comment {
    private String user;
    private String artID;
    private String comment;

    public Comment() {
    }

    public Comment(String user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public Comment(String user, String artID, String comment) {
        this.user = user;
        this.artID = artID;
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public String getArtID() {
        return artID;
    }

    public String getComment() {
        return comment;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setArtID(String artID) {
        this.artID = artID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
