package anna.howtocelebratechristmas;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class Trees_Data {

    private Trees_Data() {
    }

    public static List<Trees_Information> load_tree_items() {
        List<Trees_Information> items = new ArrayList<>();

        Trees_Information treeInformation_1 = new Trees_Information();
        treeInformation_1.name = "A traditional tree ";
        treeInformation_1.description = "You can try a traditional green and red tree";
        treeInformation_1.color = Color.rgb(240, 280, 255);
        treeInformation_1.image = "tree_1";
        treeInformation_1.URL = "http://christmas.365greetings.com/christmas-decorations/most-beautiful-christmas-trees.html";

        items.add(treeInformation_1);

        Trees_Information treeInformation_2 = new Trees_Information();
        treeInformation_2.name = "A white tree ";
        treeInformation_2.description = "Or you can go for a spendid white tree";
        treeInformation_2.color = Color.rgb(111, 11, 111);
        treeInformation_2.image = "tree_2";
        treeInformation_2.URL = "http://christmas.365greetings.com/christmas-decorations/most-beautiful-christmas-trees.html";

        items.add(treeInformation_2);


        Trees_Information treeInformation_3 = new Trees_Information();
        treeInformation_3.name = "A red and gold tree ";
        treeInformation_3.description = "Try gold accent with long ribbons";
        treeInformation_3.color = Color.rgb(13, 55, 125);
        treeInformation_3.image = "tree_3";
        treeInformation_3.URL = "http://christmas.365greetings.com/christmas-decorations/most-beautiful-christmas-trees.html";

        items.add(treeInformation_3);


        Trees_Information treeInformation_4 = new Trees_Information();
        treeInformation_4.name = "A red and white tree ";
        treeInformation_4.description = "Red and white are also good colors";
        treeInformation_4.color = Color.rgb(222, 22, 222);
        treeInformation_4.image = "tree_4";
        treeInformation_4.URL = "http://acreativemomma.blogspot.com/2014/12/red-white-christmas-tree.html";
        items.add(treeInformation_4);

        return items;
    }

}

