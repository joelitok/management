package management.dao.dao_security_concept;

import org.springframework.data.jpa.repository.JpaRepository;

import management.entities.entities_security_concept.AppRole;

public interface RoleRepository extends JpaRepository<AppRole,Long>{
public AppRole findByRoleName(String roleName); }
