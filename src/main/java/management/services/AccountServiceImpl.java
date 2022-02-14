package management.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import management.dao.dao_security_concept.RoleRepository;
import management.dao.dao_security_concept.UserRepository;
import management.entities.entities_security_concept.AppRole;
import management.entities.entities_security_concept.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public AppUser saveUser(AppUser user) {
	String hashPW =bCryptPasswordEncoder.encode(user.getPassword());
	user.setPassword(hashPW); 
	return userRepository.save(user);
	}
	@Override
	public AppRole saveRole(AppRole role) {
	return roleRepository.save(role);
	}
	@Override
	public void addRoleToUser(String username, String roleName) {
	AppRole role = roleRepository.findByRoleName(roleName);
	AppUser user =userRepository.findByUsername(username);
	user.getRoles().add(role); 
	}
	@Override
	public AppUser findUserByUsername(String username) {
	return userRepository.findByUsername(username); }
	
	@Override
	public void deleteUser() {
		 userRepository.deleteAll();
		
	}

	@Override
	public void deleteRole() {
		roleRepository.deleteAll();
		
	} 
}

