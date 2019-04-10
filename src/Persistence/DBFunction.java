package Persistence;

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






    public static int select(String statement){


        // TODO MAKE SELECTION POSSIBLE



        return 0; // TODO RETURN AMOUNT OF RECORDS FOUND - CAN BE USEFUL FOR CHECKING STUFF BEFORE TAKING ACTIONS


    }








}
