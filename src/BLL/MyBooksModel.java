/*
 * MyBooksModel.
 * This class acts as the connection between the BLL/DAL layers and the GUI.
 */
package BLL;

import BE.Book;
import BE.User;
import BLL.BooklistSearch.ISearchMatch;
import BLL.BooklistSearch.ISearchType;
import BLL.BooklistSearch.BooklistSearch;
import BLL.BooklistSearch.SearchMatchContains;
import BLL.BooklistSearch.SearchTypeAuthorName;
import BLL.BooklistSearch.SearchTypeTitle;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Niels, Dennis & Alex
 */
public class MyBooksModel
{

    /**
     * Variables
     */
    private UserManager userManager;
    private BooksManager booksManager;
    private User selectedUser;
    private Book selectedBook;
    private Book selectedBookCopi;

    /**
     * Constructor
     */
    public MyBooksModel()
    {
        userManager = UserManager.getInstance();
        booksManager = BooksManager.getInstance();
    }

    /**
     * Method used in the process of loading in the user list from the HDD
     * and then returning it.
     *
     * @return the list of users.
     *
     */
    public List<User> getAllUsers()
    {
        return userManager.getListOfUsers();
    }

    /**
     * Method to create a user
     *
     * @param isAdmin
     * @param name
     * @param address
     * @param email
     * @param password
     */
    public void createUser(boolean isAdmin, String name, String address, String email, String password)
    {
        userManager.addUser(userManager.createUser(isAdmin, name, address, email, password));
        userManager.saveUserListToHDD();
    }

    /**
     * Method to delete user
     *
     * @param userId
     */
    public void deleteUser(int userId)
    {
        userManager.deleteUser(userId);
        userManager.saveUserListToHDD();
    }

    /**
     * Method to edit user
     *
     * @param id
     * @param admin
     * @param name
     * @param address
     * @param email
     * @param password
     */
    public void editUser(int id, boolean admin, String name, String address, String email, String password)
    {
        userManager.editUser(new User(id, admin, name, address, email, password));
        userManager.saveUserListToHDD();
    }

    /**
     * @returns list of all books
     */
    public List<Book> getAllBooks()
    {
        return booksManager.getListBooks();
    }

    /**
     * @param query
     * @returns the mixed search list
     */
    public List<Book> searchBook(String query)
    {
        //Search type.
        ISearchType searchType = new SearchTypeTitle();

        //Search matcher.
        ISearchMatch searchMatcher = new SearchMatchContains(query);

        //Search.
        List<Book> list1 = BooklistSearch.getInstance().search(getAllBooks(), searchType, searchMatcher);

        //Search by start by artist name.
        searchType = new SearchTypeAuthorName();

        List<Book> list2 = BooklistSearch.getInstance().search(getAllBooks(), searchType, searchMatcher);

        return BooklistSearch.getInstance().mixList(list1, list2);
    }

    /**
     * @return the selectedUser
     */
    public User getSelectedUser()
    {
        return selectedUser;
    }

    /**
     * @param selectedUser the selectedUser to set
     */
    public void setSelectedUser(User selectedUser)
    {
        this.selectedUser = selectedUser;
    }

    /**
     * Updates the booklist held by BooksManager with the one stored on the HDD,
     * then returns a single book.
     *
     * @param ISBN
     * @return a book based on the ISBN number given, if no book is found in the
     * system with the ISBN, null is returned instead.
     */
    public Book getSingleBook(String ISBN)
    {
        return booksManager.getSingleBook(ISBN);
    }

    /**
     * Removes a book with the given ISBN String from the book list and book
     * amounts/copies list, but only if it does not have a status of being "Lent
     * out". The status check ignores case.
     *
     * @param ISBN
     * @param id
     */
    public void removeBook(String ISBN, int id)
    {
        booksManager.removeBookCopy(ISBN, id);
    }

    /**
     * Method used to create and add a book + the amount of copies of it
     * available. The book is registered based on its ISBN String, if the String
     * is already registered, the old book will be written over. The updated
     * book list is afterwards saved to the HDD when it reaches the DAL layer.
     * The difference between this method and the method
     * "editBookCopiesAvailable" is that this method assigns a generated id to
     * the book and is meant for creating new ones.
     *
     * @param ISBN
     * @param title
     * @param author
     * @param edition
     * @param year
     * @param publisher
     * @param status
     * @param dueDate
     * @param borrowers
     * @param daysLentOut
     * @param creationDay
     */
    public void createAndAddBook(String ISBN, String title, String author, int edition, int year, String publisher, String status, String dueDate, int borrowers, String daysLentOut, String creationDay)
    {
        booksManager.createAndAddBook(ISBN, title, author, edition, year, publisher, status, dueDate, borrowers, daysLentOut, creationDay);
        booksManager.saveBooklistsToHDD();
    }

    /**
     * @return the selectedBook
     */
    public Book getSelectedBook()
    {
        return selectedBook;
    }

    public void editBook(Book bookToEdit)
    {
        booksManager.editBookAndCopies(bookToEdit);
    }

    /**
     * @param selectedBook the selectedBook to set
     */
    public void setSelectedBook(Book selectedBook)
    {
        this.selectedBook = selectedBook;
    }

    /**
     * method to edit user
     *
     * @param user
     */
    public void editUserChanges(User user)
    {
        userManager.editUser(user);
        saveAllData();
    }

    /**
     * saves all users and books to HDD
     */
    public void saveAllData()
    {
        userManager.saveUserListToHDD();
        booksManager.saveBooklistsToHDD();

    }

    /**
     * @return the selectedBookCopi
     */
    public Book getSelectedBookCopi()
    {
        return selectedBookCopi;
    }

    /**
     * @param selectedBookCopi the selectedBookCopi to set
     */
    public void setSelectedBookCopi(Book selectedBookCopi)
    {
        this.selectedBookCopi = selectedBookCopi;
    }

    /**
     * returns book to the library
     *
     * @param book
     * @param user
     */
    public void returnBook(Book book, User user)
    {
        if (user.getBooksBorrowed().size() >= 0)
        {
            for (Book books : user.getBooksBorrowed())
            {
                if (book.getId() == books.getId())
                {
                    user.getBooksBorrowed().remove(user.getBooksBorrowed().indexOf(books));
                    editUserChanges(user);
                    selectedUser = user;
                    editBook(book);
                    return;
                }
            }
        }
    }

    public boolean isInt(String string)
    {
        try
        {
            Integer.parseInt(string);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public String getADueDate()
    {
        String dueDate = String.valueOf(LocalDateTime.now().getDayOfMonth()) + "-";
        if (LocalDateTime.now().getMonthValue() == 12)
        {
            dueDate += String.valueOf(1) + "-";
            dueDate += String.valueOf(LocalDateTime.now().getYear() + 1);
        }
        else
        {
            dueDate += String.valueOf(LocalDateTime.now().getMonthValue() + 1) + "-";
            dueDate += String.valueOf(LocalDateTime.now().getYear());
        }
        return dueDate;
    }
    
    
}
