package management.controllers.controller_ressource_concept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import management.dao.dao_ressource_concept.OrderRepository;
import management.entities.entities_ressource_concept.Order;
import management.error_handler.ResourceNotFoundException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    final private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/list")
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable (value = "id") long id) throws ResourceNotFoundException{
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PostMapping()
    public Order createOrder (@RequestBody Order order){
        return this.orderRepository.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrderById(@PathVariable (value = "id") long id, @RequestBody Order changedOrder) throws ResourceNotFoundException{
        Order existingOrder = this.orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id));
        existingOrder.setEmployeeName(changedOrder.getEmployeeName());
        existingOrder.setPosition(changedOrder.getPosition());
        existingOrder.setLocation(changedOrder.getLocation());
        existingOrder.setLaptop(changedOrder.getLaptop());
        existingOrder.setPhone(changedOrder.getPhone());
        existingOrder.setAddress(changedOrder.getAddress());
        existingOrder.setStartDate(changedOrder.getStartDate());
        existingOrder.setShipmentNumber(changedOrder.getShipmentNumber());
        existingOrder.setComment(changedOrder.getComment());
        return this.orderRepository.save(existingOrder);
    }
//delete data
    @DeleteMapping("/{id}")
    public Order deleteOrderById (@PathVariable ("id") long id) {
        return this.orderRepository.findById(id)
                .map(order -> {
                    this.orderRepository.deleteById(id);
                    return order;
                }).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
