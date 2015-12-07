package anna.howtocelebratechristmas;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Cookies_Adapter extends RecyclerTabLayout.Adapter<Cookies_Adapter.ViewHolder> {

    private Cookies_Pager_Adapter mAdapater;

    public Cookies_Adapter(ViewPager viewPager) {
        super(viewPager);
        mAdapater = (Cookies_Pager_Adapter) mViewPager.getAdapter();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tab_cookies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Information information = mAdapater.getItem(position);
        holder.title.setText(information.name);
        holder.color.setBackgroundColor(information.color);

        SpannableString name = new SpannableString(information.name);
        holder.title.setText(name);
    }

    @Override
    public int getItemCount() {
        return mAdapater.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View color;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            color = itemView.findViewById(R.id.color);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getViewPager().setCurrentItem(getAdapterPosition());
                }
            });
        }
    }
}