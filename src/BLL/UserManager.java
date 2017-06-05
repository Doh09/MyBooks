/*
 * UserManager.
 * Class that manages the users and admins of the program.
 * The class is a singleton class as it is a manager type that only ought to 
 * exist in 1 instance.
 */
package BLL;

import BE.User;
import DAL.UserPersistenceManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex, Dennis & Niels.
 */
public class UserManager
{

    /**
     * variables.
     */
    private static UserManager instance;
    private List<User> listOfUsers = new ArrayList<>(); //List that holds users and admins
    private UserPersistenceManager UPM;

    /**
     * Constructor for the UserManager class. Initializes the user list.
     */
    private UserManager()
    {
        //initialize variables.
        UPM = new UserPersistenceManager("userlist.dat");
        loadUserListFromHDD();
        int superadmintjek = -1;
        for (User user : listOfUsers) {
            if(user.getEmail().equals("superAdmin")){
                superadmintjek = 1;
            }
        }
        if(superadmintjek == -1){
            listOfUsers.add(createUser(true, "superAdmin", "superAdmin", "superAdmin", "superAdmin"));
            saveUserListToHDD();
            System.out.println("superAdmin added");
        }
    }

    /**
     * Singleton pattern that returns an instance/object of the class
     * UserManager.
     *
     * @return an instance of the class.
     */
    public static synchronized UserManager getInstance()
    {
        if (instance == null)
        {
            instance = new UserManager();
            return instance;
        }
        return instance;
    }

    /**
     * Adds the user given to the full user list. But only if a user with the ID
     * given is not already there.
     *
     * @param user
     * @return a String with the outcome of the attempt to add the user to the
     * list.
     */
    public String addUser(User user) //can be made into void if String isn't needed, or just do sout+tab.
    {
        //check if user is in user list.
        for (User u : listOfUsers)
        {
            //if user is in user list.
            if (u.getId() == user.getId())
            {
                return "User " + user.getId() + " already in list.";
            }
        }
        //If user is not in user list.
        listOfUsers.add(user);
        return "User successfully added to user list.";
    }

    /**
     * Removes the given user from the user list.
     *
     * @param userId
     * @return a String with the outcome of the removal.
     */
    public String deleteUser(int userId) //can be made into void if String isn't needed, or just do sout+tab.
    {
        //check if user is in user list.
        for (int i = 0; i < listOfUsers.size(); i++)
        {
            //if user is in user list.
            if (listOfUsers.get(i).getId() == userId)
            {
                listOfUsers.remove(i);
                return "User " + userId + " sucessfully deleted.";
            }
        }
        //if user is not in user list.
        return "User not found in list.";
    }

    /**
     * Replaces an existing user in the user list with the one given.
     *
     * @param user
     * @return a String with the outcome of the edit/replacing.
     */
    public String editUser(User user) //can be made into void if String isn't needed, or just do sout+tab.
    {
        //check if user is in user list.
        for (int i = 0; i < listOfUsers.size(); i++)
        {
            if (listOfUsers.get(i).getId() == user.getId())
            {
                //If user is in user list.
                listOfUsers.set(i, user);
                return "User " + user.getId() + " sucessfully edited.";
            }
        }
        //if user is not in user list.
        return "User not found in list.";
    }

    /**
     * Creates a User instance from the parameters given. The id is determined
     * by the UserManager class.
     *
     * @param admin
     * @param name
     * @param address
     * @param email
     * @param password
     * @return
     */
    public User createUser(boolean admin, String name, String address, String email, String password)
    {
        int id = 1;
        //Find the largest id in the user list and set the current id to be 1 larger than the largest one.
        for (User u : listOfUsers)
        {
            if (u.getId() >= id)
            {
                id = u.getId() + 1;
            }
        }
        //If user is not admin.
        return new User(id, admin, name, address, email, password);
    }

    /**
     * Method that saves the current list of users to the HDD through the
     * UserPersistenceManager.
     */
    public void saveUserListToHDD()
    {
        try
        {
            UPM.saveUserListToHDD(listOfUsers);
        }
        catch (IOException ex)
        {
            //Den exception herunder skal nok byttes ud med vores egen.
            throw new IllegalArgumentException("Unable to correctly save the user list to HDD.");
        }
    }

    /**
     * Method that loads in a list of users from the HDD through the
     * UserPersistenceManager.
     */
    private void loadUserListFromHDD()
    {
        try
        {
            listOfUsers = UPM.loadUserListFromHDD();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            listOfUsers = new ArrayList<>();
            System.out.println("Unable to correctly load user list,"
                    + "\n new list created.");
        }
    }

    /**
     * Returns a list of all users, after the list has been updated with the
     * file on the HDD.
     *
     * @return an updated list of all users.
     */
    public List<User> getListOfUsers()
    {
        loadUserListFromHDD();
        return listOfUsers;
    }
}
