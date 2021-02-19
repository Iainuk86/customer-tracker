package org.iainuk.tracker.controller;

import org.iainuk.tracker.Customer;
import org.iainuk.tracker.dao.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/list")
    public String showCustomerList(Model model) {
        List<Customer> customers = (List<Customer>) customerRepo.findAllByOrderByFirstName();

        model.addAttribute("customers", customers);

        return "list";
    }

    @GetMapping("/addCustomer")
    public String showForm(Model model) {

        model.addAttribute("customer", new Customer());

        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String processNewCustomerForm(@Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "addCustomer";
        }
        customerRepo.save(customer);
        return "redirect:/list";
    }

    @GetMapping("/updateCustomer")
    public String showUpdateForm(@RequestParam("id") int id, Model model) {

        Customer customer = customerRepo.findById(id).get();

        model.addAttribute("customer", customer);

        return "updateCustomer";
    }

    @PostMapping("/updateCustomer")
    public String processUpdateForm(@Valid @ModelAttribute("customer") Customer customer, Errors errors) {
        if (errors.hasErrors()) {

            return "updateCustomer";
        }

        customerRepo.save(customer);

        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id) {

        customerRepo.deleteById(id);

        return "redirect:/list";

    }

}
