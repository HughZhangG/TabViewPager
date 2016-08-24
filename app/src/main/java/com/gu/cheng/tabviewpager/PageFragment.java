package com.gu.cheng.tabviewpager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.Unbinder;

public class PageFragment extends Fragment {
 
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private Unbinder unbinder;

//    @BindView(R.id.id_gc_swipe_recycler)
    SwipeMenuRecyclerView mSwipeMenuRecyclerView;

//    @BindView(R.id.id_gc_x_recycler)
    XRecyclerView mXRecyclerView;
    private List<Object> mDataList;
    private Context mContext;
    private MenuAdapter mMenuAdapter;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }
 
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = 0;
        if (mPage == 0)
            layoutId = R.layout.fragment_page_swiperecyclerview;
        else
            layoutId = R.layout.fragment_page;
        View view = inflater.inflate(layoutId, container, false);
//        TextView textView = (TextView) view;
//        textView.setText("Fragment #" + mPage);

//        unbinder = ButterKnife.bind(view);

        initData();

        init(view);

        return view;
    }

    private void init(View view) {
        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.id_gc_swipe_recycler);
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.id_gc_x_recycler);

        if (mSwipeMenuRecyclerView != null){

            mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            mSwipeMenuRecyclerView.addItemDecoration(new ListViewDecoration(mContext));


            // 设置菜单创建器。
            mSwipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
            // 设置菜单Item点击监听。
            mSwipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);

            mSwipeMenuRecyclerView.openLeftMenu(0);
            mSwipeMenuRecyclerView.openRightMenu(0);


            mMenuAdapter = new MenuAdapter(mDataList);
            mSwipeMenuRecyclerView.setAdapter(mMenuAdapter);
//            mMenuAdapter.setData(mDataList);
        }else if (mXRecyclerView != null){
            mXRecyclerView.setLoadingMoreEnabled(true);
            mXRecyclerView.setPullRefreshEnabled(true);

            mXRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

            mXRecyclerView.addItemDecoration(new ListViewDecoration(mContext));

            XRecyclerAdapter mRecyclerAdapter = new XRecyclerAdapter(mContext);

            mXRecyclerView.setAdapter(mRecyclerAdapter);

            mRecyclerAdapter.setData(mDataList);
        }
    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

            int size = getResources().getDimensionPixelSize(R.dimen.menu_item_size);
            SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                    .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                    .setImage(R.mipmap.ic_action_add) // 图标。
                    .setText("删除") // 文字。
                    .setWidth(80) // 宽度。
                    .setHeight(80); // 高度。
            swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
//                    .setBackgroundDrawable(R.drawable.selector_red)
                    .setImage(R.mipmap.ic_launcher) // 图标。
                    .setBackgroundColor(Color.RED)
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(size)
                    .setHeight(size);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };


    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }

            // TODO 这里特别注意，如果这里删除了Item，不要调用Adapter.notifyItemRemoved(position)，因为RecyclerView有个bug，调用这个方法后，后面的position会错误！
            // TODO 删除Item后调用Adapter.notifyDataSetChanged()，下面是事例代码：
            if (menuPosition == 0) {// 删除按钮被点击。
                mDataList.remove(adapterPosition);
                mMenuAdapter.notifyDataSetChanged();
            }
        }
    };

    private void initData() {

        mDataList = new ArrayList<Object>();
        for (int i = 0; i < 50; i++) {
            mDataList.add("Fragment #" + mPage +"---"+i);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbinder.unbind();
    }
 
}