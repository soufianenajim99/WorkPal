package org.workpal.Models;

public class Feedback {
    private int id;
    private Member member;
    private Espace espace;
    private String feedback;
    private int rating;

    public Feedback(Member member, Espace espace, String feedback, int rating, int id) {
        this.member = member;
        this.espace = espace;
        this.feedback = feedback;
        this.rating = rating;
        this.id = id;
    }

    public Feedback() {
    }

    public Feedback(Member member, Espace espace, String feedback, int rating) {
        this.member = member;
        this.espace = espace;
        this.feedback = feedback;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", member=" + member +
                ", espace=" + espace +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                '}';
    }
}
