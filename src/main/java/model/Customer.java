package model;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class Customer {
    private String id;
    private String name;
    private String title;
    private String address;
    private String number;
    private LocalDate dob;

    public Customer(String id, String name, String title, String address, String number, LocalDate dob) {
        this.id = id;
        this.title=title;
        this.name = name;
        this.address = address;
        this.number = number;
        this.dob = dob;
    }


}
