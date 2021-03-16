package com.stu.otseaclient.activity.mainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import com.stu.com.R;
import com.stu.otseaclient.component.adapter.PostDiscoveryAdapter;
import com.stu.otseaclient.component.listener.SearchClickListener;
import com.stu.otseaclient.controller.PostController;
import com.stu.otseaclient.enumreation.MessageKey;
import com.stu.otseaclient.general.Async;
import com.stu.otseaclient.general.GeneralHandle;
import com.stu.otseaclient.pojo.PostInfo;
import com.stu.otseaclient.util.MessageUtil;

import java.util.LinkedList;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2020/12/16 21:32
 * @Description:
 */
public class DiscoveryFragment extends Fragment {
    private ListView discoveryListView;
    private PostDiscoveryAdapter postDiscoveryAdapter;
    private SearchView searchView;

    private LinkedList<PostInfo> postList;

    {
        //注册刷新discovery页刷新的handle
        GeneralHandle.getInstance().registerHandle(MessageKey.REFRESH_DISCOVERY_LIST, (ctx, msg) -> {
            postDiscoveryAdapter.setItemData(this.postList);
            postDiscoveryAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);

        Async.run(() -> {
            postList = PostController.getInstance().listPost();
            MessageUtil.sendEmptyMessage(MessageKey.REFRESH_DISCOVERY_LIST);
        });
        postDiscoveryAdapter = new PostDiscoveryAdapter(this.postList, R.layout.item_discovery_post);
        discoveryListView = view.findViewById(R.id.discover_list_view);
        discoveryListView.setAdapter(postDiscoveryAdapter);

        //设置搜索框
        searchView = view.findViewById(R.id.search_click);
        searchView.setOnQueryTextListener(new SearchClickListener());

        return view;
    }


}
