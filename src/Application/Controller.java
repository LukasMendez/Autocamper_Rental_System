package Application;

import Domain.Customer;
import Foundation.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by Lukas
 * 09-04-2019.
 */
public class Controller {


    @FXML
    private TableView tableView;

    // BUTTONS
    @FXML
    private Button makeReservationButton;
    @FXML
    private Button confirmCustomerInfo;
    @FXML
    private Button checkPhoneNoButton;

    // TEXTFIELD
    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField licenceTextfield;
    @FXML
    private TextField phoneNoTextfield;
    @FXML
    private TextField streetTextfield;
    @FXML
    private TextField zipTextfield;
    @FXML
    private TextField genderTextfield;

    // HBOX
    @FXML
    private HBox topHBox;
    @FXML
    private HBox middleHBox;
    @FXML
    private HBox bottomHBox;
    @FXML
    private HBox checkButtonHBox;



    @FXML
    public void initialize(){
        tableView.setVisible(false);
        DB.establishConnection();


    }


    @FXML
    public void handleMakeReservation()
    {
        topHBox.setVisible(true);
        checkButtonHBox.setVisible(true);
        checkPhoneNoButton.setVisible(true);
    }

    @FXML
    public void handleCustomerInfo()
    {
        Customer currentCustomer = new Customer();

        if (nameTextfield.getText() != null && licenceTextfield.getText() != null && phoneNoTextfield.getText() != null && streetTextfield.getText() != null && zipTextfield.getText() != null)
        {
            currentCustomer.addCustomer(nameTextfield.getText(),licenceTextfield.getText(),phoneNoTextfield.getText(),streetTextfield.getText(),Integer.parseInt(zipTextfield.getText()));
        }
        else
        {
            //TODO Print that information is missing
        }
    }

    @FXML
    public void handleCheckPhoneNo()
    {
        Customer currentCustomer = new Customer();

        if (currentCustomer.checkPhoneNo(phoneNoTextfield.getText()))
        {
            //Inform customer that his/hers information already exist in the database
            //TODO Change scene so customer can input password
        }
        else
        {
            checkButtonHBox.setVisible(false);
            middleHBox.setVisible(true);
            bottomHBox.setVisible(true);
            nameTextfield.setVisible(true);
            licenceTextfield.setVisible(true);
        }
    }





}
