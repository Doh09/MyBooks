/*
 * Class that is the main GUI view if a user logs in as an admin.
 * The class holds 2 tabbed panels, one for user administration and one for book administration.
 * In the respective panels the users/books can be added, edited and removed.
 * Furthermore the admin can clear any fines a user have on their account.
 */
package GUI;

import BLL.MyBooksModel;
import GUI.TableModels.AdminAllBooksTableModel;
import GUI.TableModels.AdminAllUserTableModel;
import GUI.TableModels.AdminBookCopies;
import GUI.TableModels.UserLoansTableModel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class AdminView extends javax.swing.JFrame
{

    /**
     * Variables
     */
    private MyBooksModel model;
    private final String searchBarHintText = "Click here to search...";
    public AdminAllBooksTableModel AAB;
    private AdminAllUserTableModel AAU;
    private UserLoansTableModel UL;
    private AdminBookCopies ABC;
    private boolean runnableRunning = false;

    /**
     * Creates new form AdminView
     */
    public AdminView(MyBooksModel model)
    {
        initComponents();
        this.setTitle("MyBooks - Administration");
        this.model = model;
        AAB = new AdminAllBooksTableModel(model.getAllBooks());
        AAU = new AdminAllUserTableModel(model.getAllUsers());
        UL = new UserLoansTableModel(new ArrayList<>());
        ABC = new AdminBookCopies(new ArrayList<>());
        setModels();
        setListeners();
        progressBar.setVisible(false);
    }

    /**
     * Setup the table models for the GUI.
     */
    private void setModels()
    {

        AdminAllBooks.setModel(AAB);
        AdminAllUser.setModel(AAU);
        AdminBookCopies.setModel(ABC);
        AdminUserLoans.setModel(UL);
    }

    /**
     * Setup the selection listeners for the GUI
     */
    private void setListeners()
    {

        //Add selection listener to the AdminAllBooks table.
        AdminAllBooks.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                if (lse.getValueIsAdjusting())
                {
                    return;
                }
                onSelectedBook(lse);
            }
        });

        //Add selection listener to the AdminAllUser table.
        AdminAllUser.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                if (lse.getValueIsAdjusting())
                {
                    return;
                }
                onSelectedUser(lse);
            }
        });

        //Add selection listener to the AdminBookCopies table.
        AdminBookCopies.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                if (lse.getValueIsAdjusting())
                {
                    return;
                }
                onAdminSelectedBookCopi(lse);
            }
        });

        searchField.addKeyListener(
                new KeyListener()
                {

                    @Override
                    public void keyPressed(KeyEvent e)
                    {

                    }

                    @Override
                    public void keyTyped(KeyEvent e)
                    {
                    }

                    @Override
                    public void keyReleased(KeyEvent e)
                    {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER && searchField.hasFocus())
                        {
                            searchButton.doClick();
                            searchField.requestFocus();
                        }
                    }
                }
        );

    }

    /**
     * Listener method that is triggered when a book is selected from the
     * AdminAllBooks table, it sets the currently selected book variable to hold
     * the selected book. The method also updates the AllBookCopies table to
     * reflect the copies of the selected book, if no book is selected the
     * AllBookCopies table is made empty.
     *
     * @param lse
     */
    private void onSelectedBook(ListSelectionEvent lse)
    {
        int rowIndex = AdminAllBooks.getSelectedRow();
        if (AdminAllBooks.convertRowIndexToModel(rowIndex) > -1)
        {
            model.setSelectedBook(model.getAllBooks().get(AdminAllBooks.convertRowIndexToModel(rowIndex)));
            ABC.updateTable(model.getSelectedBook().getBookCopies());
        }
        else
        {
            ABC.updateTable(new ArrayList<>());
        }
    }

    /**
     * Listener method that is triggered when a user is selected from the
     * AdminAllUser table, it sets the currently selected user variable to hold
     * the selected user. When the user is selected, his/her information is
     * displayed. The method also updates the UserLoans table to reflect the
     * list of the user selected, if no user is selected the UserLoans table is
     * made empty.
     *
     * @param lse
     */
    private void onSelectedUser(ListSelectionEvent lse)
    {
        int rowIndex = AdminAllUser.getSelectedRow();
        if (AdminAllUser.convertRowIndexToModel(rowIndex) > -1)
        {
            model.setSelectedUser(model.getAllUsers().get(AdminAllUser.convertRowIndexToModel(rowIndex)));
            unpaidFinesField.setText(String.valueOf(model.getSelectedUser().getUnpaiedFines()));
            paidFinesField.setText(String.valueOf(model.getSelectedUser().getPaidFines()));
            UL.updateTable(model.getSelectedUser().getBooksBorrowed());
        }
        else
        {
            UL.updateTable(new ArrayList<>());
        }
    }

    /**
     * Listener that sets the "selectedBookCopi" variable to hold the currently
     * selected book copy.
     *
     * @param lse
     */
    private void onAdminSelectedBookCopi(ListSelectionEvent lse)
    {
        int rowIndex = AdminBookCopies.getSelectedRow();
        if (AdminBookCopies.convertRowIndexToModel(rowIndex) > -1)
        {
            model.setSelectedBookCopi(model.getSelectedBook().getBookCopies().get(AdminBookCopies.convertRowIndexToModel(rowIndex)));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        unpaidFinesField = new javax.swing.JTextField();
        ClearButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AdminUserLoans = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        paidFinesField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        newUser = new javax.swing.JButton();
        editUser = new javax.swing.JButton();
        deleteUser = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AdminAllUser = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AdminAllBooks = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        AdminBookCopies = new javax.swing.JTable();
        newBookButton = new javax.swing.JButton();
        editBookButton = new javax.swing.JButton();
        DeleteBookButton = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                AdminStateChanged(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("User Loans"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Unpaid Fines"));

        jLabel2.setText("Kr.");

        unpaidFinesField.setEditable(false);

        ClearButton.setText("Clear");
        ClearButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(unpaidFinesField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(ClearButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unpaidFinesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClearButton)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        AdminUserLoans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(AdminUserLoans);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Paid Fines"));

        paidFinesField.setEditable(false);

        jLabel1.setText("Kr.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(paidFinesField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paidFinesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("User Administration"));

        newUser.setText("New");
        newUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newUserActionPerformed(evt);
            }
        });

        editUser.setText("Edit");
        editUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editUserActionPerformed(evt);
            }
        });

        deleteUser.setText("Delete");
        deleteUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        AdminAllUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane3.setViewportView(AdminAllUser);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Users", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        searchField.setText("Click here to search...");
        searchField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                searchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                searchFieldFocusLost(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("All Book Titles"));

        AdminAllBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(AdminAllBooks);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Book Copies"));

        AdminBookCopies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(AdminBookCopies);

        newBookButton.setText("New");
        newBookButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newBookButtonActionPerformed(evt);
            }
        });

        editBookButton.setText("Edit");
        editBookButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBookButtonActionPerformed(evt);
            }
        });

        DeleteBookButton.setText("Delete");
        DeleteBookButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DeleteBookButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editBookButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeleteBookButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newBookButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(newBookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(editBookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteBookButton)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Books", jPanel2);

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                logoutBtnActionPerformed(evt);
            }
        });

        progressBar.setBorder(javax.swing.BorderFactory.createTitledBorder("Books loading bar"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(logoutBtn)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutBtn)
                        .addContainerGap())
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Button method that opens up a new window to create and add a new book
     * type/copy to the library system. When the window closes down again the
     * AdminAllBooksTable is updated to reflect any possible change.
     *
     * @param evt
     */
    private void newBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBookButtonActionPerformed
        NewEditABook neweditbook = new NewEditABook(this, true, model, this);
        neweditbook.setLocationRelativeTo(this);
        neweditbook.setVisible(true);
    }//GEN-LAST:event_newBookButtonActionPerformed

    /**
     * Button method that opens up a new window to edit a book type and its
     * copies. If no book copy is selected, an error message is displayed. When
     * the window closes down again the AdminAllBooksTable is updated to reflect
     * any possible change.
     *
     * @param evt
     */
    private void editBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBookButtonActionPerformed
        if (model.getSelectedBookCopi() != null)
        {
            NewEditABook neweditbook = new NewEditABook(this, true, model.getSelectedBookCopi(), model);
            neweditbook.setLocationRelativeTo(this);
            neweditbook.setVisible(true);
            if (neweditbook.getIsdone())
            {
                AAB.updateTable(model.getAllBooks());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Can't edit a book if none is selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editBookButtonActionPerformed

    /**
     * Button method that opens up a new window to create and add a new user to
     * the library system. When the window closes down again the
     * AdminAllUserTable is updated to reflect any possible change.
     *
     * @param evt
     */
    private void newUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserActionPerformed
        NewEditAUser newEditAUser = new NewEditAUser(model, this, true);
        newEditAUser.setLocationRelativeTo(this);
        newEditAUser.setVisible(true);
        if (newEditAUser.getIsdone())
        {
            AAU.updateTable(model.getAllUsers());
        }
    }//GEN-LAST:event_newUserActionPerformed

    /**
     * Button method that opens up a new window to edit a user. If no user is
     * selected, an error message is displayed. When the window closes down
     * again the AdminAllUserTable is updated to reflect any possible change.
     *
     * @param evt
     */
    private void editUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserActionPerformed
        if (model.getSelectedUser() != null)
        {
            NewEditAUser newEditAUser = new NewEditAUser(model, model.getSelectedUser(), this, true);
            newEditAUser.setLocationRelativeTo(this);
            newEditAUser.setVisible(true);
            if (newEditAUser.getIsdone())
            {
                AAU.updateTable(model.getAllUsers());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Can't edit a user if none is selected", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_editUserActionPerformed

    /**
     * Button method used to perform searches for books in the library. When the
     * button is clicked, a search is commenced based on the String in the
     * searchField, results matching the search is displayed in the
     * AdminAllBooksTable. The search is only commenced if the String is not
     * empty.
     *
     * @param evt
     */
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchButtonActionPerformed
    {//GEN-HEADEREND:event_searchButtonActionPerformed
        if (searchField.getText().trim().isEmpty() || searchField.getText().equalsIgnoreCase(searchBarHintText))
        {
            AAB.updateTable(model.getAllBooks());
        }
        else if (!searchField.getText().trim().isEmpty() && !searchField.getText().equalsIgnoreCase(searchBarHintText))
        {
            AAB.updateTable(model.searchBook(searchField.getText()));
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    /**
     * Method used to delete a user from the library system, based on his/her
     * user id. The "selectedUser" variable is set to null and the
     * AdminAllUserTable is updated to reflect the change.
     *
     * @param evt
     */
    private void deleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActionPerformed
        model.deleteUser(model.getSelectedUser().getId());
        model.setSelectedUser(null);
        AAU.updateTable(model.getAllUsers());
    }//GEN-LAST:event_deleteUserActionPerformed

    /**
     * Method triggered when the tab is switched between "User" and "Books" in
     * the AdminView. The method updates the content on tab about to be
     * displayed.
     *
     * @param evt
     */
    private void AdminStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_AdminStateChanged
    {//GEN-HEADEREND:event_AdminStateChanged
        if (model != null)
        {
            setModels();
            progressBar.setVisible(!progressBar.isVisible());
        }
    }//GEN-LAST:event_AdminStateChanged

    /**
     * Method used to delete a book from the library system, based on the book
     * Id and ISBN. The "selectedBookCopi" variable is set to null and the
     * AdminAllBookTable is updated to reflect the change.
     *
     * @param evt
     */
    private void DeleteBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBookButtonActionPerformed
        model.removeBook(model.getSelectedBookCopi().getISBN(), model.getSelectedBookCopi().getId());
        AAB.updateTable(model.getAllBooks());
        model.setSelectedBookCopi(null);
    }//GEN-LAST:event_DeleteBookButtonActionPerformed

    /**
     * Method used to clear the fines of the currently selected user. The now
     * paid fines is added to the total fine amount the user has paid over
     * his/hers accounts lifetime.
     *
     * @param evt
     */
    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        model.getSelectedUser().setPaidFines(model.getSelectedUser().getPaidFines() + model.getSelectedUser().getUnpaiedFines());
        model.getSelectedUser().setUnpaiedFines(0);
        unpaidFinesField.setText(String.valueOf(model.getSelectedUser().getUnpaiedFines()));
        paidFinesField.setText(String.valueOf(model.getSelectedUser().getPaidFines()));
        model.editUserChanges(model.getSelectedUser());

    }//GEN-LAST:event_ClearButtonActionPerformed

    /**
     * Method called when the window closed by the close option in the top right
     * corner. The method saves all the data of the library system to the HDD
     * before the program is shut down. Program cannot shut down if runnable is
     * running, this is to avoid data loss.
     *
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (runnableRunning)
        {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            JOptionPane.showMessageDialog(this, "Please wait for the program to finish loading books!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            model.saveAllData();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * Method that logs out the user, resets the "selected" variables and
     * switches from the AdminView to the login screen. Program cannot shut
     * down/log out if runnable is running, this is to avoid data loss.
     *
     * @param evt
     */
    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_logoutBtnActionPerformed
    {//GEN-HEADEREND:event_logoutBtnActionPerformed
        if (!runnableRunning)
        {
            model.setSelectedBook(null);
            model.setSelectedBookCopi(null);
            model.setSelectedUser(null);
            Login userLogin = new Login(model);
            userLogin.setLocationRelativeTo(null);
            userLogin.setVisible(true);
            progressBar.setVisible(false);
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please wait for the program to finish loading books!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_logoutBtnActionPerformed

    /**
     * Method that puts the String "searchBarHintText" in the searchField if it
     * is empty when de-selected.
     *
     * @param evt
     */
    private void searchFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_searchFieldFocusLost
    {//GEN-HEADEREND:event_searchFieldFocusLost
        if (searchField.getText().equalsIgnoreCase(""))
        {
            searchField.setText(searchBarHintText);
        }
    }//GEN-LAST:event_searchFieldFocusLost

    /**
     * Method that clears the searchField when the searchField gains focus, if
     * the text held by it at the time is the "searchBarHintText".
     *
     * @param evt
     */
    private void searchFieldFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_searchFieldFocusGained
    {//GEN-HEADEREND:event_searchFieldFocusGained
        if (searchField.getText().equalsIgnoreCase(searchBarHintText))
        {
            searchField.setText("");
        }
    }//GEN-LAST:event_searchFieldFocusGained

    //progressBar methods   
    public void setProgressBarText(String string)
    {
        if (!string.equalsIgnoreCase("Done loading books"))
        {
            progressBar.setBorder(BorderFactory.createTitledBorder(progressBar.getValue() + "% done " + string));
        }
        else
        {
            progressBar.setBorder(BorderFactory.createTitledBorder(string));
        }

    }

    public void setProgressBarVisibiity(boolean visible)
    {
        progressBar.setVisible(visible);
    }

    public void setProgressBarBackground(Color color)
    {
        progressBar.setBackground(color);
    }

    public void setProgressBarValue(int Value)
    {
        progressBar.setValue(Value);
    }

    public void repaintProgressBar()
    {
        progressBar.repaint();
    }

    public boolean getRunnableRunning()
    {
        return runnableRunning;
    }

    public void setRunnableRunning(boolean setting)
    {
        runnableRunning = setting;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AdminAllBooks;
    private javax.swing.JTable AdminAllUser;
    private javax.swing.JTable AdminBookCopies;
    private javax.swing.JTable AdminUserLoans;
    private javax.swing.JButton ClearButton;
    private javax.swing.JButton DeleteBookButton;
    private javax.swing.JButton deleteUser;
    private javax.swing.JButton editBookButton;
    private javax.swing.JButton editUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton newBookButton;
    private javax.swing.JButton newUser;
    private javax.swing.JTextField paidFinesField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField unpaidFinesField;
    // End of variables declaration//GEN-END:variables
}
