package com.mauri.restapiserver;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
//https://thoughts-on-java.org/hibernate-postgresql-5-things-need-know/
@Entity
@Table(name="expenses", schema="public")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_name_id_seq")
    @SequenceGenerator(name="table_name_id_seq", sequenceName = "table_name_id_seq", allocationSize=1)
    @Column(name="id",insertable = false,updatable = false, nullable = false)
    private int id;
    @Column(name="description")
    private String description;
    @Column(name="type")
    private String type;
    @Column(name="date")
    private LocalDate date;
    @Column(name="amount")
    private BigDecimal amount;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /*
    public void setId(int id) {
        //this.id = id;
    }
    */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
