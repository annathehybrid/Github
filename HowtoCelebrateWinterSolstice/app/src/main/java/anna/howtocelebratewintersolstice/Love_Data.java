package anna.howtocelebratewintersolstice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 12/16/2015.
 */
public class Love_Data {

    private Love_Data() {
        // empty constructor
    }

    public static List<Information> load_all_items() {

        List<Information> items = new ArrayList<>();

        Information item_information_1 = new Information();
        item_information_1.name = "Spend time with loved ones";
        item_information_1.description = "The winter solstice is a time to renew our connection " +
                "with other people. \n" +
                "This is a good time to do " +
                "be aware of other humans, " +
                "perform acts of goodwill, " +
                "and special rituals.";


        item_information_1.image = "love_1";
        //http://heyweddinglady.com/longest-night-winter-solstice-wedding-inspiration/
        items.add(item_information_1);




        Information item_information_2 = new Information();
        item_information_2.name = "Hope";
        item_information_2.description = "Winter solstice is a time to still ourselves inside" +
                " and behold the glory of the cosmos. Solstice also gives us the opportunity" +
                " to ask whether or not we are still on the correct course.\n" +
                "\n" +
                "Friends gather to celebrate the longest night. Something in " +
                "us needs to know that at the end of the longest night, there will be light.\n";
        item_information_2.image = "love_2";
        items.add(item_information_2);


        // http://www.huffingtonpost.com/t-thorn-coyle/winter-solstice-honoring-_b_2340507.html
        return items;
    }



}
