package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    public JFXTextField id;
    public JFXTextField name;
    public  ComboBox <String> title;
    public JFXTextField address;
    public JFXTextField number;
    public DatePicker dob;
    public Label errorMsg;
    private boolean isExists;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("Mr.");
        titleList.add("Mrs.");
        titleList.add("Miss.");
        title.setItems(titleList);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        isExists=false;
        List<Customer> customerList = DBConnection.getInstance().getCustomerList();
        for(Customer customer:customerList){
            if(customer.getId().equals(id.getText())){
                isExists=true;
            }
        }

        if(isExists){
            id.setText("");
            errorMsg.setText("Customer id already exists...");
        }else{
            errorMsg.setText("");
            customerList.add(new Customer(id.getText(),name.getText(),title.getValue(),address.getText(),number.getText(),dob.getValue()));

        }

        customerList.forEach(customer -> {
            System.out.println(customer);
        });

    }


}
