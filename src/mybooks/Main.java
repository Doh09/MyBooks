/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybooks;

import BLL.MyBooksModel;
import GUI.Login;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;

import javax.swing.UIManager;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        setLookAndFeel();
        MyBooksModel model = new MyBooksModel();
        

        Login userLogin = new Login(model);
        userLogin.setLocationRelativeTo(null);
        userLogin.setVisible(true);

    }

    private static void setLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
