package anna.howtocelebratewintersolstice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 12/17/2015.
 */
public class Yule_Data {


    private Yule_Data() {
        // empty constructor
    }

    public static List<Information> load_all_items() {

        List<Information> items = new ArrayList<>();

        Information item_information_1 = new Information();
        item_information_1.name = "Yule ";
        item_information_1.description = "The word “yule” may derive from an" +
                " Anglo-Saxon term that means “wheel.”\n" +
                "\n" +
                "In pagan Scandinavia, village people sat around bonfires " +
                "of burning yule logs throughout the night while drinking mead " +
                "and listening to the stories of minstrel-poets.";

        item_information_1.image = "yule_1";
        // http://www.motherearthliving.com/health-and-wellness/simply-solstice-celebrate-winter-with-new-and-old-traditions.aspx
        items.add(item_information_1);


        Information item_information_2 = new Information();
        item_information_2.name = "Feast of Juul ";
        item_information_2.description = "In pagan Scandinavia, the Feast of Juul, or Yule, " +
                "lasted for 12 days.\n" +
                "The Scandinavians celebrated " +
                "the rebirth of the sun god. This gave rise to the custom of burning a Yule log " +
                "in honor of the Scandinavian god Thor.";


        item_information_2.image = "yule_2";
        // http://akmaya.ru/post345707580/
        items.add(item_information_2);

        Information item_information_2b = new Information();
        item_information_2b.name = "Saint Lucy ";
        item_information_2b.description = "Scandinavians commemorate Saint Lucy" +
                " on December 13, which is equivalent to the Winter Solstice " +
                "in the Julian calendar. This saint's name refers to light " +
                "(the name Lucy is derived from the Latin word for light, lux). \n" +
                "\nIn popular celebrations today, young girls dress up as Saint" +
                " Lucy in honor of the holiday.\n";

        item_information_2b.image = "yule_3";
        // http://www.patheos.com/Resources/Additional-Resources/Winter-Solstice
        items.add(item_information_2b);



        Information item_information_3 = new Information();
        item_information_3.name = "Saturnalia ";
        item_information_3.description = "In ancient Rome, the winter solstice was celebrated " +
                "at the Feast of Saturnalia, to honor Saturn, the god of agricultural bounty.\n" +
                "\n" +
                "Lasting about a week, Saturnalia was characterized by feasting, debauchery " +
                "and gift-giving.";

        item_information_3.image = "yule_4";
        // http://www.huffingtonpost.com/entry/winter-solstice-2015_5671b120e4b0688701dbdc4c
        items.add(item_information_3);


        Information item_information_4 = new Information();
        item_information_4.name = "Stonehenge ";
        item_information_4.description = "In the ancient ruins of Stonehenge, England, " +
                "thousands of druids and pagans gather there to chant, dance and sing.\n\n" +
                "This continues all night, until the dawn. " +
                "Then everyone stops to see the spectacular sunrise.";

        item_information_4.image = "yule_5";
        // http://www.huffingtonpost.com/entry/winter-solstice-2015_5671b120e4b0688701dbdc4c
        items.add(item_information_4);


        Information item_information_5 = new Information();
        item_information_5.name = "Yule in Western Europe ";
        item_information_5.description = "In England, Germany, and France, the" +
                " Yule log was burned until nothing but ash remained.\n" +
                "\n" +
                "The ashes were then collected and either strewn on " +
                "the fields as fertilizer every night until Twelfth " +
                "Night or kept as a charm and or as medicine.";

        item_information_5.image = "yule_6";
        //http://www.timeanddate.com/calendar/december-solstice-customs.html
        items.add(item_information_5);


        Information item_information_6 = new Information();
        item_information_6.name = "Gody ";
        item_information_6.description = "In Poland, the ancient December solstice " +
                "observance involved people showing forgiveness and sharing food.\n";

        item_information_6.image = "yule_7";
        //http://www.timeanddate.com/calendar/december-solstice-customs.html
        items.add(item_information_6);


        Information item_information_7 = new Information();
        item_information_7.name = "Meán Geimhridh ";
        item_information_7.description = "In ancient Ireland, " +
                "Celts celebrated Meán Geimhridh during the Winter Solstice.\n" +
                "\n" +
                "Each day between December 19 and 23, rays from the sunrise" +
                " would shine through a sacred hallway and room built at just" +
                " the right angles to capture the light. \n";

        item_information_7.image = "yule_8";
        //http://www.patheos.com/Resources/Additional-Resources/Winter-Solstice
        items.add(item_information_7);



        return items;
    }



}
