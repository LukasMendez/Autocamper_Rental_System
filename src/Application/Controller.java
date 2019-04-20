package Application;

import Domain.Autocamper.Autocamper;
import Domain.Autocamper.BasicCamper;
import Domain.Autocamper.LuxuryCamper;
import Domain.Autocamper.StandardCamper;
import Domain.Customer;
import Foundation.DB;
import Persistence.DBFunction;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Lukas
 * 09-04-2019.
 */
public class Controller {


    @FXML
    private TableView<Autocamper> tableView;

    @FXML
    private TableColumn autocamperCol;

    @FXML
    private TableColumn priceCol;

    @FXML
    private TableColumn modelCol;

    @FXML
    private TableColumn heatingCol;

    @FXML
    private TableColumn noOfBedsCol;

    @FXML
    private TableColumn sizeCol;

    @FXML
    private TableColumn descriptionCol;


    // LABELS
    @FXML
    private Label headLabel;

    @FXML
    private Label infoLabel;

    @FXML
    private Label weekfromLabel;

    @FXML
    private Label weektoLabel;


    //ChoiceBoxes
    @FXML
    private ChoiceBox<String> weekfromChoice;

    @FXML
    private ChoiceBox weektoChoice;


    // BUTTONS
    @FXML
    private Button makeReservationButton;
    @FXML
    private Button confirmCustomerInfo;
    @FXML
    private Button checkPhoneNoButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button searchWeeksButton;
    @FXML
    private Button confirmAutoChoiceButton;
    @FXML
    private Button bigMakeReservationButton;

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
    private PasswordField passwordField;

    @FXML
    private ImageView frontImage;

    //VBOX
    @FXML
    private VBox mainVbox;


    // When you press the "Confirm" button. The output will depend on this boolean
    private boolean loginMode = false;

    @FXML
    public void initialize()
    {
        DB.establishConnection();
        handleHome();
    }


    @FXML
    public void handleMakeReservation() {

        loginMode = false;

        clearSceen();

        headLabel.setText("Please enter your phone number");
        checkPhoneNoButton.setVisible(true);
        phoneNoTextfield.setVisible(true);
    }


    /**
     * Attached to the "Check button" and has two functions
     * <p>
     * If loginMode is false:
     * <p>
     * This method will pass the user information to a stored procedure and return true if it was entered successfully.
     * The criteria for a successful record insertion is to have a unique driver licence, but also phone number as the
     * phone number will be used for logging into the system. Therefore we can't have more than one record with the same phone number.
     * <p>
     * The method also lets the user know if anything went wrong or if the data was saved successfully.
     * <p>
     * If loginMode is true:
     * <p>
     * Will //TODO WRITE WHAT IT ACTUALLY DOES
     */


