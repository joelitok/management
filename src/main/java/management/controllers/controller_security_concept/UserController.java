package management.controllers.controller_security_concept;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import management.dao.dao_security_concept.UserRepository;
import management.entities.entities_security_concept.AppUser;
import management.error_handler.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    
    @DeleteMapping("/{id}")
    public AppUser deleteHardwareById(@PathVariable Long id) {
        return this.userRepository.findById(id)
                .map(hardware -> {
                    this.userRepository.deleteById(id);
                    return hardware;
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    
}
