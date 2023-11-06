package laborai.demo;

import Lab3Bartkute.Book;
import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;

public class AutoApskaita {

    public static SetADT<String> automobiliuMarkes(Automobilis[] auto) {
        SetADT<Automobilis> uni = new BstSetKTU<>(Automobilis.pagalMarke);
        SetADT<String> kart = new BstSetKTU<>();
        for (Automobilis a : auto) {
            int sizeBefore = uni.size();
            uni.add(a);

            if (sizeBefore == uni.size()) {
                kart.add(a.getMarkė());
            }
        }
        return kart;
    }

    public static SetADT<String> automobiliuMarkes(Book[] bookArray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
