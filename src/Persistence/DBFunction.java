package Persistence;

import Domain.Customer;
import Foundation.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Lukas
 * 10-04-2019.
 */
public class DBFunction {

    // We are using static to make sure, that we are using the same connection all the time. And that we don't create
    // a new one for each instance.
    static Connection con = DB.getCon();






    /**
     * Checks the phone number in the database
     * @param phoneNo the given phone number
     * @return 0 or 1
     */

    public static int checkPhoneNumber(String phoneNo){

        int results = 0;

        try{

            PreparedStatement ps = con.prepareCall("SELECT dbo.checkPhoneNumber(?) ");

            ps.setString(1,phoneNo);

            ResultSet rs = ps.executeQuery();

            // Will count the amount of results found. If the system is bug-free, it should only be 0 or 1
            while(rs.next()){
                results = rs.getInt(1);
            }

            System.out.println("The result of the search was: " + results);

            // con.close();


        } catch (Exception e){

            System.err.println(e.getMessage());

        }

        // Will return the amount of records found with the given phone number
        return results;

    }


    /**
     *
     * This method uses a stored procedure from the DB_Autocamper database. The purpose is to simply add the information
     * into a new record. This is however used as a helper-method for the method addCustomer in the Customer class.
     * @param name name of the customer
     * @param driverLicenceNo driver licence number of the person responsible
     * @param phoneNo phone number which is the main key
     * @param streetName address
     * @param zipCode zip code of the customer
     * @param password the password he or she will use to login again
     * @return amount of rows affected
     */

    public static int insertCustomer(String name, String driverLicenceNo, String phoneNo, String streetName, int zipCode, String password){

        // Rows affected
        int returnVal = 0;

        try{
            PreparedStatement ps = con.prepareCall("EXEC dbo.addCustomer ?,?,?,?,?,?");
            ps.setString(1,name);
            ps.setString(2,driverLicenceNo);
            ps.setString(3,phoneNo);
            ps.setString(4,streetName);
            ps.setInt(5,zipCode);
            ps.setString(6,password);

            returnVal = ps.executeUpdate();

            System.out.println("Rows affected: " + returnVal);



        } catch (Exception e){

            System.err.println(e.getMessage());

        }


        return returnVal;


    }



    public static int select(String statement){





        return 0; // TODO RETURN AMOUNT OF RECORDS FOUND - CAN BE USEFUL FOR CHECKING STUFF BEFORE TAKING ACTIONS


    }


    /**
     *
     * Will retrieve an existing customer from the database, and assign all the attributes to a Customer object.
     * Will search based on phone number and password. If no results are found it will just return a Customer object that is null.
     * @param phoneNumber phone number of the customer
     * @param password his/her password
     * @return Customer object with all instance variables fulfilled
     */

    public static Customer getExistingCustomer(String phoneNumber, String password){

        Customer customer = null;

        try{


            PreparedStatement ps = con.prepareCall("SELECT * FROM dbo.getCustomerInfo(?, ?)");

            ps.setString(1,phoneNumber);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            // If anything was found it will assign the attributes to a Customer object
            if (rs.next()){

                customer = new Customer();

                String name = rs.getString(1);
                String driverLicenceNo = rs.getString(2);
                // phoneNumber already saved (columnIndex 3)
                String streetName = rs.getString(4);
                int zipCode = rs.getInt(5);
                int loyaltyGrade = rs.getInt(6);
                // password is already saved (columnIndex 7)

                // Will assign the values to the customer object and then return it at the end
                customer.assignInfoToCustomerObject(name,driverLicenceNo,phoneNumber,streetName,zipCode,loyaltyGrade,password);



            }


        } catch (Exception e){


            System.err.println(e.getMessage());
        }


        // If it's null the other method will take care of this
        return customer;

    }








}
