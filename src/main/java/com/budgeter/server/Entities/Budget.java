package com.budgeter.server.Entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="budgets")
public class Budget implements Serializable {

    private @Id @GeneratedValue Long id;
    private String name;
    private double total;
    @ElementCollection private Map<String, Double> categories;
    @JoinColumn(name="budget_id") @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Transaction> transactions;

    public Budget() {}
    @JsonCreator
    public Budget(Long id, String name, double total, Map<String, Double> categories, List<Transaction> transactions){
        this.id = id;
        this.name = name;
        this.total = total;
        this.categories = categories;
        this.transactions = transactions;
    }

    public Transaction addTransaction(Transaction t){
        List<Transaction> newTrans = this.transactions;
        newTrans.add(t);
        this.transactions = newTrans;
        return t;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategories(Map<String, Double> categories) {
        this.categories = categories;
    }

    public Map<String, Double> getCategories() {
        return this.categories;
    }

    public double getTotal(){
        return this.total;
    }

    public void setTotal(double total){
        this.total = total;
    }

    @Override
    public String toString(){
        String toRet = this.name + " budget: Total amount = "+this.total + "\n";
//        for(Category cat: this.categories){
//            toRet += cat.toString() + "\n";
//        }
        return toRet;
    }

}
