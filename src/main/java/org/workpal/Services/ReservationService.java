package org.workpal.Services;

import org.workpal.Models.Reservation;
import org.workpal.Repositories.RepositoryInterfaces.ReservationRepositoryInterface;
import org.workpal.Services.ServicesInterfaces.ReservationServiceInterface;

import java.util.List;
import java.util.Optional;

public class ReservationService implements ReservationServiceInterface {private final ReservationRepositoryInterface reservationRepo;

    public ReservationService(ReservationRepositoryInterface reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepo.save(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationRepo.delete(reservation);
    }

    @Override
    public Optional<Reservation> findByMemberAndEspace(int memberId, int espaceId) {
        return reservationRepo.findByMemberAndEspace(memberId, espaceId);
    }

    @Override
    public List<Reservation> findByMember(int memberId) {
        return reservationRepo.findByMember(memberId);
    }

    @Override
    public List<Reservation> findByEspace(int espaceId) {
        return reservationRepo.findByEspace(espaceId);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }
}