    @FXML
    public void handleCustomerInfo() throws SQLException {
        Customer currentCustomer = new Customer();

        if (!loginMode) {

            // Will check if all fields are filled
            if (!nameTextfield.getText().equals("") && !licenceTextfield.getText().equals("") && !phoneNoTextfield.getText().equals("") && !streetTextfield.getText().equals("") && !zipTextfield.getText().equals("") && !passwordField.getText().equals("")) {

                boolean addedCustomerSuccess = currentCustomer.addCustomer(nameTextfield.getText(), licenceTextfield.getText(), phoneNoTextfield.getText(), streetTextfield.getText(), Integer.parseInt(zipTextfield.getText()), passwordField.getText());

                // If none of the given information was rejected it will save and inform the customer that everything went well
                if (addedCustomerSuccess) {

                    System.out.println("Saved data successfully");

                    infoLabel.setText("The info was saved successfully");
                    infoLabel.setTextFill(Color.BLACK);
                    infoLabel.setVisible(true);

                    loginMode = true;
                    loginScreen();

                } else {

                    System.out.println("Controller Class: There was something wrong with the data. Please check for errors");

                    infoLabel.setText("You have entered incorrect information. Please try again!");
                    infoLabel.setTextFill(Color.RED);
                    infoLabel.setVisible(true);
                }

            } else {

                infoLabel.setText("You need to fill all the fields to continue!");
                infoLabel.setTextFill(Color.RED);
                infoLabel.setVisible(true);

            }

            // If loginMode is ON
        } else {


            if (!phoneNoTextfield.equals("") && !passwordField.equals("")) {

                currentCustomer = DBFunction.getExistingCustomer(phoneNoTextfield.getText(), passwordField.getText());

                if (currentCustomer != null) {

                    infoLabel.setTextFill(Color.GREEN);
                    infoLabel.setText("PASSWORD WAS CORRECT");

                    setReservationScreen();

                } else {

                    infoLabel.setText("Your password or phone number is incorrect. Please try again!");
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
     * Sets the reservation "scene"
     * @throws SQLException
     */
    private void setReservationScreen() throws SQLException
    {
        //Sets all the autocampers in the tableview
        setTableView(DBFunction.getAllAutocampers());

        clearSceen();

        //Declaring the choices in the choiceboxes
        weekfromChoice.setItems(FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20"));
        weektoChoice.setItems(FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20"));

        headLabel.setText("Reservation");
        tableView.setVisible(true);
        weekfromLabel.setVisible(true);
        weektoLabel.setVisible(true);
        weekfromChoice.setVisible(true);
        weektoChoice.setVisible(true);
        searchWeeksButton.setVisible(true);

    }


    /**
     * Changes the tableview from showing all autocampers to only showing all the avaliable autocamper in the selected week(s)
     * @throws SQLException
     */
    @FXML
    private void handleSearch() throws SQLException
    {
        setTableView(DBFunction.getAvaliableCampers(DBFunction.getAllAutocampers(), weekfromChoice.getValue(), (String) weektoChoice.getValue()));
        confirmAutoChoiceButton.setVisible(true);
    }

    /**
     * Gets the selected autocamper
     */
    @FXML
    private void handleChoice()
    {
        Autocamper selectedAutocamper = tableView.getSelectionModel().getSelectedItem();

        //TODO Maybe change the scene and register the reservation
    }


    /**
     * Will present the user with a login screen. Mostly used to avoid duplicate code
     */
    @FXML
    private void loginScreen() {

        clearSceen();

        // Will make sure that only phone number text field is displayed
        phoneNoTextfield.setVisible(true);
        passwordField.setVisible(true);
        confirmCustomerInfo.setVisible(true);
        licenceTextfield.setVisible(false);

        // Will make sure that zip code text field and street text field are removed, so that the password field is
        // the only visible node in the middleHBox and also aligned perfectly with the phone number text field

        infoLabel.setVisible(true);
        infoLabel.setTextFill(Color.BLACK);
        infoLabel.setText("Please login with your existing account");

        // Will change the button text
        confirmCustomerInfo.setText("Log in");

    }


    /**
     * Will check the database to see if the customer is registered and then present him with a login screen.
     * If not a registration page will appear
     */

    @FXML
    public void handleCheckPhoneNo() {

        Customer currentCustomer = new Customer();

        if (currentCustomer.checkPhoneNo(phoneNoTextfield.getText())) {

            System.out.println("This phone number already exist in the database");

            loginMode = true;

            loginScreen();

        }

        // If the user isn't registered
        else {

            loginMode = false;
            rebuildRegistrationPage();
        }
    }

    /**
     * Used for rebuilding registration page. Mostly used for avoiding duplicate code
     */

    private void rebuildRegistrationPage() {

        clearSceen();

        phoneNoTextfield.setVisible(true);
        nameTextfield.setVisible(true);
        licenceTextfield.setVisible(true);

        // Will make sure the nodes back in its old order

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


    /**
     * The method gets an arraylist of all the autocampers, that need to be displayed in the tableview and displays them
     * @param avaliableAutocampers
     */
    public void setTableView(ArrayList<Autocamper> avaliableAutocampers) {

        tableView.setVisible(true);
        autocamperCol.setVisible(true);

        final ObservableList<Autocamper> data = FXCollections.observableArrayList();

        for (int i = 0; i < avaliableAutocampers.size(); i++)
        {
            if (avaliableAutocampers.get(i) instanceof BasicCamper)
            {
                data.addAll(new BasicCamper(avaliableAutocampers.get(i).getVINNumber(), (avaliableAutocampers.get(i)).getModelYear(), (avaliableAutocampers.get(i)).getHeatingSystem(), (avaliableAutocampers.get(i)).getSize(), (avaliableAutocampers.get(i)).getDescription(), (avaliableAutocampers.get(i)).getNumberOfBeds(),"Basic",((BasicCamper) avaliableAutocampers.get(i)).getPrice()));
            }
            else if (avaliableAutocampers.get(i) instanceof LuxuryCamper)
            {
                data.addAll(new LuxuryCamper(avaliableAutocampers.get(i).getVINNumber(), ((LuxuryCamper) avaliableAutocampers.get(i)).getModelYear(), ((LuxuryCamper) avaliableAutocampers.get(i)).getHeatingSystem(), ((LuxuryCamper) avaliableAutocampers.get(i)).getSize(), ((LuxuryCamper) avaliableAutocampers.get(i)).getDescription(), ((LuxuryCamper) avaliableAutocampers.get(i)).getNumberOfBeds(),"Luxury",((LuxuryCamper) avaliableAutocampers.get(i)).getPrice()));
            }
            else
                {
                data.addAll(new StandardCamper(avaliableAutocampers.get(i).getVINNumber(), ((StandardCamper) avaliableAutocampers.get(i)).getModelYear(), ((StandardCamper) avaliableAutocampers.get(i)).getHeatingSystem(), ((StandardCamper) avaliableAutocampers.get(i)).getSize(), ((StandardCamper) avaliableAutocampers.get(i)).getDescription(), ((StandardCamper) avaliableAutocampers.get(i)).getNumberOfBeds(),"Standard", ((StandardCamper) avaliableAutocampers.get(i)).getPrice()));
            }
        }

        autocamperCol.setCellValueFactory(new PropertyValueFactory<Autocamper, String>("type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Autocamper, String>("price"));
        modelCol.setCellValueFactory(new PropertyValueFactory<Autocamper, Integer>("ModelYear"));
        heatingCol.setCellValueFactory(new PropertyValueFactory<Autocamper, String>("HeatingSystem"));
        noOfBedsCol.setCellValueFactory(new PropertyValueFactory<Autocamper, Integer>("numberOfBeds"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<Autocamper, String>("Size"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Autocamper, String>("description"));

        tableView.setItems(data);
    }

    @FXML
    public void handleHome()
    {
        clearSceen();

        headLabel.setText("Welcome to Wagners Auto Camper Rental Service");
        frontImage.setVisible(true);
        bigMakeReservationButton.setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    public void clearSceen()
    {
        tableView.setVisible(false);

        infoLabel.setVisible(false);
        weekfromLabel.setVisible(false);
        weektoLabel.setVisible(false);
        weektoChoice.setVisible(false);
        weekfromChoice.setVisible(false);

        confirmAutoChoiceButton.setVisible(false);
        confirmCustomerInfo.setVisible(false);
        checkPhoneNoButton.setVisible(false);
        searchWeeksButton.setVisible(false);
        bigMakeReservationButton.setVisible(false);

        nameTextfield.setVisible(false);
        licenceTextfield.setVisible(false);
        phoneNoTextfield.setVisible(false);
        streetTextfield.setVisible(false);
        zipTextfield.setVisible(false);
        passwordField.setVisible(false);

        frontImage.setVisible(false);
    }

}
