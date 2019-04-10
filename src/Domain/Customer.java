package Domain;

import Persistence.DBFunction;

/**
 * Created by Lukas
 * 08-04-2019.
 */
public class Customer {

    private String name;
    private String driverLicenceNo;
    private String phoneNo;
    private String streetName;
    private int zipCode;

    // GRADE: 1, 2 or 3. The higher the better
    private int loyaltyGrade = 0; // Initial value for new customers


    /**
     * Will return true if there's already a customer with that phone number in the database, and false if not.
     * This method uses a helper method from the DBFunction to work the database.
     * @param phoneNo the phone number that is given (or being checked)
     * @return true if there's a mathc, false if not.
     */


    public boolean checkPhoneNo(String phoneNo)
    {
        if (DBFunction.checkPhoneNumber(phoneNo)>=1)
        {
            System.out.println("There was found a match with that phone number");
            return true;
        }
        else
        {
            System.out.println("No records were found with that phone number");
            return false;
        }
    }


    public void addCustomer(String name, String driverLicenceNo, String phoneNo, String streetName, int zipCode)
    {

        this.name=name;
        this.driverLicenceNo=driverLicenceNo;
        this.phoneNo=phoneNo;
        this.streetName=streetName;
        this.zipCode=zipCode;

        // TODO ADD THE INFORMATION TO THE DATABASE AS WELL

    }

    //TODO implement getter and setters for all instance variables



}
