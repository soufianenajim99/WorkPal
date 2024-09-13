package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.Reservation;

import java.util.List;

public interface ReservationRepositoryInterface {
    void save(Reservation reservation);
    List<Reservation> findByMemberId(int memberId);
    List<Reservation> findByEspaceId(int espaceId);
    void delete(int memberId, int espaceId);
}
