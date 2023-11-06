package Lab3Bijeikis;

import laborai.demo.AutoGamyba;
import laborai.demo.Automobilis;
import laborai.gui.MyException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class DuomGeneravimas {
    private static Kompiuteris[] kompiuteriai;
    private static int pradinisIndeksas = 0, galinisIndeksas = 0;
    private static boolean arPradzia = true;

    public static Kompiuteris[] generuoti(int kiekis){
        Kompiuteris[] kompArray = new Kompiuteris[kiekis];
        for(int i=0;i<kiekis;i++){
            kompArray[i] = new Kompiuteris.Builder().buildRandom();
        }
        return kompArray;
    }
    public static Kompiuteris[] generuotiIrIsmaisyti(int kiekis, double koef) throws Exception {
        return generuotiIrIsmaisyti(kiekis, kiekis, koef);
    }

    public static Kompiuteris[] generuotiIrIsmaisyti(int kiekis, int imtis, double koef) throws Exception {
        kompiuteriai = generuoti(kiekis);
        return ismaisyti(kompiuteriai,imtis, koef);
    }

    public static Kompiuteris[] ismaisyti(Kompiuteris[] kompiuteriuBaze, int kiekis, double koef) throws Exception {
        if(kompiuteriuBaze==null) throw new IllegalArgumentException("KompiuteriuBaze yra null");
        if(kiekis<0) throw new Exception("Kiekis <=0");
        if(kompiuteriuBaze.length < kiekis) throw new Exception("Bazeje nera tiek duomenu");
        if ((koef < 0) || (koef > 1)) throw new Exception("Koeficientas turi buti tarp 0 ir 1");

        int likusiuKiekis = kompiuteriuBaze.length - kiekis;
        int pradziosIndeksas = (int) (likusiuKiekis * koef / 2);

        Kompiuteris[] pradineKompiuteriuImtis = Arrays.copyOfRange(kompiuteriuBaze, pradziosIndeksas, pradziosIndeksas + kiekis);
        Kompiuteris[] likusiKompiuteriuImtis = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(kompiuteriuBaze, 0, pradziosIndeksas)),
                        Arrays.stream(Arrays.copyOfRange(kompiuteriuBaze, pradziosIndeksas + kiekis, kompiuteriuBaze.length)))
                .toArray(Kompiuteris[]::new);

        Collections.shuffle(Arrays.asList(pradineKompiuteriuImtis)
                .subList(0, (int) (pradineKompiuteriuImtis.length * koef)));
        Collections.shuffle(Arrays.asList(likusiKompiuteriuImtis)
                .subList(0, (int) (likusiKompiuteriuImtis.length * koef)));

        DuomGeneravimas.pradinisIndeksas = 0;
        galinisIndeksas = likusiKompiuteriuImtis.length - 1;
        DuomGeneravimas.kompiuteriai = likusiKompiuteriuImtis;
        return pradineKompiuteriuImtis;
    }

    public static Kompiuteris gautiIsBazes() throws Exception {
        if ((galinisIndeksas - pradinisIndeksas) < 0) {
            throw new Exception("Neb4ra duomenu bazeje");
        }
        // Vieną kartą Automobilis imamas iš masyvo pradžios, kitą kartą - iš galo.
        arPradzia = !arPradzia;
        return kompiuteriai[arPradzia ? pradinisIndeksas++ : galinisIndeksas--];
    }
}
