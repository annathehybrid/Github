package anna.howtocelebratewintersolstice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 12/15/2015.
 */
public class Contemplate_Data {

    private Contemplate_Data() {
        // empty constructor
    }

    public static List<Information> load_all_items() {

        List<Information> items = new ArrayList<>();

        Information item_information_1 = new Information();
        item_information_1.name = "Winter solstice definition ";
        item_information_1.description = "The term \"solstice\" " +
                "derives from the Latin words sol (sun) and sistere (to stand). This means " +
                "that the sun as reached its northernmost ecliptic and appears to stand still.\n" +
                "\n" +
                "Winter Solstice occurs on December 21 or 22. It is the shortest day of the " +
                "year, when the least amount of sunlight reaches the northern hemisphere.";

        item_information_1.image = "contemplate_1";
        //http://www.patheos.com/Resources/Additional-Resources/Winter-Solstice
        items.add(item_information_1);

        //

        Information item_information_2 = new Information();
        item_information_2.name = "Alertness ";
        item_information_2.description = "In Celebrate the Solstice, Richard Heinberg writes " +
                "that “wisdom consists in knowing one’s place in any given cycle, and " +
                "what kinds of action (or restraint of action) are appropriate for that phase.”\n" +
                "\n" +
                "Attuning our senses to the subtle changes and cycles of the seasons might " +
                "help us attune more lovingly to the subtle changes and cycles in ourselves. \n" +
                "\n";

        item_information_2.image = "contemplate_2";
        //http://www.amazon.co.uk/Celebrate-Solstice-Honoring-Seasonal-Festival-ebook/dp/B00SKF0ZCU
        items.add(item_information_2);


        Information item_information_3 = new Information();
        item_information_3.name = "Quietness ";
        item_information_3.description = "Spend more time listening, watching, and honoring the slower, quieter rhythm of the season.\n" +
                "\n" +
                "\t find a trail you can walk or a field you can lie down in\n" +
                "\t find a hillside or mountain perch that provides the perfect view, " +
                "or even the roof of your apartment building or a quiet place on the edge of" +
                " your yard\n" +
                "\t watching the sun rise or set from your little patch of the world. Write " +
                "a poem\n" +
                "\t Make a list of loving wishes for friends, family, coworkers, even people " +
                "you don’t know that well\n" +
                "\t Build a shrine of nature’s found objects\n" +
                "\t Light a candle.\n" +
                "\t Reflect on your aspirations for the coming months\n" +
                "\t Sing an original song.\n";

        item_information_3.image = "contemplate_3";
        //http://akmaya.ru/post345707580/
        items.add(item_information_3);


        return items;
    }


}
