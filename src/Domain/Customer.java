package Domain;

import Persistence.DB;

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


    public Customer(){

    }



    public boolean addCustomer(String name, String driverLicenceNo, String phoneNo, String streetName, int zipCode){


        // IF ZERO RECORDS ARE FOUND WITH THE GIVEN DRIVER LICENCE NUMBER, WE ASSUME THAT THE CUSTOMER IS NOT REGISTERED
        if (DB.select("select * from tblCustomer where fldDriverLicenceNo'"+driverLicenceNo+"'")==0){

            this.name=name;
            this.driverLicenceNo=driverLicenceNo;
            this.phoneNo=phoneNo;
            this.streetName=streetName;
            this.zipCode=zipCode;


            // TODO ADD THE INFORMATION TO THE DATABASE AS WELL

            return true;


        } else {


            // TODO DISPLAY AND TELL THE USER THAT A USER ACCOUNT WITH THE GIVEN LICENCE NUMBER ALREADY EXIST

            // TODO PERHAPS USE THE EXISTING CUSTOMER INFO, BUT AVOID ADDING IT TO THE DATABASE AGAIN

            return false;

        }


    }






}
