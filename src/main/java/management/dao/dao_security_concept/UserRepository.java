package management.dao.dao_security_concept;

import org.springframework.data.jpa.repository.JpaRepository;

import management.entities.entities_security_concept.AppUser;

public interface UserRepository extends JpaRepository<AppUser,Long>{
	public AppUser findByUsername(String username);
}
