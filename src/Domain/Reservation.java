package Domain;

/**
 * Created by Lukas
 * 08-04-2019.
 */
public class Reservation {

    private boolean reservationSuccess;
    private Customer customer;




    public boolean enterCustomerInformation(String name, String driverLicenceNo, String phoneNo, String streetName, int zipCode){


        customer = new Customer(name,driverLicenceNo,phoneNo,streetName,zipCode);


        //TODO GRASP PATTERN: Information Expert <---- Should we move this to the Customer class?

        //TODO GRASP PATTERN: Should we argue for the Creator pattern? "B should be responsible for creating A, if they are very tightly bound" EG: Reservation cannot exist without a customer










        return true;



    }



}
