package Application;

import Domain.Customer;
import Foundation.DB;
import Persistence.DBFunction;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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

    @FXML
    private Label signInLabel;


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

    // HBOX & VBOX
    @FXML
    private HBox topHBox;
    @FXML
    private HBox middleHBox;
    @FXML
    private HBox bottomHBox;
    @FXML
    private HBox checkButtonHBox;




    // When you press the "Confirm" button. The output will depend on this boolean
    private boolean loginMode = false;

    @FXML
    public void initialize(){
        tableView.setVisible(false);
        DB.establishConnection();
        handleMakeReservation();


    }


    @FXML
    public void handleMakeReservation()
    {

        signInLabel.setText("Not signed in");
        loginMode=false;

        passwordField.setVisible(false);
        confirmCustomerInfo.setVisible(false);
        infoLabel.setVisible(false);
        topHBox.setVisible(true);
        checkButtonHBox.setVisible(true);
        checkPhoneNoButton.setVisible(true);
        phoneNoTextfield.setVisible(true);


        headLabel.setText("Please enter your phone number");

    }


    /**
     * Attached to the "Check button" and has two functions
     *
     * If loginMode is false:
     *
     * This method will pass the user information to a stored procedure and return true if it was entered successfully.
     * The criteria for a successful record insertion is to have a unique driver licence, but also phone number as the
     * phone number will be used for logging into the system. Therefore we can't have more than one record with the same phone number.
     *
     * The method also lets the user know if anything went wrong or if the data was saved successfully.
     *
     * If loginMode is true:
     *
     * Will //TODO WRITE WHAT IT ACTUALLY DOES
     *
     */


    @FXML
    public void handleCustomerInfo()
    {
        Customer currentCustomer = new Customer();


        if (!loginMode){

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

                    loginMode=true;
                    loginScreen();

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

            // If loginMode is ON
        } else {


            if (!phoneNoTextfield.equals("") && !passwordField.equals("")){


                currentCustomer = DBFunction.getExistingCustomer(phoneNoTextfield.getText(),passwordField.getText());

                if (currentCustomer!=null){

                    infoLabel.setTextFill(Color.GREEN);
                    infoLabel.setText("PASSWORD WAS CORRECT! DEBUGGING");

                    signInLabel.setText("Welcome, " + currentCustomer.getName());

                    // TODO THE CUSTOMER IS NOW LOGGED IN AND WILL BE ABLE TO CHOOSE AN AUTOCAMPER

                } else {

                    infoLabel.setText("Your password or phone number is incorrect. Please check again!");
                    infoLabel.setTextFill(Color.RED);
                    infoLabel.setVisible(true);

                }






            } else {

                infoLabel.setText("Please fill all the fields");
                infoLabel.setTextFill(Color.RED);
                infoLabel.setVisible(true);

            }





        }



    }

    /**
     *
     * Will present the user with a login screen. Mostly used to avoid duplicate code
     *
     */

    @FXML
    private void loginScreen(){

        signInLabel.setText("Not signed in");

        checkButtonHBox.setVisible(false);
        middleHBox.setVisible(true);
        bottomHBox.setVisible(true);

        // Will make sure that only phone number text field is displayed
        nameTextfield.setVisible(false);
        streetTextfield.setVisible(false);
        phoneNoTextfield.setVisible(true);
        passwordField.setVisible(true);
        confirmCustomerInfo.setVisible(true);
        licenceTextfield.setVisible(false);

        // Will make sure that zip code text field and street text field are removed, so that the password field is
        // the only visible node in the middleHBox and also aligned perfectly with the phone number text field
        middleHBox.getChildren().removeAll(zipTextfield, streetTextfield);

        infoLabel.setVisible(true);
        infoLabel.setTextFill(Color.BLACK);
        infoLabel.setText("Please login with your existing account");

        // Will change the button text
        confirmCustomerInfo.setText("Log in");

    }


    /**
     *
     * Will check the database to see if the customer is registered and then present him with a login screen.
     * If not a registration page will appear
     *
     */

    @FXML
    public void handleCheckPhoneNo()
    {
        Customer currentCustomer = new Customer();

        if (currentCustomer.checkPhoneNo(phoneNoTextfield.getText()))
        {

            System.out.println("This phone number already exist in the database");

            loginMode=true;

            loginScreen();

        }

        // If the user isn't registered
        else
        {

            loginMode=false;
            rebuildRegistrationPage();

        }
    }

    /**
     *
     * Used for rebuilding registration page. Mostly used for avoiding duplicate code
     *
     */

    private void rebuildRegistrationPage(){

        signInLabel.setText("Not signed in");

        checkButtonHBox.setVisible(false);
        middleHBox.setVisible(true);
        bottomHBox.setVisible(true);

        nameTextfield.setVisible(true);
        licenceTextfield.setVisible(true);

        // Will make sure the nodes back in its old order
        middleHBox.getChildren().removeAll(streetTextfield,zipTextfield,passwordField);

        middleHBox.getChildren().addAll(streetTextfield,zipTextfield,passwordField);

        streetTextfield.setVisible(true);
        zipTextfield.setVisible(true);
        passwordField.setVisible(true);

        // Clear password field if used previously
        nameTextfield.setText("");
       // phoneNoTextfield.setText("");
        licenceTextfield.setText("");
        streetTextfield.setText("");
        passwordField.setText("");
        zipTextfield.setText("");

        confirmCustomerInfo.setVisible(true);

        //Will change the login text back to confirm
        confirmCustomerInfo.setText("Confirm");

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
