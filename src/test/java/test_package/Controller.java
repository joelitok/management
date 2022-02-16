package test_package;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import management.controllers.controller_ressource_concept.OrderController;


import static org.assertj.core.api.Assertions.assertThat;

/* Checks if context created the controllers */

@SpringBootTest
public class Controller {

    @Autowired
    private OrderController orderController;

   

    @Test
    public void orderContextLoads() throws Exception {
        assertThat (orderController).isNotNull();
    }
/*
    @Test
    public void loginContextLoads() throws Exception {
        assertThat (loginController).isNotNull();
    }*/
}
