import Ülesanded.kasPanka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame  {

    private JComboBox pangaValik;
    private JButton kinnita;

    public GUI () {
        setLayout(new FlowLayout());
        JLabel tekst = new JLabel("Kas soovid asju panka viia ?");
        String panking [] = {"Jah", "Ei"};

        kinnita = new JButton("Kinnita");
        pangaValik = new JComboBox(panking);

        add(tekst);
        add(pangaValik);
        add(kinnita);

        kinnita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kasPanka.kasPanka = (String) pangaValik.getSelectedItem();
                SulgeAken();
            }
        });

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();

    }

    public void SulgeAken(){
        super.dispose();
    }
}
