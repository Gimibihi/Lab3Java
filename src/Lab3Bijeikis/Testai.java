package Lab3Bijeikis;

import laborai.demo.Automobilis;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.BstSetKTUx;
import laborai.studijosktu.Ks;
import laborai.studijosktu.SortedSetADTx;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class Testai {
    static Kompiuteris[] kompParduotuvė;
    static SortedSetADTx<Kompiuteris> kSerija = new BstSetKTUx(new Kompiuteris(),Kompiuteris.pagalKaina);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US);
        aibesTestas();
    }

    static SortedSetADTx genreruotiAibe(int kiekis, int generN){
        kompParduotuvė = new Kompiuteris[generN];
        for(int i = 0;i<generN;i++){
            kompParduotuvė[i] = new Kompiuteris.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(kompParduotuvė));
        kSerija.clear();
        for(int i=0;i<kiekis;i++){
            kSerija.add(kompParduotuvė[i]);
        }
        return kSerija;
    }

    public static void aibesTestas() throws CloneNotSupportedException{
        Kompiuteris komp1 = new Kompiuteris("Intel","i5",16,1024,12000,999.9);
        Kompiuteris komp2 = new Kompiuteris.Builder()
                .procGamintojas("AMD")
                .procModelis("Ryzen 7")
                .ramuKiekis(32)
                .HDDVieta(512)
                .nasumas(13045)
                .kaina(1299).build();
        Kompiuteris komp3 = new Kompiuteris.Builder().buildRandom();
        Kompiuteris komp4 = new Kompiuteris("Intel;i3;8;256;12000;200.0");
        Kompiuteris komp5 = new Kompiuteris("Intel;i9;64;4096;16000;8459.4");
        Kompiuteris komp6 = new Kompiuteris("Apple;M1;8;256;8000;9000.0");
        Kompiuteris komp7 = new Kompiuteris("AMD;Threadripper;128;8096;19000;19000.0");

        Kompiuteris[] kompMasyvas = {komp7, komp4, komp1, komp6};

        Ks.oun("Kompiuteriu aibe:");
        SortedSetADTx<Kompiuteris> kompAibe = new BstSetKTUx(new Kompiuteris());

        for(Kompiuteris komp : kompMasyvas){
            kompAibe.add(komp);
            Ks.oun("Aibe papildoma: "+ komp + ". Jos dydis: " + kompAibe.size());
        }
        Ks.oun("");
        Ks.oun(kompAibe.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibeje?");
        for(Kompiuteris komp : kompMasyvas){
            Ks.oun(komp + ": " + kompAibe.contains(komp));
        }
        Ks.oun(komp3 + ": " + kompAibe.contains(komp3));
        Ks.oun(komp2 + ": " + kompAibe.contains(komp2));
        Ks.oun(komp5 + ": " + kompAibe.contains(komp5));

        SortedSetADTx<Kompiuteris> kompAibesKopija = (SortedSetADTx<Kompiuteris>) kompAibe.clone();
        Ks.oun("Kopija");
        Ks.oun(kompAibesKopija.toVisualizedString(""));

        Ks.oun("Elementu salinimas is kopijos. Aibes dydis pries: "+ kompAibesKopija.size());
        kompAibesKopija.remove(komp4);
        Ks.oun("Pasalinta: " + komp4 + ". Jos dydis: "+kompAibesKopija.size());
        Ks.oun("Aibe po pasalinimo");
        Ks.oun(kompAibesKopija.toVisualizedString(""));

        Ks.oun("\nPrie aibes pridetas pasalintas kompiuteris: " + komp4);
        kompAibesKopija.add(komp4);
        Ks.oun(kompAibesKopija.toVisualizedString(""));

        Ks.oun("Kompiuteriu aibė su iteratoriumi:");
        Ks.oun("");
        for (Kompiuteris komp : kompAibe) {
            Ks.oun(komp);
        }
        Ks.oun("");

        Ks.oun("Kompiuteriu aibė AVL-medyje:");
        SortedSetADTx<Kompiuteris> kompAibe2 = new AvlSetKTUx(new Kompiuteris());
        for (Kompiuteris komp : kompAibe) {
            kompAibe2.add(komp);
        }
        Ks.ounn(kompAibe2.toVisualizedString(""));
    }
}
