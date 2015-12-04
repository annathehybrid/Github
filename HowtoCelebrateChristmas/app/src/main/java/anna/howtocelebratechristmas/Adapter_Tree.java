package anna.howtocelebratechristmas;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter_Tree extends RecyclerTabLayout.Adapter<Adapter_Tree.ViewHolder> {

    private Tree_Pager_Adapter mAdapater;

    public Adapter_Tree(ViewPager viewPager) {
        super(viewPager);
        mAdapater = (Tree_Pager_Adapter) mViewPager.getAdapter();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tab_tree, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tree_Information treeInformation = mAdapater.getColorItem(position);
        holder.title.setText(treeInformation.name);
        holder.color.setBackgroundColor(treeInformation.color);

        SpannableString name = new SpannableString(treeInformation.name);
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
