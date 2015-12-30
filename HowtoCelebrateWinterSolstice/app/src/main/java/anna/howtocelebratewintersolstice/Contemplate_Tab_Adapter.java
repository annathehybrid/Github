package anna.howtocelebratewintersolstice;

import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shinichi Nishimura on 2015/07/22.
 */
public class Contemplate_Tab_Adapter extends RecyclerTabLayout.Adapter<Contemplate_Tab_Adapter.ViewHolder> {

    private Contemplate_Pager_Adapter mAdapater;

    public Contemplate_Tab_Adapter(ViewPager viewPager) {
        super(viewPager);
        mAdapater = (Contemplate_Pager_Adapter) mViewPager.getAdapter();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // get the name item from the Information class
        // create an instance
        Information information = mAdapater.get_item(position);
        SpannableString name = new SpannableString(information.name);
        holder.title.setText(name);
    }

    @Override
    public int getItemCount() {
        return mAdapater.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getViewPager().setCurrentItem(getAdapterPosition());
                }
            });
        }
    }
}