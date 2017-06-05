/*
 * UserPersistenceManager.
 * This class saves and loads TreeMaps containing userlists.
 * The saving/loading is done to/from the HDD.
 * The filepath used is given as a parameter in the class constructor.
 */
package DAL;

import BE.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Alex, Dennis & Niels.
 */
public class UserPersistenceManager
{

    /**
     * Variables.
     */
    private String path;

    /**
     * Constructor.
     * Sets the default path for saving and loading the user list.
     * @param path 
     */
    public UserPersistenceManager(String path)
    {
        //Set default path for this UserPersistenceManager.
        this.path = path;
    }

    /**
     * Saves a list of all the users to the HDD.
     *
     * @param userList
     * @throws IOException
     */
    public void saveUserListToHDD(List<User> userList) throws IOException
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)))
        {
            oos.writeObject(userList);
            oos.close();
        }
    }

    /**
     * Loads a saved list of all users.
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<User> loadUserListFromHDD() throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path)))
        {
            return (List<User>) ois.readObject();
        }
    }
}
