package Ülesanded;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class dropiKraam extends Task {

    @Override
    public boolean validate() {
        return Inventory.isFull() && kasPanka.kasPanka.equals("Ei");
    }

    @Override
    //rakendab meetodit dropOrder
    public int execute() {
        int[] indexes = dropOrder();
        for (int index : indexes) {
            Item fish = Inventory.getItemAt(index);
            if (fish != null && fish.getName().startsWith("Raw")) {
                fish.interact("Drop");
                Time.sleep(suvaline(250, 624));
            }
        }
        return 300;
    }
    //Custom made 3 listi, mis viskavad kalad kotist maha. Selle jaoks on 3 erinevat listi, mis valitakse randomi abil
    private int[] dropOrder() {
        int[] drop1 = new int[]{0,4, 8, 12, 16, 20, 24, 25, 21, 17, 13, 9, 5, 1, 2, 6, 10, 14, 18, 22, 26, 27, 23, 19, 15, 11, 7, 3};//esimene inventory list
        int[] drop2 = new int[]{0,1, 4, 5, 8, 9, 12, 13, 16, 17, 20, 21, 24, 25, 26, 27, 22, 23, 18, 19, 14, 15, 10, 11, 6, 7, 2, 3};//teine
        int[] drop3 = new int[]{0,1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 15, 14, 13, 12, 16, 17, 18, 19, 23, 22, 21, 20, 24, 25, 26, 27};//kolmas

        switch (suvaline(1, 3)) {
            case 1:
                Log.info("Drop 1");
                return drop1;
            case 2:
                Log.info("Drop 2");
                return drop2;
            case 3:
                Log.info("Drop 3");
                return drop3;
        }
        return dropOrder();
    }
    //loob suvalise numbri
    public static int suvaline(int a, int b) {
        return (int) (Math.round(Math.random() * (b - a) + a));
    }

}
