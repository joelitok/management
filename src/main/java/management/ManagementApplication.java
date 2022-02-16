package management;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import management.dao.dao_ressource_concept.HardwareRepository;
import management.dao.dao_ressource_concept.OrderRepository;
import management.entities.entities_ressource_concept.Hardware;
import management.entities.entities_ressource_concept.Order;
import management.entities.entities_security_concept.AppRole;
import management.entities.entities_security_concept.AppUser;
import management.services.AccountService;

@SpringBootApplication
public class ManagementApplication implements  CommandLineRunner {

	@Autowired
	private HardwareRepository HardwareRepository;
	
	@Autowired
	private OrderRepository OrderRepository;

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE(){
	return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... args) throws Exception{

	accountService.deleteUser();
	accountService.saveUser(new AppUser(null,"admin","1234",null));
	accountService.saveUser(new AppUser(null,"user","1234",null));
	
	accountService.deleteRole();
	accountService.saveRole(new AppRole(null,"ADMIN"));
	accountService.saveRole(new AppRole(null,"USER"));
	
	accountService.addRoleToUser("admin", "ADMIN");
	accountService.addRoleToUser("user", "USER");

	OrderRepository.deleteAll();
	Stream.of("T1","T2","T3").forEach(t->{
		OrderRepository.save(new Order("joel"+Math.random(),t, t,t, t, t, t, t, t));
	}); 

	HardwareRepository.deleteAll();
	Stream.of("h1","h2","h3").forEach(t->{
		HardwareRepository.save(new Hardware(null,t));
	});
	
	OrderRepository.findAll().forEach(t->{
	System.out.println(t.getEmployeeName());
	});
	}
}


