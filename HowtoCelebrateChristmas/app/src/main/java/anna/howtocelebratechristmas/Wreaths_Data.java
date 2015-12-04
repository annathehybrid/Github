package anna.howtocelebratechristmas;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 12/4/2015.
 */
public class Wreaths_Data {

    private Wreaths_Data() {
    }

    public static List<Wreaths_Information> load_wreath_items() {
        List<Wreaths_Information> items = new ArrayList<>();

        Wreaths_Information wreathInformation_1 = new Wreaths_Information();
        wreathInformation_1.name = "A“Gourmet” Christmas Wreath";
        wreathInformation_1.description = "\t1)  First you want to lay down a base of pine branches.  " +
                "Lay them in the same direction, counter-clockwise, so the ends of the branches are on the right, " +
                "and the soft tops of the branches will be laying towards the left.  \n" +
                "(Of course you can do it clockwise if you prefer, but for whatever reason counter-clockwise" +
                " just looks and feels better to me).  \n" +
                "Secure them to the straw wreath by wrapping some florist wire around them, strapping them" +
                " down to the base. \n" +
                "\t2)  Continue adding more branches all around the straw wreath form. \n" +
                "\t3)  Now you’ll want to fill in the sides, sliding the ends of the branches in " +
                "through the existing wire loops and through the other branches. " +
                " Fill in any gaps with smaller twigs until you have a nice, full wreath.  " +
                "It will look a little disheveled at this point but we’ll address that next.  \n" +
                "\t4)  Wrap a little more wire around just to strap down any large branches that are " +
                "sticking out, looping the wire under some of the smaller branches so " +
                "you don’t end up with a completely matted, wired down wreath.  " +
                "\nThe green florist wire is hard to see anyway, but you want to make it as invisible as possible.\n" +
                "Okay, now your basic pine wreath foundation is complete!\n";
        wreathInformation_1.color = Color.rgb(240, 280, 255);
        wreathInformation_1.image = "wreath_1";
        wreathInformation_1.URL = "http://www.daringgourmet.com/2014/11/29/how-to-make-a-homemade-christmas-wreath-and-advent-wreath/";

        items.add(wreathInformation_1);

        Wreaths_Information wreathInformation_2 = new Wreaths_Information();
        wreathInformation_2.name = "Christmas Mesh Wreath ";
        wreathInformation_2.description = "I really do think the Christmas mesh wreaths " +
                "are the easiest to make. The reason is because there is a lot of " +
                "greenery to attach things too. \n" +
                "\tAlso, all of the greenery is wired" +
                " so you can just wrap the greenery around the mesh and ribbon to secure it. \n" +
                "\tI always start adding the mesh at the bottom of the wreath. \n" +
                "The mesh is really wide, so you will need to scrunch!\n";
        wreathInformation_2.color = Color.rgb(111, 11, 111);
        wreathInformation_2.image = "wreath_2";
        wreathInformation_2.URL = "http://kristenscreationsonline.blogspot.com/2011/11/christmas-mesh-wreath-tutorial.html";
        items.add(wreathInformation_2);


        Wreaths_Information wreathInformation_3 = new Wreaths_Information();
        wreathInformation_3.name = "Silver and white snowflake themed wreath  ";
        wreathInformation_3.description = "Silver and gold wreaths are also very Christmas-y";
        wreathInformation_3.color = Color.rgb(13, 55, 125);
        wreathInformation_3.image = "wreath_3";
        wreathInformation_3.URL = "http://www.hometalk.com/721508/christmas-wreaths-part-2";

        items.add(wreathInformation_3);


        Wreaths_Information wreathInformation_4 = new Wreaths_Information();
        wreathInformation_4.name = "A Wreath Chandelier ";
        wreathInformation_4.description = "How To Make A Wreath Chandelier.\n" +
                "Martha shows you how to make a festive monogram " +
                "wreath that will add a personal touch to your front door\n";
        wreathInformation_4.color = Color.rgb(222, 22, 222);
        wreathInformation_4.image = "wreath_4";
        wreathInformation_4.URL = "http://www.marthastewart.com/974724/home-how-series#1134465";
        items.add(wreathInformation_4);

        return items;
    }

}
