package anna.howtocelebratechristmas;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Beverages_Pager_Adapter extends PagerAdapter {

    private List<Information> mItems = new ArrayList<>();

    public Beverages_Pager_Adapter() {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_pager_beverages, container, false);

        TextView textView = (TextView) view.findViewById(R.id.title);


        String item_1 = mItems.get(position).name;
        textView.setText(item_1);

        TextView textView2 = (TextView) view.findViewById(R.id.Info1);
        textView2.setText(mItems.get(position).description);


        TextView textView3 = (TextView) view.findViewById(R.id.Info2);
        String item_2 = mItems.get(position).URL;
        textView3.setText(item_2);

        textView3.setClickable(true);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        // initialize the image
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        // get the string id of the image from the information class
        String meeeeee = mItems.get(position).image;
        // get the context of the image
        Context context = imageView.getContext();
        // convert the string if of the image to an integar
        int id = context.getResources().getIdentifier(meeeeee, "drawable", context.getPackageName());
        // set int id to the imageview
        imageView.setImageResource(id);

        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public String getPageTitle(int position) {
        return mItems.get(position).name;
    }

    public Information getItem(int position) {
        return mItems.get(position);
    }

    public void addAll(List<Information> items) {
        mItems = new ArrayList<>(items);
    }


}
