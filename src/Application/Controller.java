package Application;

import Domain.Customer;
import Foundation.DB;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Created by Lukas
 * 09-04-2019.
 */
public class Controller {


    @FXML
    private TableView tableView;

    // LABELS
    @FXML
    private Label headLabel;

    @FXML
    private Label infoLabel;


    // BUTTONS
    @FXML
    private Button makeReservationButton;
    @FXML
    private Button confirmCustomerInfo;
    @FXML
    private Button checkPhoneNoButton;
    @FXML
    private Button homeButton;

    // TEXTFIELD OR PASSWORD FIELD
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
    private TextField passwordTextfield;

    @FXML
    private PasswordField passwordField;

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
        headLabel.setText("Please enter your phone number");
        topHBox.setVisible(true);
        checkButtonHBox.setVisible(true);
        checkPhoneNoButton.setVisible(true);
        phoneNoTextfield.setVisible(true);
    }


    /**
     *
     * This method will pass the information to a stored procedure and return true if it was entered successfully.
     * The criteria for a successful record insertion is to have a unique driver licence, but also phone number as the
     * phone number will be used for logging into the system. Therefore we can't have more than one record with the same phone number.
     *
     * The method also lets the user know if anything went wrong or if the data was saved successfully.
     *
     */


    @FXML
    public void handleCustomerInfo()
    {
        Customer currentCustomer = new Customer();

        // Will check if all fields are filled
        if (!nameTextfield.getText().equals("") && !licenceTextfield.getText().equals("") && !phoneNoTextfield.getText().equals("") && !streetTextfield.getText().equals("") && !zipTextfield.getText().equals("") && !passwordField.getText().equals(""))
        {

            boolean addedCustomerSuccess = currentCustomer.addCustomer(nameTextfield.getText(),licenceTextfield.getText(),phoneNoTextfield.getText(),streetTextfield.getText(),Integer.parseInt(zipTextfield.getText()),passwordField.getText());

            // If none of the given information was rejected it will save and inform the customer that everything went well
            if (addedCustomerSuccess){

                System.out.println("Saved data successfully");

                infoLabel.setText("The info was saved successfully");
                infoLabel.setTextFill(Color.BLACK);
                infoLabel.setVisible(true);
            } else {

                System.out.println("Controller Class: There was something wrong with the data. Please check for errors");

                infoLabel.setText("You have entered incorrect information. Please try again!");
                infoLabel.setTextFill(Color.RED);
                infoLabel.setVisible(true);


            }



        }
        else
        {

            infoLabel.setText("You need to fill all the fields to continue!");
            infoLabel.setTextFill(Color.RED);
            infoLabel.setVisible(true);

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


    @FXML
    public void handleHomeButton(){

        checkButtonHBox.setVisible(false);
        middleHBox.setVisible(false);
        bottomHBox.setVisible(false);
        nameTextfield.setVisible(false);
        licenceTextfield.setVisible(false);
        phoneNoTextfield.setVisible(false);
        headLabel.setText("Welcome to Wagners Auto Camper Rental Service");




    }





}
