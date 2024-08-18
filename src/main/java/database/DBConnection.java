package database;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static DBConnection instance;

    private List<Customer>customerList;
    private DBConnection(){
        customerList=new ArrayList<>();
    }

    public List<Customer> getCustomerList(){
        return customerList;
    }
    public static DBConnection getInstance(){
        if(instance==null){
            return instance=new DBConnection();
        }
        return instance;
    }
}
