package com.stu.otseaclient.activity.lessonPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stu.com.R;
import com.stu.otseaclient.component.adapter.LessonDirListAdapter;
import com.stu.otseaclient.pojo.LessonDirNode;
import com.stu.otseaclient.pojo.LessonInfo;
import com.stu.otseaclient.util.JsonUtil;
import com.video.player.lib.view.VideoPlayerTrackView;
import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/10 17:28
 * @Description:
 */
public class LessonDirFragment extends LessonFragment {
    private NiceSpinner spinner;
    private LessonDirListAdapter listAdapter;
    private ListView listView;
    private List<LessonDirNode> lessonDirNode;


    public LessonDirFragment(VideoPlayerTrackView mVideoPlayer, LessonInfo lessonInfo) {
        super(mVideoPlayer, lessonInfo);

        String dirStr = lessonInfo.getLesson().getFolderStructure();
        ArrayNode arrNode = (ArrayNode) JsonUtil.readTree(dirStr);
        lessonDirNode = parseNodes(arrNode);

    }

    private List<LessonDirNode> parseNodes(ArrayNode arr) {
        List<LessonDirNode> list = new ArrayList<>();
        for (JsonNode node : arr) {
            LessonDirNode dirNode = new LessonDirNode();
            ObjectNode o = (ObjectNode) node;

            dirNode.setName(o.get("name").asText());

            JsonNode linkNode = o.get("link");
            if (linkNode != null)
                dirNode.setLink(linkNode.asInt());

            JsonNode sonNode = o.get("son");
            if (sonNode != null)
                dirNode.setSon(parseNodes((ArrayNode) sonNode));

            list.add(dirNode);
        }
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson_dir, container, false);

        listAdapter = new LessonDirListAdapter(this.mVideoPlayer, lessonDirNode, R.layout.item_lesson_dir_spinner);
        listView = view.findViewById(R.id.lesson_dir_list_view);
        listView.setAdapter(listAdapter);
        

        return view;
    }
}
