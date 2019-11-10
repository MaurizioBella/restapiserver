package com.mauri.restapiserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {

        // custom query to search to blog post by title or content
        //List<Expenses> findById(int Id);
        Expenses findById(int Id);

    }