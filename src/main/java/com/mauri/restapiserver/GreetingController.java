package com.mauri.restapiserver;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @Autowired
    ExpensesRepository expensesRespository;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @GetMapping("/expenses")
    public List<Expenses> index(){
        return expensesRespository.findAll();
    }

    @GetMapping("/expenses/{id}")
    public Expenses show(@PathVariable String id){
        int expenseId = Integer.parseInt(id);
        System.out.println("expensesId: "+expenseId);
        return expensesRespository.findById(expenseId);
    }
}
