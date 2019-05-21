package Ülesanded;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class PüüaKala extends Task {

    //Lisan mängus olevad kalastuvarustuse ja sööda.
    private int [] kalsatusVarustus = {303, 307, 309};
    private int [] sööt = {313, 314};

    Area fishingAreaBarb = Area.rectangular(new Position(3101,3436), new Position(3112, 3422));

    @Override
    //Enne execute() minemist kontrollib, et ega mängija juba ei tegele millegagi.
    public boolean validate() {
        return !Players.getLocal().isAnimating() && !Inventory.isFull() && fishingAreaBarb.contains(Players.getLocal().getPosition());
    }

    //Kui validate() returnib True, siis alustab kalapüügiga.
    @Override
    public int execute() {
        //Võtab kalastuskohad mängus mis on eraldi id'ga kirjas.

        //Kui kalastuskoht on null, ehk kui pole kalastuskoht juures ja mängija on animatsioonis, siis ta ei lähe siia if lausesse.
        if(!Players.getLocal().isAnimating()) {

            //Kalastuskohad
            Npc kalastusKohtLumb = Npcs.getNearest(1530);
            Npc kalastusKohtBarb = Npcs.getNearest(1526);

            //Kontrollib, kas on vastav varustus et püüda krevette või anšoovise .
            if(Inventory.contains(kalsatusVarustus[0])){
                Log.info("Hakkan püüdma fishing netinga");
                //Suhtleb kalastuskohaga valides tegevuse Net.
                kalastusKohtLumb.interact("Net");
                Time.sleep(1250, 3000);
                //Magab niikaua, kuni mängija on lõpetanud animatsiooni.
                Time.sleepUntil(() -> Players.getLocal().isAnimating(), 100);
            }
            //Loogika on kõigil sama nagu ülemisel.
            else if(Inventory.contains(kalsatusVarustus[1], sööt[0]) && Skills.getCurrentLevel(Skill.FISHING) >= 5) {
                Log.info("Hakkan püüdma fishing rodiga kala");
                kalastusKohtLumb.interact("Bait");
                Time.sleep(1250, 3000);
                Time.sleepUntil(() -> Players.getLocal().isAnimating(), 100);
            }
            else if(Inventory.contains(kalsatusVarustus[2], sööt[1]) && Skills.getCurrentLevel(Skill.FISHING) >= 20) {
                Log.info("Hakkan püüdma fly fishing rodiga kala");
                kalastusKohtBarb.interact("Lure");
                Time.sleep(1250, 3000);
                Time.sleepUntil(() -> Players.getLocal().isAnimating(), 100);
            }
        }
        //Intervall, kui kiiresti ta seda execute() kasutab.
        return 200;
    }

    public Area getFishingAreaBarb() {
        return fishingAreaBarb;
    }
}
