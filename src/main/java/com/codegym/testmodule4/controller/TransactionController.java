package com.codegym.testmodule4.controller;

import com.codegym.testmodule4.model.Customer;
import com.codegym.testmodule4.model.Transaction;
import com.codegym.testmodule4.service.CustomerService;
import com.codegym.testmodule4.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final CustomerService customerService;
    public TransactionController(TransactionService transactionService, CustomerService customerService){
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public ModelAndView showTransList(@RequestParam(value = "customerName", required = false) String customerName) {
        ModelAndView modelAndView = new ModelAndView("transaction/list");
        Iterable<Transaction> transactions;

        if (customerName != null && !customerName.isEmpty()) {
            transactions = transactionService.findByCustomerName(customerName);
            modelAndView.addObject("customerName", customerName); // Hiển thị lại giá trị tìm kiếm
        } else {
            transactions = transactionService.findAll();
        }
        if (!transactions.iterator().hasNext()) {
            modelAndView.addObject("errorMessage", "Không tìm thấy giao dịch nào.");
        }
        modelAndView.addObject("transactions", transactions);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showFormAdd(){
        ModelAndView modelAndView = new ModelAndView("transaction/add");
        Iterable<Customer> list = customerService.findAll();
        modelAndView.addObject("list", list);
        modelAndView.addObject("transaction", new Transaction());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addTransaction(@Validated @ModelAttribute Transaction transaction, BindingResult bindingResult, @RequestParam Long idCustomer, Model model){
        Iterable<Customer> list = customerService.findAll();
        if (bindingResult.hasErrors()){
            model.addAttribute("list", list);
            return "transaction/add";
        }
        model.addAttribute("list", list);
        Optional<Customer> customerOptional = customerService.findById(idCustomer);
        Customer customer = customerOptional.get();
        transaction.setCustomer(customer);
        transactionService.save(transaction);
        return "redirect:/transaction/list";
    }

    @GetMapping("/view")
    public ModelAndView viewTransaction(@RequestParam Long id){
        Optional<Transaction> transaction = transactionService.findById(id);
        ModelAndView modelAndView = new ModelAndView("transaction/view");
        modelAndView.addObject("transaction", transaction.get());
        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteTransaction(@RequestParam Long id){
        transactionService.delete(id);
        return "redirect:/transaction/list";
    }
}
