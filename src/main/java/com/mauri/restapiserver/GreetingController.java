package com.mauri.restapiserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @PostMapping("/expenses")
    public Expenses newExpenses(@RequestBody Expenses newExpenses) {
        System.out.println("Expenses.getType: "+newExpenses.getType());
        System.out.println("Expenses.getId: "+newExpenses.getId());
        System.out.println("Expenses.getDescription: "+newExpenses.getDescription());
        System.out.println("Expenses.getAmount: "+newExpenses.getAmount());
        return expensesRespository.save(newExpenses);
    }

    @PutMapping("/expenses/{id}")
    public Expenses replaceExpenses(@RequestBody Expenses newExpenses, @PathVariable Integer id) {
        return expensesRespository.findById(id)
                .map(expenses -> {
                    expenses.setDescription(newExpenses.getDescription());
                    expenses.setAmount(newExpenses.getAmount());
                    expenses.setDate(newExpenses.getDate());
                    expenses.setType(newExpenses.getType());
                    return expensesRespository.save(expenses);
                })
                .orElseGet(() -> {
                    //newExpenses.setId(id);
                    return expensesRespository.save(newExpenses);
                });
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
