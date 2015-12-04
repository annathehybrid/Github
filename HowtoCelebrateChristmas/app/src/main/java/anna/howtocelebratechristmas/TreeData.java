package anna.howtocelebratechristmas;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class TreeData {

    private TreeData() {
    }

    public static List<Tree_Information> loadDemoColorItems() {
        List<Tree_Information> items = new ArrayList<>();

        Tree_Information treeInformation = new Tree_Information();
        treeInformation.name = "Alice Blue";
        treeInformation.hex = "A real tree";
        treeInformation.color = Color.rgb(240, 280, 255);
        treeInformation.image = "ic_action_delete";

        items.add(treeInformation);

        Tree_Information treeInformation2 = new Tree_Information();
        treeInformation2.name = "Me too Blue";
        treeInformation2.hex = "A fake tree";
        treeInformation2.color = Color.rgb(111, 11, 111);
        treeInformation2.image = "ic_action_cut";
        items.add(treeInformation2);


        Tree_Information treeInformation3 = new Tree_Information();
        treeInformation3.name = "Me too Blue";
        treeInformation3.hex = "A fake tree";
        treeInformation3.color = Color.rgb(13, 55, 125);
        treeInformation3.image = "ic_action_copy";
        items.add(treeInformation3);


        Tree_Information treeInformation4 = new Tree_Information();
        treeInformation4.name = "Green";
        treeInformation4.hex = "A fake tree";
        treeInformation4.color = Color.rgb(222, 22, 222);
        treeInformation4.image = "ic_action_call";
        items.add(treeInformation4);

        return items;
    }

}

