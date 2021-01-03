package json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonlibTest {

    @Test
    public void List2JsonArray() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray);
    }

    @Test
    public void test1() {
        Object mediaIds = "{id:[1,2,3,4]}";
        Object excludedMediaIds = "{id:[2,3,6]}";

        JSONObject mediaJsonObject = JSONObject.fromObject(mediaIds);
        JSONObject excludedMediaJsonObject = JSONObject.fromObject(excludedMediaIds);

        JSONArray mediaIdsArray = JSONArray.fromObject(mediaJsonObject);

        JSONArray excludedMediaIdsArray = JSONArray.fromObject(excludedMediaJsonObject);
        /*mediaIdsArray = mediaJsonObject.toJSONArray(mediaIdsArray);
        excludedMediaIdsArray = excludedMediaJsonObject.toJSONArray(excludedMediaIdsArray);*/
        List<Integer> mediaIdList;
        List<Integer> excludedMediaIdList;
        mediaIdList = (List<Integer>) JSONArray.toList(mediaIdsArray);
        excludedMediaIdList = (List<Integer>) JSONArray.toList(excludedMediaIdsArray);


        if (mediaIds != null) {
            mediaIdList = new ArrayList<>(mediaIdsArray.size());
            excludedMediaIdList = new ArrayList<>(excludedMediaIdsArray.size());
            for (Object excludedId :excludedMediaIdList) {
                if (excludedId != null && Integer.class.equals(excludedId.getClass())) {
                    for (Object id : mediaIdList) {
                        if (id != null && Integer.class.equals(id.getClass())) {
                            if (id == excludedId) {
                                mediaIdList.remove(id);
                            }
                        }
                    }
                }
            }
        }

        for (Object obj : mediaIdList) {
            System.out.println(obj);
        }
    }
}
