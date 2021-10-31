package ua.goit.projectmanager.controller.servletcontroller;

import ua.goit.projectmanager.model.Customer;

public class CustomerController extends Controller<Customer, Long> {
    protected CustomerController() {
        super(Customer.class);
    }
}
