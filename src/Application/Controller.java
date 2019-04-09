package Application;

import Domain.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * Created by Lukas
 * 09-04-2019.
 */
public class Controller {


    @FXML
    private TableView tableView;

    @FXML
    private Button makeReservationButton;


    @FXML
    public void initialize(){
        tableView.setVisible(true);


    }







    @FXML
    public void handleAddCustomer()
    {
        Customer currentCustomer = new Customer();

        if (currentCustomer.addCustomer("","","","",0))
        {
            //Create password
        }
        else
        {
            //Inform customer that his/hers information already exist in the database
            //Change scene so customer can input password
        }
    }








}
