package management.services;

import management.entities.entities_security_concept.AppRole;
import management.entities.entities_security_concept.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username,String roleName);
	public AppUser findUserByUsername(String username);
	public void deleteUser();
	public void deleteRole();
}
