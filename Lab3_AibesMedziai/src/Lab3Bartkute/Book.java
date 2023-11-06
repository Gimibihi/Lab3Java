/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3Bartkute;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import laborai.studijosktu.KTUable;
import laborai.studijosktu.Ks;

/**
 *
 * @author Tautvilė
 */
public class Book implements KTUable<Book> {

    //general data
    final static int currentYear = LocalDate.now().getYear();
    //individual data
    private String Author;
    private String Name;
    private int PublishedYear;
    private int Pages;
    private int Price;
    private String Genre;
    private Boolean isValid;

    /**
     * empty constructor
     */
    public Book() {
    }

    /**
     * class constructor
     *
     * @param Author
     * @param Name
     * @param PublishedYear
     * @param Pages
     * @param Price
     * @param Genre
     */
    public Book(String Author, String Name, int PublishedYear, int Pages,
            int Price, String Genre) {
        this.Author = Author;
        this.Name = Name;
        this.PublishedYear = PublishedYear;
        this.Pages = Pages;
        this.Price = Price;
        this.Genre = Genre;
        this.isValid = true;
    }

    /**
     * book constructor with single parameter
     *
     * @param dataString
     */
    public Book(String dataString) {
        this.parse(dataString);
    }

    /**
     * Creates a new object from a string
     *
     * @param dataString line to create an object
     * @return book class object
     */
    @Override
    public Book create(String dataString) {
        return new Book(dataString);
    }

    /**
     * Forms an object from a string
     *
     * @param dataString line to form an object
     */
    @Override
    public final void parse(String dataString) {
        try {
            Scanner ed = new Scanner(dataString);
            ed.useDelimiter("; *");
            Author = ed.next();
            Name = ed.next();
            PublishedYear = ed.nextInt();
            Pages = ed.nextInt();
            Price = ed.nextInt();
            Genre = ed.next();
            this.isValid = true;
        } catch (InputMismatchException e) {
            Ks.ern("Bad forms -> " + dataString);
            this.isValid = false;
        } catch (NoSuchElementException e) {
            Ks.ern("Data missing -> " + dataString);
            this.isValid = false;
        }
    }

    /**
     * Checks object values ​​according to desired rules
     *
     * @return empty string or string-error type
     */
    @Override
    public String validate() {
        String errorType = "";
        if (PublishedYear > currentYear) {
            errorType = "Published year cannot be higher than current year.";
        }
        return errorType;
    }

    /**
     * Output of object values, specifying only the name of the object in the
     * output
     *
     * @return A line formed for output
     */
    @Override
    public String toString() {
        return String.format("%s %s %d %d %d %s",
                Author, Name, PublishedYear, Pages, Price, Genre);
    }

    /**
     * Converts object characteristics to an object array
     *
     * @return object array
     */
    public Object[] dataToArray() {
        Object[] a = {Author, Name, PublishedYear, Pages, Price, Genre};
        return a;
    }

    /**
     * is valid getter
     *
     * @return
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     * is valid setter
     *
     * @param isValid
     */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * author getter
     *
     * @return
     */
    public String getAuthor() {
        return Author;
    }

    /**
     * name getter
     *
     * @return
     */
    public String getName() {
        return Name;
    }

    /**
     * published year getter
     *
     * @return
     */
    public int getPublishedYear() {
        return PublishedYear;
    }

    /**
     * pages getter
     *
     * @return
     */
    public int GetPages() {
        return Pages;
    }

    /**
     * price getter
     *
     * @return
     */
    public int getPrice() {
        return Price;
    }

    /**
     * genre getter
     *
     * @return
     */
    public String getGenre() {
        return Genre;
    }
    /**
     * pages setter
     * @param pages 
     */
    public void setPages(int pages){
        this.Pages=pages;
    }

    /**
     * price setter
     *
     * @param price
     */
    public void setPrice(int price) {
        this.Price = price;
    }

    /**
     * Interface Comparable method used to sort objects
     *
     * @param a object to be compared
     * @return 0 | 1 | -1
     */
    @Override
    public int compareTo(Book a) {
        int nextPrice = a.getPrice(); //compare by price
        return Integer.compare(nextPrice, Price);
    }
    /**
     * Sort by author comparator
     */
    public final static Comparator<Book> byAuthor = new Comparator<Book>() {
        @Override
        public int compare(Book a1, Book a2) {
            int cmp = a1.getAuthor().compareTo(a2.getAuthor());
            return cmp;
        }
    };
    /**
     * Price sorting comparator
     */
    public final static Comparator<Book> byPrice = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            double k1 = b1.getPrice();
            double k2 = b2.getPrice();
            if (k1 < k2) {
                return -1;
            }
            if (k1 > k2) {
                return 1;
            }
            return 0;
        }
    };
    /**
     * Sort by year of publish and price comparator
     */
    public final static Comparator byPubYearAndPrice = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Book a1 = (Book) o1;
            Book a2 = (Book) o2;
            if (a1.getPublishedYear() < a2.getPublishedYear()) {
                return 1;
            }
            if (a1.getPublishedYear() > a2.getPublishedYear()) {
                return -1;
            }
            if (a1.getPrice() < a2.getPrice()) {
                return 1;
            }
            if (a1.getPrice() > a2.getPrice()) {
                return -1;
            }
            return 0;
        }
    };
    /**
     * Book class object builder
     */
    static class Builder {

        private String Author;
        private String Name;
        private int PublishedYear;
        private int Pages;
        private int Price;
        private String Genre;
        
        public Builder author(String author){
            this.Author = author;
            return this;
        }
        public Builder name(String name){
            this.Name = name;
            return this;
        }
        public Builder publishedYear(int year){
            this.PublishedYear = year;
            return this;
        }
        public Builder pages(int pages){
            this.Pages = pages;
            return this;
        }
        public Builder Price(int price){
            this.Price = price;
            return this;
        }
        public Builder Genre(String genre){
            this.Genre = genre;
            return this;
        }

        public Book build(){
            return new Book();
        }
        
        public Book buildRandom() {
            //To change body of generated methods, choose Tools | Templates.

            Random random = new Random(); //author, name, year, pages, price, genre
            String author = "Author" + random.nextInt(99999);
            String name = "Name" + random.nextInt(9);
            int year = 1995 + random.nextInt(20);
            int pages = random.nextInt(500);
            int price = random.nextInt(5055555);
            String genre = "Genre" + random.nextInt(4);
            return new Book(author, name, year, pages, price, genre);

        }

        Object Author(String renault) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
