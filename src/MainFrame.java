import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame {
    int dlugoscHasla = 8;

    //    Tytuł: MainFrame
//    Opis: konstruktor głównego okna
//    Przyjmowane argumenty: brak
//    Zwracana wartość: brak
//    Autor: Marcin Kulesza
    MainFrame(){
        setTitle("Generator Haseł");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().setBorder(
                BorderFactory.createEmptyBorder(0, 20, 20, 20)
        );

        JLabel naglowek = new JLabel("<html><h1>Generator haseł</h1><h3>Utworz silne i bezpieczne hasło</h3></html>", 0);
        add(naglowek, BorderLayout.NORTH);

        JPanel formulaz = new JPanel(new GridLayout(5, 1));


        JPanel dlugoscHaslaPanel = new JPanel();
        dlugoscHaslaPanel.setLayout(new GridLayout(1, 2));
        JLabel dlugoscHaslaLabel = new JLabel("Długość hasła: ");
        JFormattedTextField dlugoscHaslaPole = new JFormattedTextField(dlugoscHasla);
        dlugoscHaslaPole.addActionListener(e -> {
            try{
                dlugoscHasla = Integer.parseInt(dlugoscHaslaPole.getText());
            } catch (NumberFormatException ex) {
                dlugoscHaslaPole.setText(String.valueOf(dlugoscHasla));
            }
        });
        dlugoscHaslaPanel.add(dlugoscHaslaLabel);
        dlugoscHaslaPanel.add(dlugoscHaslaPole);
        formulaz.add(dlugoscHaslaPanel);

        JCheckBox wielkieLitery = new JCheckBox("Wielkie litery (A-Z)");
        formulaz.add(wielkieLitery);

        JCheckBox maleLitery = new JCheckBox("Małe litery (a-z)");
        formulaz.add(maleLitery);

        JCheckBox cyfry = new JCheckBox("Cyfry (0-9)");
        formulaz.add(cyfry);

        JCheckBox znakiSpecjalne = new JCheckBox("Znaki specjalne (!@#...");
        formulaz.add(znakiSpecjalne);

        add(formulaz, BorderLayout.WEST);


        JPanel wygenerowaneHasloPanel = new JPanel();
        wygenerowaneHasloPanel.setLayout(new GridLayout(2, 1));
        JLabel wygenerowaneHasloLabel = new JLabel("Wygenerowane hasło:");
        JTextField wygenerowaneHasloOutput = new JTextField("");
        wygenerowaneHasloOutput.setEditable(false);
        wygenerowaneHasloOutput.setBackground(new Color(232, 245, 236));
        wygenerowaneHasloOutput.setForeground(new Color(35, 45, 55));
        wygenerowaneHasloOutput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 215, 199), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        wygenerowaneHasloPanel.add(wygenerowaneHasloLabel);
        wygenerowaneHasloPanel.add(wygenerowaneHasloOutput);
        add(wygenerowaneHasloPanel, BorderLayout.SOUTH);

        JPanel panelPrzycisku = new JPanel();
        panelPrzycisku.setLayout(new FlowLayout());
        JButton generujBtn = new JButton("<html><backgroung color='#1212dd'>Generuj</html>");
        generujBtn.addActionListener(e -> wygenerowaneHasloOutput.setText(generuj(Integer.parseInt(dlugoscHaslaPole.getText()), wielkieLitery.isSelected(), maleLitery.isSelected(), cyfry.isSelected(), znakiSpecjalne.isSelected())));
        generujBtn.setVerticalAlignment(0);
        generujBtn.setForeground(Color.WHITE);
        generujBtn.setBackground(new Color(43, 115, 220));
        generujBtn.setBorder(new EmptyBorder(12, 28, 12, 28));

        panelPrzycisku.add(generujBtn);
        add(panelPrzycisku, BorderLayout.EAST);

        setVisible(true);
    }

    //    Tytuł: generuj
//    Opis: generuje hasło
//    Przyjmowane argumenty: długość hasła, czy pole wielkie litery jest zaznaczone, czy pole małe litery jest zaznaczone, czy pole cyfry jest zaznaczone, czy pole znaki specjalne jest zaznaczone
//    Zwracana wartość: Ciąg z wygenerowanym hasłem
//    Autor: Marcin Kulesza
    String generuj(int dlugoscHasla, boolean wielkieChecked, boolean maleChecked, boolean cyfryChecked, boolean specjalneeChecked){
        ArrayList<String> znaki = new ArrayList<String>();
        String wielkieLitery = "ABCDEFGHIJKLMNOPRSTQUWXYZ";
        String maleLitery = "abcdefghijklmnoprstquwyz";
        String cyfry = "1234567890";
        String znakiSpecjalne = "!@#$%^&*";
        if (wielkieChecked){znaki.add(wielkieLitery);}
        if (maleChecked){znaki.add(maleLitery);}
        if (cyfryChecked){znaki.add(cyfry);}
        if(specjalneeChecked){znaki.add(znakiSpecjalne);}

        StringBuilder haslo = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i<dlugoscHasla; i++){
            int typZnaku = random.nextInt(znaki.size());
            haslo.append(znaki.get(typZnaku).charAt(random.nextInt(znaki.get(typZnaku).length())));
        }
        return String.valueOf(haslo);
    }
}
 