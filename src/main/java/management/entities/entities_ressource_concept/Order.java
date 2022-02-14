package management.entities.entities_ressource_concept;

import javax.persistence.*;

@Entity
@Table ( name = "order_table" )
public class Order {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private long id;

    private String employeeName;
    
    private String position;
    
    private String location;

    private String laptop;
    
    private String phone;

    private String address;

    private String startDate;

    private String shipmentNumber;
    
    private String comment;

    public Order() {
    }

    public Order(String employeeName, String position, String location, String laptop, String phone, String address, String startDate, String shipmentNumber, String comment) {
        this.employeeName = employeeName;
        this.position = position;
        this.location = location;
        this.laptop = laptop;
        this.phone = phone;
        this.address = address;
        this.startDate = startDate;
        this.shipmentNumber = shipmentNumber;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getLaptop() {
        return laptop;
    }
    
    public void setLaptop(String laptop) {
        this.laptop = laptop;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
}
