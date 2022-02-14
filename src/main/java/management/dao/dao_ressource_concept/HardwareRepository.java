package management.dao.dao_ressource_concept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import management.entities.entities_ressource_concept.Hardware;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
}
