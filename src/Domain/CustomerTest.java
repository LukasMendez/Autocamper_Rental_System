package Domain;

import static org.junit.Assert.*;

/**
 * Created by Lukas
 * 23-04-2019.
 */
public class CustomerTest {

    private Customer instance;

    @org.junit.Before
    public void setUp() throws Exception {

        instance = new Customer();

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void checkPhoneNo() {

        boolean expected = true;
        boolean actual = instance.checkPhoneNo("+4528306309");


    }

    @org.junit.Test
    public void addCustomer() {
    }

    @org.junit.Test
    public void getName() {
    }
}