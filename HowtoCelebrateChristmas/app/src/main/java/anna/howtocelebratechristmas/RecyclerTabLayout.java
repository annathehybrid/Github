package anna.howtocelebratechristmas;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class RecyclerTabLayout extends RecyclerView {

    protected LinearLayoutManager mLinearLayoutManager;
    protected ViewPager mViewPager;
    protected Adapter<?> mAdapter;

    protected float mOldPositionOffset;

    public RecyclerTabLayout(Context context) {
        this(context, null);
    }

    public RecyclerTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWillNotDraw(false);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(mLinearLayoutManager);
    }


    public void setUpWithAdapter(RecyclerTabLayout.Adapter<?> adapter) {
        mAdapter = adapter;
        mViewPager = adapter.getViewPager();
        if (mViewPager.getAdapter() == null) {
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
        }
        mViewPager.addOnPageChangeListener(new ViewPagerOnPageChangeListener(this));
        setAdapter(adapter);
    }


    protected void scrollToTab(int position, float positionOffset) {
        int scrollOffset = 0;

        mLinearLayoutManager.scrollToPositionWithOffset(position, scrollOffset);
        mOldPositionOffset = positionOffset;
    }


    protected static class ViewPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        private final RecyclerTabLayout mRecyclerTabLayout;

        public ViewPagerOnPageChangeListener(RecyclerTabLayout recyclerTabLayout) {
            mRecyclerTabLayout = recyclerTabLayout;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mRecyclerTabLayout.scrollToTab(position, positionOffset);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageSelected(int position) {

        }
    }

    public static abstract class Adapter<T extends RecyclerView.ViewHolder>
            extends RecyclerView.Adapter<T> {

        protected ViewPager mViewPager;

        public Adapter(ViewPager viewPager) {
            mViewPager = viewPager;
        }

        public ViewPager getViewPager() {
            return mViewPager;
        }

    }

}
