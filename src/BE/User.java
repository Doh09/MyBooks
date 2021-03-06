/**
 * User. Class that represents a user. The class holds the users information and
 * a list of the books the user has borrowed.
 *
 */
package BE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex, Dennis & Niels
 */
public class User implements Serializable
{

    /**
     * Variables
     */
    private final int id; //generated by system
    private boolean admin; //checkbox in new/edit user window
    private String name;
    private String address;
    private String email;
    private String password;
    private ArrayList<Book> booksBorrowed;
    private double unpaidFines;
    private double paidFines;

    /**
     * Constructor for the User class.
     *
     * @param id
     * @param admin
     * @param name
     * @param address
     * @param email
     * @param password
     */
    public User(int id, boolean admin, String name, String address, String email, String password)
    {
        //initialize variables
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        booksBorrowed = new ArrayList<>();
        unpaidFines = 0;
        paidFines = 0;
    }

    /**
     * @return the value of the int "id"
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the value of the boolean "admin"
     */
    public boolean isAdmin()
    {
        return admin;
    }

    /**
     * @return the value of the String "name"
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the value of the String "address"
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @return the value of the String "email"
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return the value of the String "password"
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @return the List<Book> "booksBorrowed"
     */
    public List<Book> getBooksBorrowed()
    {
        return booksBorrowed;
    }

    /**
     * Sets the admin variable to the parameter value given.
     * @param admin
     */
    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    /**
     * Sets the name variable to the parameter value given.
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the address variable to the parameter value given.
     * @param address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Sets the email variable to the parameter value given.
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Sets the password variable to the parameter value given.
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Adds a book to the booksBorrowed list
     * @param book
     */
    public void AddBooksBorrowed(Book book)
    {
        booksBorrowed.add(book);
    }

    /**
     * @return the variable unpaidFines
     */
    public double getUnpaiedFines() {
        return unpaidFines;
    }

    /**
     * @param unpaidFines the unpaidFines to set
     */
    public void setUnpaiedFines(double unpaidFines) {
        this.unpaidFines = unpaidFines;
    }
    
    /**
     * Adds unpaidFines to userFines
     * @param userFines
     */
    public void addFines(double userFines){
        this.unpaidFines+= userFines;
    }

    /**
     * @return the paidFines
     */
    public double getPaidFines() {
        return paidFines;
    }

    /**
     * @param paidFines the paidFines to set
     */
    public void setPaidFines(double paidFines) {
        this.paidFines = paidFines;
    }
}
