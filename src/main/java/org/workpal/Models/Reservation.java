package org.workpal.Models;

import java.time.LocalDate;

public class Reservation {
    private int memberId;
    private int espaceId;
    private LocalDate reservationDate;

    public Reservation() {
    }

    public Reservation(int memberId, int espaceId, LocalDate reservationDate) {
        this.memberId = memberId;
        this.espaceId = espaceId;
        this.reservationDate = reservationDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getEspaceId() {
        return espaceId;
    }

    public void setEspaceId(int espaceId) {
        this.espaceId = espaceId;
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
                "memberId=" + memberId +
                ", espaceId=" + espaceId +
                ", reservationDate=" + reservationDate +
                '}';
    }
}
