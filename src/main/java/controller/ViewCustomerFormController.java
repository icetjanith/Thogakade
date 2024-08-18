package controller;

import com.jfoenix.controls.JFXTextField;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {

    @FXML
    private JFXTextField address;

    @FXML
    private TableColumn<?, ?> cellAddress;

    @FXML
    private TableColumn<?, ?> cellBirthday;

    @FXML
    private TableColumn<?, ?> cellId;

    @FXML
    private TableColumn<?, ?> cellName;

    @FXML
    private TableColumn<?, ?> cellNumber;

    @FXML
    private TableColumn<?, ?> cellTitle;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private DatePicker dob;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField number;

    @FXML
    private ComboBox<String> title;

    void loadTable(){

        cellId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cellTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        cellName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cellAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        cellNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        cellBirthday.setCellValueFactory(new PropertyValueFactory<>("dob"));

        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        DBConnection.getInstance().getCustomerList().forEach(customer -> {
            customerList.add(customer);
        });

        customerTable.setItems(customerList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> titleList = FXCollections.observableArrayList();
        titleList.add("Mr.");
        titleList.add("Mrs.");
        titleList.add("Miss.");
        title.setItems(titleList);

        loadTable();

        customerTable.getSelectionModel().selectedItemProperty().addListener(((observableValue,oldValue,newValue )-> {
            if(newValue!=null){
                System.out.println(newValue);
                id.setText(newValue.getId());
                id.setEditable(false);
                name.setText(newValue.getName());
                address.setText(newValue.getAddress());
                number.setText(newValue.getNumber());
                dob.setValue(newValue.getDob());
            }
        }));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        System.out.println("delete");
        List<Customer> customerList = DBConnection.getInstance().getCustomerList();
        for(Customer customer:customerList ){
            if (customer.getId().equals(id.getText())){
                customerList.remove(customer);
                break;
            }
        }
        loadTable();
        customerTable.refresh();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        System.out.println("update");
        List<Customer> customerList = DBConnection.getInstance().getCustomerList();
        for(Customer customer:customerList ){
            if (customer.getId().equals(id.getText())){
                customer.setName(name.getText());
                customer.setTitle(title.getValue());
                customer.setAddress(address.getText());
                customer.setNumber(number.getText());
                customer.setDob(dob.getValue());
                break;
            }
        }
        loadTable();
        customerTable.refresh();
    }


}
