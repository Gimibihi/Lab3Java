/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3Bartkute;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.BstSetKTUx;
import laborai.studijosktu.Ks;
import laborai.studijosktu.SortedSetADTx;

/**
 *
 * @author Tautvilė
 */
public class EfficiencyTest {
    Book[] books;
    AvlSetKTUx<Book> bookAVL = new AvlSetKTUx(new Book(), Book.byPrice);
    BstSetKTUx<Book> bookBST = new BstSetKTUx(new Book(), Book.byPrice);
    int[] testingAmount = {2_0000, 4_0000, 8_0000, 16_0000};
    StringBuilder sb = new StringBuilder();
    /**
     * Generates a specified amount of books
     * @param amount 
     */
    void generateBooks(int amount) {
        books = new Book[amount];
        
        for (int i = 0; i < amount; i++) {
            books[i] = new Book.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(books));
        bookAVL.clear();
        bookBST.clear();
        for (Book b : books) {
            bookBST.add(b);
            bookAVL.add(b);
        }
    }
    /**
     * Clones a few lists and calculates time till completion
     * @param amount 
     */
    void simpleTest(int amount){
        long t0 = System.nanoTime();
        generateBooks(amount);
        Book randomElement = fetchRandomElement();
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
        
        bookBST.contains(randomElement);
        long t3 = System.nanoTime();
        
        bookAVL.contains(randomElement);
        long t4 = System.nanoTime();
        
        AddElement(bookBST);
        long t5 = System.nanoTime();
        
        AddElement(bookAVL);
        long t6 = System.nanoTime();
        
        bookBST.getLeafCount();
        long t7 = System.nanoTime();
        
        bookAVL.getLeafCount();
        long t8 = System.nanoTime();
        
        sb.append(String.format("%7d %7.8f %7.8f %7.8f %7.8f %7.8f %7.8f %7.8f %7.8f \n", amount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9, (t5 - t4) / 1e9, (t6 - t5) / 1e9,
                (t7 - t6) / 1e9, (t8 - t7) / 1e9));
    }
    /**
     * Generates 20 objects and does the test for selected  amount of objects
     * @return book
     */
    public String ChooseTest() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generateBooks(20);
        for (Book b : bookBST) {
            Ks.oun(b);
        }
        sb.append("1 - Data generation \n");
        sb.append("2 - Garabage collection\n");
        sb.append("3 - Finding an element in BST\n");
        sb.append("4 - Finding an element in AVL\n");
        sb.append("5 - Adding an element to BST\n");
        sb.append("6 - Adding an element to AVL\n");
        sb.append("7 - Calculating leaves in BST\n");
        sb.append("8 - Calculating leaves in AVL\n");
        sb.append(String.format("%6d %7d   %7d   %7d   %7d   %7d   %7d   %7d   %7d \n", 0, 1, 2, 3, 4, 5, 6, 7, 8));
        for (int n : testingAmount) {
            simpleTest(n);
        }
        sb.append("Efficiency test is over");

        System.out.println(sb.toString());
        return sb.toString();
    }
    /**
     * fects random element
     * @return book
     */
    Book fetchRandomElement(){
        Random random = new Random();
        return books[random.nextInt(books.length)];
    }
    /**
     * method which adds element
     * @param tree 
     */
    void AddElement(SortedSetADTx tree){
        Book book = new Book("Austin", "War", 2000, 475, 15, "Fantasy");
        tree.add(book);
    }
    public static void main(String[] args){
        new EfficiencyTest().ChooseTest();
    }
}
