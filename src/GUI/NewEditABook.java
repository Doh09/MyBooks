/*
 * Class with 2 constructors.
 * One constructor opens up a window used to create and add new books and its copies.
 * The other constructor opens up a window allowing to edit a selected book and
 * it's book type/similar copies.
 */
package GUI;

import BE.Book;
import BLL.MyBooksModel;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class NewEditABook extends javax.swing.JDialog
{

    /**
     * Variables
     */
    private MyBooksModel model;
    private boolean isDone = false;
    private String dueDate;
    private int borrowers;
    private String daysLentOut;
    private String creationDay;
    private AdminView AV;
    //Variables to use in ISBN check.
    private String tempAuthor;
    private String tempTitle;
    private String tempEdition;
    private String tempYear;
    private String tempPublisher;
    private boolean saveTempVariables; //this variable IS used, despite NetBeans saying it isn't.

    /**
     * Constructor for "New Book". Creates a new form NewEditABook set up for
     * creating + adding books.
     *
     * @param parent
     * @param modal
     * @param model
     * @param AV
     */
    public NewEditABook(java.awt.Frame parent, boolean modal, MyBooksModel model, AdminView AV)
    {
        super(parent, modal);
        initComponents();
        this.setTitle("MyBooks - New Book");
        this.model = model;
        ISBN.setEditable(true);
        setupISBNFieldAnyKeyListener();
        tempAuthor = "";
        tempTitle = "";
        tempEdition = "";
        tempYear = "";
        tempPublisher = "";
        this.AV = AV;
        AV.setProgressBarVisibiity(true);
    }

    /**
     * Constructor for the "Edit Book" window. Creates a new form NewEditABook
     * set up for editing.
     *
     * @param parent
     * @param modal
     * @param book
     * @param model
     */
    public NewEditABook(java.awt.Frame parent, boolean modal, Book book, MyBooksModel model)
    {
        super(parent, modal);
        initComponents();
        this.setTitle("MyBooks - Edit Book");
        this.model = model;
        ISBN.setEditable(false);
        BookId.setText(String.valueOf(book.getId()));
        ISBN.setText(book.getISBN());
        Author.setText(book.getAuthor());
        Title.setText(book.getTitle());
        Edition.setText(String.valueOf(book.getEdition()));
        Year.setText(String.valueOf(book.getYear()));
        Publisher.setText(book.getPublisher());
        switch (book.getStatus())
        {
            case "On Shelf":
                status.setSelectedIndex(0);
                break;
            case "Lend out":
                status.setSelectedIndex(1);
                break;
            case "Damaged":
                status.setSelectedIndex(2);
                break;
        }
        dueDate = book.getDueDate();
        borrowers = book.getBorrowers();
        daysLentOut = book.getDaysLentOut();
        creationDay = book.getCreationDay();
        copiesField.setEnabled(false);
    }

    private void setISBNCheckVariables()
    {
        tempAuthor = Author.getText();
        tempTitle = Title.getText();
        tempEdition = Edition.getText();
        tempYear = Year.getText();
        tempPublisher = Publisher.getText();
    }

    private void getISBNCheckVariables()
    {
        Author.setText(tempAuthor);
        Title.setText(tempTitle);
        Edition.setText(tempEdition);
        Year.setText(tempYear);
        Publisher.setText(tempPublisher);
    }

    /**
     * Returns the boolean isDone.
     *
     * @return boolean isDone.
     */
    public boolean getIsdone()
    {
        return isDone;
    }

    private void checkIfISBNKnown(Book b, boolean knownISBN)
    {
        
        if (knownISBN)
        {
            setISBNCheckVariables();
            Author.setText(b.getAuthor());
            Title.setText(b.getTitle());
            Edition.setText(String.valueOf(b.getEdition()));
            Year.setText(String.valueOf(b.getYear()));
            Publisher.setText(b.getPublisher());
            Author.setEnabled(false);
            Title.setEnabled(false);
            Edition.setEnabled(false);
            Year.setEnabled(false);
            Publisher.setEnabled(false);
        }
        else
        {      
            if(!tempAuthor.isEmpty() || !tempEdition.isEmpty() || !tempPublisher.isEmpty() || !tempTitle.isEmpty() || !tempYear.isEmpty())
            getISBNCheckVariables();
            Author.setEnabled(true);
            Title.setEnabled(true);
            Edition.setEnabled(true);
            Year.setEnabled(true);
            Publisher.setEnabled(true);            
        }
    }

    private void setupISBNFieldAnyKeyListener()
    {
        saveTempVariables = true;
        ISBN.addKeyListener(
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
                        {
                            String check = ISBN.getText();
                            if (model.getSingleBook(check) instanceof Book)
                            {
                                saveTempVariables = false;
                                checkIfISBNKnown(model.getSingleBook(check), true);
                            }
                            else
                            {
                                saveTempVariables = true;
                                checkIfISBNKnown(null, false);
                            }
                        }
                    }
                }
        );
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BookId = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ISBN = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Author = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Title = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        Edition = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Year = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        Publisher = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        Copies = new javax.swing.JLabel();
        copiesField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Copy Id:");

        BookId.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BookId, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BookId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel2.setText("ISBN:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel3.setText("Author:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Author, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel3)
            .addComponent(Author, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel4.setText("Title:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel5.setText("Edition:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Edition, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Edition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel6.setText("Year:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel7.setText("Publisher:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Publisher, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Publisher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel8.setText("Status:");

        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "On Shelf", "Lend out", "Damaged" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okButtonActionPerformed(evt);
            }
        });

        Copies.setText("Copies:");

        copiesField.setText("1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(Copies)
                                .addGap(29, 29, 29)
                                .addComponent(copiesField))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Copies)
                    .addComponent(copiesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

        int response = JOptionPane.YES_OPTION;
        if (model.isInt(Edition.getText()) && model.isInt(Year.getText()) && model.isInt(copiesField.getText()))
        {
            if (!ISBN.getText().isEmpty() && !Title.getText().isEmpty() && !Author.getText().isEmpty() && !Edition.getText().isEmpty() && !Year.getText().isEmpty() && !Publisher.getText().isEmpty() && BookId.getText().isEmpty())
            {
                if (Integer.parseInt(copiesField.getText()) > 99)
                {
                    response = askForConfirmation();
                }
                if (response == JOptionPane.YES_OPTION)
                {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    creationDay = LocalDateTime.now().format(format);
                    //Create the runnable + thread, which will create the new books and control the loading bar.
                    newBooksRunnableAndThread();
                }

            }
            else if (!ISBN.getText().isEmpty() && !Title.getText().isEmpty() && !Author.getText().isEmpty() && !Edition.getText().isEmpty() && !Year.getText().isEmpty() && !Publisher.getText().isEmpty() && !BookId.getText().isEmpty())
            {
                model.editBook(new Book(ISBN.getText(), Title.getText(), Author.getText(), Integer.parseInt(Edition.getText()), Integer.parseInt(Year.getText()), Publisher.getText(), Integer.parseInt(BookId.getText()), status.getSelectedItem().toString(), dueDate, borrowers, daysLentOut, creationDay));
            }
            if (response == JOptionPane.YES_OPTION)
            {
                isDone = true;
                dispose();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Edition & Year must be a number ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private int askForConfirmation() throws HeadlessException
    {
        int response;
        //setup the first JOptionPane
        Object[] options1 =
        {
            "Yes",
            "Cancel",
        };
        response = JOptionPane.showOptionDialog(null,
                "The amount of copies you requested added is relatively large."
                + "\nDepending on your computer, adding them may take a while."
                + "\nYou will not be able to exit the program while they are being added."
                + "\nAre you sure you wish to add them at this time?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options1, //The array holding the text of the buttons
                options1[0]);
        return response;
    }

    private void newBooksRunnableAndThread()
    {
        //Create Runnable + Tread.
        Runnable runner = new Runnable()
        {
            public void run()
            {
                AV.setRunnableRunning(true);
                AV.setProgressBarBackground(Color.orange);
                int loadingBarStep = 0;
                double percentage = 0;
                for (int i = 0; i < Integer.parseInt(copiesField.getText()); i++)
                {
                    model.createAndAddBook(ISBN.getText(), Title.getText(), Author.getText(), Integer.parseInt(Edition.getText()), Integer.parseInt(Year.getText()), Publisher.getText(), status.getSelectedItem().toString(), "0", 0, "0", creationDay);

                    if (i > 0)
                    {
                        percentage = ((100 / Double.parseDouble(copiesField.getText())) * i);
                        try
                        {
                            Thread.sleep(2);
                            AV.setProgressBarValue((int) percentage);
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            break;
                        }
                        AV.repaintProgressBar();
                        switch (loadingBarStep)
                        {
                            case 0:
                                AV.setProgressBarText("loading books");
                                loadingBarStep++;
                                break;
                            case 4:
                                AV.setProgressBarText("loading books.");
                                loadingBarStep++;
                                break;
                            case 8:
                                AV.setProgressBarText("loading books..");
                                loadingBarStep++;
                                break;
                            case 12:
                                AV.setProgressBarText("loading books...");
                                loadingBarStep = 0;
                                break;
                            default:
                                loadingBarStep++;
                                break;
                        }
                        System.out.println(percentage + "%");
                    }
                }
                AV.setProgressBarValue(100);
                AV.repaintProgressBar();
                AV.setProgressBarBackground(null);
                AV.setProgressBarText("Done loading books");
                model.saveAllData();
                AV.AAB.updateTable(model.getAllBooks());
                AV.setRunnableRunning(false);
            }
        };
        Thread t = new Thread(runner, "Code Executer");
        t.setDaemon(true);
        t.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Author;
    private javax.swing.JTextField BookId;
    private javax.swing.JLabel Copies;
    private javax.swing.JTextField Edition;
    private javax.swing.JTextField ISBN;
    private javax.swing.JTextField Publisher;
    private javax.swing.JTextField Title;
    private javax.swing.JTextField Year;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField copiesField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox status;
    // End of variables declaration//GEN-END:variables

}
