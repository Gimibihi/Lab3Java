package Lab3Bijeikis;

import Lab3Bijeikis.Gui.GuiSukurimas;
import laborai.gui.swing.Lab3Window;

import java.util.Locale;

public class GuiVykdymui {
    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US);
        Testai.aibesTestas();
        GuiSukurimas.createAndShowGUI();
    }
}
