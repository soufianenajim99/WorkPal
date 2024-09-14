package org.workpal.Models;

import java.time.LocalDate;

public class Reservation {
    private Member member;
    private Espace espace;
    private LocalDate reservationDate;

    public Reservation(Member member, Espace espace, LocalDate reservationDate) {
        this.member = member;
        this.espace = espace;
        this.reservationDate = reservationDate;
    }

    public Reservation() {
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

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "member=" + member +
                ", espace=" + espace +
                ", reservationDate=" + reservationDate +
                '}';
    }
}
