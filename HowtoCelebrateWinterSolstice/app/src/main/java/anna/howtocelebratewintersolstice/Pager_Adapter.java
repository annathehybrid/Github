package anna.howtocelebratewintersolstice;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Pager_Adapter extends PagerAdapter {

    public Pager_Adapter() {
        // empty constructor
    }


    private List<Information> array_items = new ArrayList<>();


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_pager, container, false);

        // initialize first textbox
        TextView textbox_name = (TextView) view.findViewById(R.id.textbox_name);
        String item_1 = array_items.get(position).name;
        textbox_name.setText(item_1);


        // initialize second textbox
        TextView textbox_description = (TextView) view.findViewById(R.id.textbox_description);
        String item_2 = array_items.get(position).description;
        textbox_description.setText(item_2);


        // Step 1: initialize the imageview
        ImageView image_winter = (ImageView) view.findViewById(R.id.imageView);
        // Step 2: Get the string id of the image from the information class
        String image_string = array_items.get(position).image;
        // Step 3: get the context of the image
        Context context = image_winter.getContext();
        // Step 4: convert the string of the image to an integer
        int id = context.getResources().getIdentifier(image_string, "drawable", context.getPackageName());
        // Step 5: set int id to the imageview
        image_winter.setImageResource(id);



        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return array_items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public void addAll(List<Information> items) {
        array_items = new ArrayList<>(items);

    }


}
