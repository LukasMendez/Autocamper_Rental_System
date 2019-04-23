package Domain;

import Foundation.DB;

import static org.junit.Assert.*;

/**
 * Created by Lukas
 * 23-04-2019.
 */
public class CustomerTest {

    private Customer instance;
    private DB db;

    @org.junit.Before
    public void setUp() throws Exception {

        DB.establishConnection();
        instance = new Customer();

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void checkPhoneNo() {

        boolean expected = true;
        boolean actual = instance.checkPhoneNo("+4528306309");

        assertTrue(expected == actual);

    }

    @org.junit.Test
    public void addCustomer() {

        boolean expected = false;
        boolean actual = instance.addCustomer("Jens Hansen","48378943289483","+4528306309","Kolortvej 45",6400,"landmandsøgerkærlighed");

        assertTrue(expected == actual);

    }

    @org.junit.Test
    public void getName() {

        String expected = null;
        String actual = instance.getName();

        assertEquals(expected,actual);

    }
}