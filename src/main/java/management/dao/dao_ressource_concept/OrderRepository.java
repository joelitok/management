package management.dao.dao_ressource_concept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import management.entities.entities_ressource_concept.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
