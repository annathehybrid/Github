package anna.howtocelebratechristmas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tree_Pager_Adapter extends PagerAdapter {

    private List<Tree_Information> mItems = new ArrayList<>();

    public Tree_Pager_Adapter() {



    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.tree_layout_page, container, false);

        TextView textView = (TextView) view.findViewById(R.id.title);


        //textView.setText("Set text " + mItems.get(position).hex);
        String item_1 = mItems.get(position).hex;
        String set_text = "Set text ";
        String combined = set_text + item_1;
        textView.setText(combined);

        TextView textView2 = (TextView) view.findViewById(R.id.Info1);
        //textView2.setText("More info " + mItems.get(position).name);
        textView2.setText("More info " + mItems.get(position).name);

        TextView textView3 = (TextView) view.findViewById(R.id.Info2);
        //textView3.setText("More info " + mItems.get(position).color);
        textView3.setText("More info " + mItems.get(position).color);


        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //holder.img.setImageResource(imageId[position]);


        String meeeeee = mItems.get(position).image;

        Context context = imageView.getContext();
        int id = context.getResources().getIdentifier(meeeeee, "drawable", context.getPackageName());

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

    public Tree_Information getColorItem(int position) {
        return mItems.get(position);
    }

    public void addAll(List<Tree_Information> items) {
        mItems = new ArrayList<>(items);
    }


}
