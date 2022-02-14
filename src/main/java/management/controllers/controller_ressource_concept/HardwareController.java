package management.controllers.controller_ressource_concept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import management.dao.dao_ressource_concept.HardwareRepository;
import management.entities.entities_ressource_concept.Hardware;
import management.error_handler.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hardware")
public class HardwareController {

    @Autowired
    final private HardwareRepository hardwareRepository;

    public HardwareController(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    //lister les Hardware
    @GetMapping("/list")
    public List<Hardware> getAllHardwares() {
        return hardwareRepository.findAll();
    }
     
    //recuperer un Hardware en fonction de son id
    @GetMapping("/{id}")
    public Hardware getHardwareById(@PathVariable long id) throws ResourceNotFoundException {
        return hardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    //ajouter un Hardware
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Hardware createHardware(@RequestBody Hardware hardware) {
        return hardwareRepository.save(hardware);
    }

    //modifier un hardware
    @PutMapping("/{id}")
    public Hardware updateHardwareById(@PathVariable long id, @RequestBody Hardware hardware) throws ResourceNotFoundException{
        Hardware existingHardware = this.hardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        existingHardware.setName(hardware.getName());
        existingHardware.setType(hardware.getType());
        return this.hardwareRepository.save(existingHardware);
    }

    //supprimer un hardware
    @DeleteMapping("/{id}")
    public Hardware deleteHardwareById(@PathVariable long id) {
        return this.hardwareRepository.findById(id)
                .map(hardware -> {
                    this.hardwareRepository.deleteById(id);
                    return hardware;
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
