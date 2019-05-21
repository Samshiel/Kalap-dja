package Ülesanded;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.path.PredefinedPath;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Pank extends Task {

    Position[] edgevillePanka = {
            new Position(3108, 3433, 0),
            new Position(3100, 3437, 0),
            new Position(3097, 3444, 0),
            new Position(3094, 3450, 0),
            new Position(3091, 3455, 0),
            new Position(3087, 3461, 0),
            new Position(3089, 3465, 0),
            new Position(3099, 3466, 0),
            new Position(3099, 3473, 0),
            new Position(3099, 3480, 0),
            new Position(3097, 3485, 0),
            new Position(3089, 3486, 0),
            new Position(3089, 3490, 0),
            new Position(3093, 3489, 0)
    };

    Position[] edgevillePangasTagasi = {
            new Position(3093, 3489, 0),
            new Position(3089, 3490, 0),
            new Position(3089, 3486, 0),
            new Position(3097, 3485, 0),
            new Position(3099, 3480, 0),
            new Position(3099, 3473, 0),
            new Position(3099, 3466, 0),
            new Position(3089, 3465, 0),
            new Position(3087, 3461, 0),
            new Position(3091, 3455, 0),
            new Position(3094, 3450, 0),
            new Position(3097, 3444, 0),
            new Position(3100, 3437, 0),
            new Position(3108, 3433, 0),
    };

    Area fishingAreaBarb = Area.rectangular(new Position(3101,3436), new Position(3112, 3422));

    @Override
    public boolean validate() {
        return Inventory.isFull() || !fishingAreaBarb.contains(Players.getLocal().getPosition()) && kasPanka.kasPanka.equals("Jah");
    }

    @Override
    public int execute() {
        if(edgevillePanka[edgevillePanka.length -1].distance() > 8 && Inventory.isFull()) {
            Log.info("Liigun panka");
            PredefinedPath.build(edgevillePanka).walk();
            Time.sleep(2500);
        }
        if (edgevillePanka[edgevillePanka.length - 1].distance() <= 8 && Inventory.isFull()) {
            if(Bank.isOpen()) {
                Bank.depositAllExcept(309,314);
                Time.sleep(1000);
                Bank.close();

            }
            else {
                Bank.open();
                Time.sleepUntil(() -> Bank.isOpen(), 1000);
            }
        }
        if (Inventory.containsOnly(309, 314)) {
            Log.info("Hakkan tagasi minema");
            if(!new PüüaKala().getFishingAreaBarb().contains(Players.getLocal().getPosition())) {
                PredefinedPath.build(edgevillePangasTagasi).walk();
                Time.sleep(2000);
            }
        }
        return 2000;
    }
}
