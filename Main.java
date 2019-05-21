import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import Ülesanded.Pank;
import Ülesanded.PüüaKala;
import Ülesanded.dropiKraam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ScriptMeta(developer = "Sander ja Artur", name = "Kalapüüdija" , desc = "Püüab kala" )
public class Main extends Script implements RenderListener {

    public static String kasPanka;

    List<Task> ülesanded = new ArrayList<Task>();
    private int algusXp;
    StopWatch kell;
    private int level;

    @Override

    //Alguses lisab ülseanded Listi ja salvestab vajalikud muutujad info kuvamiseks
    public void onStart() {
        GUI GUI = new GUI();
        GUI.setVisible(true);
        ülesanded.add(new dropiKraam());
        ülesanded.add(new Pank());
        ülesanded.add(new PüüaKala());
        algusXp = Skills.getExperience(Skill.FISHING);
        kell = StopWatch.start();

    }

    //Loop mida ta koguaeg täidab
    @Override
    public int loop() {
        level = Skills.getCurrentLevel(Skill.FISHING);
        for(Task ülesanne: ülesanded) {
            if(ülesanne.validate()) {
                ülesanne.execute();
            }
        }
        return 300;
    }

    //Ekraanile info kuvamiseks
    @Override
    public void notify(RenderEvent renderEvent) {
        int hetkeXp = Skills.getExperience(Skill.FISHING);
        int saadudXp = hetkeXp - algusXp;
        Graphics g = renderEvent.getSource();
        Graphics g2 = (Graphics2D) g;
        ((Graphics2D) g2).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = 35;
        int x = 10;
        g2.setColor(Color.BLUE);
        g2.drawString("Sandri ja Arturi kalamasin" , x, y);
        g2.drawString("Hetke level: " + Integer.toString(level), x, y += 20);
        g2.drawString("Saadud xp: " + Integer.toString(saadudXp), x, y += 20);
        g2.drawString("Masin on teinud tööd: " + kell.toElapsedString(), x, y += 20);
    }
}
