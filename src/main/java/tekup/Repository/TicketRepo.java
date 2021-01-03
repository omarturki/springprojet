package tekup.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.module.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
