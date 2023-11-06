/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3Bartkute;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import laborai.studijosktu.*;

/**
 *
 * @author Tautvilė
 */
public class BookTest {
    static Book[] bookBase;
    static SortedSetADTx<Book> aSeries = new BstSetKTUx(new Book(), Book.byPrice);
    
    public static void main(String[] args) throws CloneNotSupportedException{
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        SetTest();
    }
    /**
     * Method which generates set
     * @param kiek Amount
     * @param generN Generate new
     * @return Set
     */
    static SortedSetADTx generateSet(int kiek, int generN){
        bookBase = new Book[generN];
        for(int i=0; i<generN; i++){
            bookBase[i]=new Book.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(bookBase));
        aSeries.clear();
        for(int i=0; i<kiek; i++){
            aSeries.add(bookBase[i]);
        }
        return aSeries;
    }
    /**
     * Set test
     * @throws CloneNotSupportedException 
     */
    public static void SetTest() throws CloneNotSupportedException{
        Book b1 = new Book("Martin", "A Game of Thrones", 1984, 584, 36, "Fantasy");
        Book b2 = new Book.Builder().buildRandom();
        Book b3 = new Book.Builder().buildRandom();
        Book b4 = new Book.Builder().buildRandom();
        Book b5 = new Book.Builder().buildRandom();
        Book b6 = new Book.Builder().buildRandom();
        Book b7 = new Book.Builder().buildRandom();
        Book b8 = new Book.Builder().buildRandom();
        Book b9 = new Book.Builder().buildRandom();
        
        Book[] bookArray = {b9, b7, b8, b5, b1, b6};

        Ks.oun("Book set:");
        SortedSetADTx<Book> bookSet = new BstSetKTUx(new Book());

        for (Book b : bookArray) {
            bookSet.add(b);
            Ks.oun("Set additional: " + b + ". Size: " + bookSet.size());
        }
        Ks.oun("");
        Ks.oun(bookSet.toVisualizedString(""));

        SortedSetADTx<Book> bookSetCopy
                = (SortedSetADTx<Book>) bookSet.clone();

        bookSetCopy.add(b2);
        bookSetCopy.add(b3);
        bookSetCopy.add(b4);
        Ks.oun("Supplemented bookSet copy:");
        Ks.oun(bookSetCopy.toVisualizedString(""));

        b9.setPages(423);

        Ks.oun("Original:");
        Ks.ounn(bookSet.toVisualizedString(""));

        Ks.oun("Do the elements exist in the set?");
        for (Book b : bookArray) {
            Ks.oun(b + ": " + bookSet.contains(b));
        }
        Ks.oun(b2 + ": " + bookSet.contains(b2));
        Ks.oun(b3 + ": " + bookSet.contains(b3));
        Ks.oun(b4 + ": " + bookSet.contains(b4));
        Ks.oun("");

        Ks.oun("Do the elements exist in the copy of the set?");
        for (Book b : bookArray) {
            Ks.oun(b + ": " + bookSetCopy.contains(b));
        }
        Ks.oun(b2 + ": " + bookSetCopy.contains(b2));
        Ks.oun(b3 + ": " + bookSetCopy.contains(b3));
        Ks.oun(b4 + ": " + bookSetCopy.contains(b4));
        Ks.oun("");

        Ks.oun("Removing items from a copy. Set size before disposal:  " + bookSetCopy.size());
        for (Book b : new Book[]{b2, b1, b9, b8, b5, b3, b4, b2, b7, b6, b7, b9}) {
            bookSetCopy.remove(b);
            Ks.oun("Removed from the book copy: " + b + ". Size: " + bookSetCopy.size());
        }
        Ks.oun("");

        Ks.oun("A set of books with an iterator:");
        Ks.oun("");
        for (Book b : bookSet) {
            Ks.oun(b);
        }
        Ks.oun("");
        Ks.oun("Book set AVL-tree:");
        SortedSetADTx<Book> bookSet3 = new AvlSetKTUx(new Book());
        for (Book b : bookArray) {
            bookSet3.add(b);
        }
        Ks.ounn(bookSet3.toVisualizedString(""));

        Ks.oun("A set of books with an iterator:");
        Ks.oun("");
        for (Book b : bookSet3) {
            Ks.oun(b);
        }

        Ks.oun("");
        Ks.oun("Book set with reverse iterator:");
        Ks.oun("");
        Iterator iter = bookSet3.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Book set toString() method:");
        Ks.ounn(bookSet3);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        bookSet.clear();
        bookSet3.clear();

        Ks.oun("");
        Ks.oun("Book set DP-tree:");
        bookSet.load("Duomenys/Data1.txt");
        Ks.ounn(bookSet.toVisualizedString(""));
        Ks.oun("Find out why the tree only grew in one direction.");

        Ks.oun("");
        Ks.oun("Book set AVL-tree:");
        bookSet3.load("Duomenys/Data1.txt");
        Ks.ounn(bookSet3.toVisualizedString(""));

    }
}
