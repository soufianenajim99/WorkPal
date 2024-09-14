package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryInterface {
    void save(Reservation reservation);
    void delete(Reservation reservation);
    Optional<Reservation> findByMemberAndEspace(int memberId, int espaceId);
    List<Reservation> findByMember(int memberId);
    List<Reservation> findByEspace(int espaceId);
    List<Reservation> findAll();
}
