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
    private String password;

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


    public boolean addCustomer(String name, String driverLicenceNo, String phoneNo, String streetName, int zipCode, String password)
    {
        // WILL TRY TO ADD THE INFORMATION TO THE DATABASE, BUT ONLY CREATE A CUSTOMER INSTANCE IF 1 ROW IS AFFECTED
        // THIS MEANS THAT THE INFORMATION WAS REGISTERED SUCCESSFULLY WITHOUT ANY ERRORS

        if (DBFunction.insertCustomer(name,driverLicenceNo,phoneNo,streetName,zipCode,password)==1){


            assignInfoToCustomerObject(name,driverLicenceNo,phoneNo,streetName,zipCode,0,password);

            return true;

        } else {

            System.out.println("Customer Class: No records were created. You either have a record with that phone number or driver licence already");

            return false;
        }

    }

    /**
     * Used for both creating new customers, but also retrieving information about an existing customer
     * @param name name of the customer
     * @param driverLicenceNo driver licence number of the person responsible
     * @param phoneNo phone number which is the main key
     * @param streetName address
     * @param zipCode zip code of the customer
     * @param loyaltyGrade loyalty grade of customer. If it's a new customer, this one is usually set to 0
     * @param password the password he or she will use to login again
     */
    public void assignInfoToCustomerObject(String name, String driverLicenceNo, String phoneNo, String streetName, int zipCode, int loyaltyGrade, String password){

        this.name=name;
        this.driverLicenceNo=driverLicenceNo;
        this.phoneNo=phoneNo;
        this.streetName=streetName;
        this.zipCode=zipCode;
        this.loyaltyGrade=loyaltyGrade;
        this.password=password;

    }


    public String getName() {
        return name;
    }


}
