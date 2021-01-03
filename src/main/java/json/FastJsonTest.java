package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.alibaba.fastjson.JSON.parseObject;
import static com.alibaba.fastjson.JSON.toJSONString;

public class FastJsonTest {

    @Test
    public void test77() {
        String str = "{\"1000386\":157,\"1000387\":156,\"1000427\":153,\"1000428\":153,\"1000457\":157,\"1000458\":157,\"1000459\":155,\"1000460\":155,\"1000490\":153,\"1000492\":153,\"1000513\":157,\"1000564\":157,\"1000565\":157,\"1000568\":153,\"1000714\":153,\"1000715\":157,\"1000719\":153,\"1000720\":153,\"1000778\":153,\"1000779\":157,\"1000793\":157,\"1000794\":153,\"1000801\":154,\"1000851\":154,\"1000852\":154,\"1000958\":157,\"1001061\":154,\"1001109\":158,\"1001111\":157,\"1001112\":157,\"1001242\":153,\"1001316\":157,\"1001327\":157,\"1001356\":153,\"1001357\":153,\"1001360\":153,\"1001361\":153,\"1001362\":156,\"1001363\":156,\"1001427\":153,\"1001428\":153,\"1001450\":156,\"1001477\":157,\"1001478\":157,\"1001482\":153,\"1001484\":153,\"1001489\":155,\"1001490\":153,\"1001495\":155,\"1001496\":155,\"1001497\":155,\"1001517\":153,\"1001519\":157,\"1001520\":153,\"1001553\":153,\"1001554\":153,\"1001559\":153,\"1001560\":153,\"1001561\":157,\"1001563\":155,\"1001564\":155,\"1001586\":156,\"1001587\":153,\"1001589\":153,\"1001590\":153,\"1001591\":155,\"1001592\":155,\"1001597\":155,\"1001598\":155,\"1001614\":155,\"1001615\":155,\"1001617\":153,\"1001618\":153,\"1001619\":153,\"1001657\":153,\"1001658\":153,\"1001663\":153,\"1001664\":153,\"1001667\":153,\"1001668\":153,\"1001671\":156,\"1001672\":156,\"1001681\":157,\"1001682\":157,\"1001685\":153,\"1001686\":157,\"1001687\":157,\"1001718\":153,\"1001724\":153,\"1001728\":153,\"1001729\":153,\"1001730\":153,\"1001742\":153,\"1001744\":155,\"1001764\":153,\"1001771\":153,\"1001776\":153,\"1001777\":153,\"1001806\":153,\"1001807\":153,\"1001808\":155,\"1001809\":155,\"1001820\":158,\"1001821\":158,\"1001857\":153,\"1001876\":153,\"1001879\":153,\"1001880\":153,\"1001882\":156,\"1001883\":153,\"1001900\":153,\"1001901\":153,\"1001925\":153,\"1001928\":153,\"1001941\":153,\"1001942\":153,\"1002032\":153,\"1002082\":158,\"1002083\":158,\"1002085\":158,\"1002096\":153,\"1002097\":153,\"1002106\":153,\"1002107\":153,\"1002108\":157,\"1002109\":153,\"1002110\":153,\"1002111\":153,\"1002112\":153,\"1002113\":153,\"1002114\":153,\"1002115\":153,\"1002116\":153,\"1002117\":153,\"1002118\":157,\"1002119\":158,\"1002120\":158,\"1002121\":158,\"1002122\":158,\"1002154\":157,\"1002156\":157,\"1002183\":157,\"1002185\":157,\"1002237\":157,\"1002238\":157,\"1002243\":153,\"1002245\":153,\"1002256\":153,\"1002257\":153,\"1002263\":153,\"1002264\":153,\"1002275\":158,\"1002276\":158,\"1002277\":157,\"1002278\":157,\"1002279\":157,\"1002280\":157,\"1002305\":153,\"1002306\":153,\"1002315\":157,\"1002316\":158,\"1002317\":158,\"1002320\":153,\"1002321\":153,\"1002332\":153,\"1002360\":153,\"1002361\":157,\"1002363\":153,\"1002366\":153,\"1002367\":153,\"1002370\":154,\"1002372\":157,\"1002388\":157,\"1002395\":153,\"1002396\":153,\"1002401\":153,\"1002404\":153,\"1002428\":153,\"1002429\":157,\"1002430\":153,\"1002431\":153,\"1002433\":157,\"1002436\":153,\"1002437\":153,\"1002438\":157,\"1002439\":157,\"1002440\":153,\"1002441\":153,\"1002442\":157,\"1002443\":157,\"1002445\":157,\"1002446\":157,\"1002451\":153,\"1002452\":153,\"1002453\":157,\"1002454\":157,\"1002455\":157,\"1002456\":157,\"1002457\":157,\"1002458\":157,\"1002459\":157,\"1002460\":157,\"1002483\":153,\"1002484\":153,\"1002500\":158,\"1002501\":153,\"1002502\":153,\"1002504\":157,\"1002506\":157,\"1002508\":153,\"1002509\":153,\"1002510\":157,\"1002511\":157,\"1002513\":158,\"1002514\":158,\"1002515\":153,\"1002518\":153,\"1002519\":153,\"1002520\":153,\"1002522\":153,\"1002523\":153,\"1002524\":153,\"1002533\":158,\"1002534\":157,\"1002536\":157,\"1002538\":157,\"1002542\":154,\"1002544\":157,\"1002551\":153,\"1002552\":153,\"1002596\":158,\"1002599\":153,\"1002600\":153,\"1002601\":153,\"1002602\":153,\"1002603\":153,\"1002604\":153,\"1002605\":153,\"1002606\":153,\"1002610\":153,\"1002614\":153,\"1002615\":153,\"1002618\":157,\"1002619\":157,\"1002620\":157,\"1002882\":153,\"1002903\":153,\"1002908\":153,\"1002909\":153,\"1002912\":153,\"1002913\":158,\"1002914\":158,\"1002917\":153,\"1002918\":153,\"1002919\":158,\"1002920\":153,\"1002921\":153,\"1002925\":153,\"1002926\":153,\"1002927\":157,\"1002928\":157,\"1002929\":158,\"1002930\":157,\"1002931\":153,\"1002932\":153,\"1002933\":157,\"1002943\":157,\"1002945\":157,\"1002946\":157,\"1002947\":157,\"1002948\":157,\"1002956\":153,\"1002957\":153,\"1002960\":153,\"1002966\":157,\"1002979\":153,\"1002982\":153,\"1002990\":158,\"1002999\":153,\"1003004\":153,\"1003006\":153,\"1003007\":153,\"1003022\":153,\"1003023\":153,\"1003042\":153,\"1003045\":158,\"1003083\":153,\"1003084\":153,\"1003085\":153,\"1003086\":153,\"1003087\":153,\"1003089\":157,\"1003091\":153,\"1003100\":153,\"1003101\":153,\"1003102\":153,\"1003103\":153,\"1003108\":153,\"1003110\":157,\"1003139\":153,\"1003161\":153,\"1003163\":153,\"1003164\":153,\"1003165\":153,\"1003167\":157,\"1003170\":157,\"1003171\":157,\"1003172\":157,\"1003173\":153,\"1003174\":153,\"1003175\":158,\"1003176\":158,\"1003177\":158,\"1003197\":157,\"1003198\":157,\"1003200\":153,\"1003201\":153,\"1003213\":153,\"1003217\":153,\"1003223\":153,\"1003228\":153,\"1003253\":153,\"1003254\":153,\"1003314\":158,\"1003315\":158,\"1003340\":153,\"1003341\":153,\"1003346\":153,\"1003390\":153,\"1003392\":158,\"1003393\":158,\"1003396\":153,\"1003397\":153,\"1003404\":153,\"1003405\":153,\"1003406\":156,\"1003407\":156,\"1003411\":153,\"1003412\":153,\"1003421\":153,\"1003427\":153,\"1003428\":153,\"1003447\":153,\"1003448\":153,\"1003459\":153,\"1003461\":153,\"1003462\":153,\"1003464\":158,\"1003468\":153,\"1003469\":153,\"1003472\":153,\"1003477\":153,\"1003494\":153,\"1003581\":153,\"1003582\":153,\"1003614\":153,\"1003618\":153,\"1003620\":153,\"1003622\":153,\"1003625\":153,\"1003626\":153,\"1003627\":153,\"1003651\":153,\"1003652\":153,\"1003654\":153,\"1003673\":153,\"1003674\":153,\"1003236\":157,\"1003235\":157,\"1003877\":153,\"1003876\":153,\"1003874\":153,\"1003873\":153,\"1003871\":153,\"1003870\":153,\"1003869\":153,\"1003868\":153,\"1003595\":153,\"1003624\":153,\"1003706\":158,\"1003578\":153,\"1003525\":153,\"1003524\":153,\"1003764\":153,\"1000412\":153,\"1000341\":158,\"1003810\":153,\"1003495\":153,\"1003498\":158,\"1003499\":158,\"1003496\":158,\"1003497\":158,\"1003047\":153,\"1003887\":153,\"1003892\":153,\"1003889\":158,\"1003894\":158,\"1003891\":158,\"1003896\":158,\"1003915\":158,\"1003914\":158,\"1003913\":158,\"1003912\":158,\"1003911\":158,\"1003910\":158,\"1003909\":153,\"1003908\":153,\"1003907\":153,\"1003906\":153,\"1003905\":153,\"1003904\":153,\"1003903\":158,\"1003902\":158,\"1003901\":154,\"1003900\":154,\"1003899\":153,\"1003898\":153,\"1002499\":158,\"1003938\":158,\"1003897\":155,\"1003743\":153,\"1003744\":153,\"1003745\":153,\"1003746\":153,\"1003747\":158,\"1003748\":158,\"1003749\":155,\"1003750\":158,\"1003751\":158,\"1003752\":158,\"1003825\":153,\"1003826\":153,\"1003939\":155,\"1003940\":155,\"1003917\":153,\"1003822\":153,\"1003812\":153,\"1003813\":153,\"1003786\":153,\"1003521\":153,\"1003707\":153,\"1003850\":153}";
        JSONObject obj = JSONObject.parseObject(str);
        JSONObject object = new JSONObject();
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            String value = entry.getValue().toString();
            JSONArray valueArr = new JSONArray();
            valueArr.add(Integer.parseInt(value));
            String newValue = "1" + value;
            valueArr.add(Integer.parseInt(newValue));
            object.put(entry.getKey(), valueArr);
        }

        /*for (Map.Entry<String, Object> entry : object.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }*/
        System.out.println(object.toJSONString());
    }

    @Test
    public void test23() throws IOException {
        String url = "http://58dp.58corp.com/api/fetchJobState?schedulerId=154448";
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        int responseCode = connection.getResponseCode();
        if (200 != responseCode) {
        }
        String contentLength = connection.getHeaderField("Content-Length");

        System.out.println("RT 序列化文件长度:" + Integer.parseInt(contentLength));

        InputStream inputStream = connection.getInputStream();
        byte[] buffer = new byte[1024];
        int length;
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String str = result.toString(StandardCharsets.UTF_8.name());
        System.out.println(str);
        JSONObject object = JSONObject.parseObject(str);
        String status = object.getString("status");
        if (!"ok".equalsIgnoreCase(status)) {
        }
        int jobState = object.getIntValue("jobState");
        if (3 != jobState) {
        }
    }

    @Test
    public void parseObjectTest2() {
        String str = "{\n" +
                "    \"status\": \"ok\",\n" +
                "    \"msg\": \"fetch success\",\n" +
                "    \"jobState\": 3,\n" +
                "    \"jobStateCode\": 400,\n" +
                "    \"schedulerId\": \"154448\",\n" +
                "    \"jobStateDesc\": \"JOB_REDAY==0,JOB_WAITING_SIGNAL = 1,JOB_EXECUTING = 2,JOB_SUCCESS = 3,JOB_FAIL = 4,JOB_KILLED = 5,JOB_runDuplic = 6\"\n" +
                "}";

        JSONObject object = JSONObject.parseObject(str);
        String status = object.getString("status");
        System.out.println(status);
    }

    @Test
    public void json222() {
        JSONObject object = new JSONObject();
        object.put("spm", new Object[]{"58un", 1});
        System.out.println(object.toJSONString());
    }

    @Test
    public void jsonArray2Test() {
        String a = "[[null,null,null,null,null]]";
        //String a = "[]";// isEmpty
        //String a = "[[\"0\"]]";
        JSONArray jsonArray = JSON.parseArray(a);
        JSONArray jsonArray1 = jsonArray.getJSONArray(0);
        String string = jsonArray1.getString(0);
        System.out.println(string == null);
        System.out.println(jsonArray1);
    }


    @Test
    public void getIntegerTest() {
        System.out.println(Integer.MAX_VALUE);
        JSONObject object = new JSONObject();
        object.put("a", "3764513286");
        Long a1 = object.getLong("a");
        System.out.println(a1);
        Integer a = object.getInteger("a");
        System.out.println(a);
    }


    @Test
    public void test222() {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("order_id", "订单id");
        headMap.put("order_name", "订单名称");
        headMap.put("promotion_id", "推广id");
        headMap.put("promotion_name", "推广名称");

        String s = JSON.toJSONString(headMap);
        System.out.println(s);

        String str = "{\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"order_id\": \"1\",\n" +
                "        \"order_name\": \"订单1\",\n" +
                "        \"promotion_id\": \"01\",\n" +
                "        \"promotin_name\":\"推广01\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"order_id\": \"2\",\n" +
                "        \"order_name\": \"订单2\",\n" +
                "        \"promotion_id\": \"02\",\n" +
                "        \"promotin_name\":\"推广02\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"order_id\": \"3\",\n" +
                "        \"order_name\": \"订单3\",\n" +
                "        \"promotion_id\": \"03\",\n" +
                "        \"promotin_name\":\"推广03\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

    }

    @Test
    public void JsonTest() {
        // list
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        // list -> json
        String listStr = JSON.toJSONString(list);
        System.out.println("list 转jsonString:" + listStr);

        // json -> list
        System.out.println("json 转 list");
        List<String> list1 = (List)JSON.parse(listStr);
        for (String a : list1) {
            System.out.println(a);
        }

        // json -> JSONArray
        JSONArray jsonArray = JSON.parseArray(listStr);
        System.out.println("jsonArray:" + jsonArray);

        // map
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        // map -> json
        String mapStr = JSON.toJSONString(map);
        System.out.println("map 转 jsonString:" +mapStr);
        // json -> map
        Map<String, String> maps = (Map)JSON.parse(mapStr);

        System.out.println("json 转 map");
        for (Iterator<String> iterator = maps.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            System.out.println("key:" + key+" value:" + maps.get(key));
        }

        String a = "[\"a\",\"b\"]";
        JSONArray jsonArray2 = JSON.parseArray(a);
        System.out.println(jsonArray2);

    }

    @Test
    public void test1() {
        JSONObject obj = new JSONObject();
        obj.put("medias", 1);
        obj.put("excluded_medias", 2);
        System.out.println(obj);

        String medias = toJSONString(obj.get("medias"));
        JSONObject jsonObject = parseObject(medias);
        System.out.println(jsonObject);

        System.out.println(medias);
        Set<Integer> excludedSet = JSONObject.toJavaObject(parseObject(toJSONString(obj.get("excluded_medias"))), Set.class);
        for (Integer id : excludedSet) {
            System.out.println(id);
        }

    }

    @Test
    public void parseObjectTest() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        String s = JSON.toJSONString(set);
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);
    }

    @Test
    public void jsonArrayTest() {
        Integer[] array1 = {1,2,3,4};
        Integer[] array2 = {2,3,5};
        Object o1 = JSON.toJSON(array1);
        Object o2 = JSON.toJSON(array2);
        System.out.println("o1:" + o1);
        System.out.println("o2:" + o2);

        String s = toJSONString(o1);
        Object parse = JSON.parse(s);
        System.out.println(parse);
    }

    @Test
    public void test22() {
        JSONObject data = new JSONObject();
        data.put("start_date", "2018-07-02");
        data.put("end_date", "2018-07-");
        data.put("advertiser_id", 1111);
        data.put("creative_id", 123);
        System.out.println(data);

        JSONObject res = new JSONObject();
        res.put("data", data);
        System.out.println(res.toJSONString());

    }

    @Test
    public void test2ddd22() {

        String conditions = "{\n" +
                "  \"location\": [\n" +
                "    \"319\",\n" +
                "    \"700\",\n" +
                "    \"10171\",\n" +
                "    \"2501\",\n" +
                "    \"10159\",\n" +
                "    \"10179\",\n" +
                "    \"2315\",\n" +
                "    \"5918\",\n" +
                "    \"3184\"\n" +
                "  ],\n" +
                "  \"user_sex\": [\n" +
                "    \"9999999\"\n" +
                "  ],\n" +
                "  \"user_age\": [\n" +
                "    \"9999999\"\n" +
                "  ],\n" +
                "  \"mobile_system\": [\n" +
                "    \"42104\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    \"9999999\"\n" +
                "  ],\n" +
                "  \"slots\": [\n" +
                "    9999999\n" +
                "  ],\n" +
                "  \"medias\": [\n" +
                "    9999999\n" +
                "  ],\n" +
                "  \"excluded_medias\": [],\n" +
                "  \"excluded_slots\": []\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(conditions);
        JSONArray location = jsonObject.getJSONArray("location");
        System.out.println(location);

        JSONArray user_sex = jsonObject.getJSONArray("user_sex");
        System.out.println(user_sex);

        JSONArray user_age = jsonObject.getJSONArray("user_age");
        System.out.println(user_age);

        JSONArray mobile_system = jsonObject.getJSONArray("mobile_system");
        System.out.println(mobile_system);
        for (int i = 0; i < mobile_system.size(); i++) {
            String key = mobile_system.getString(i);
            System.out.println(key);
        }

        JSONArray tags = jsonObject.getJSONArray("tags");
        System.out.println(tags);

        JSONArray slots = jsonObject.getJSONArray("slots");
        System.out.println(slots);

        JSONArray medias = jsonObject.getJSONArray("medias");
        System.out.println(medias);

        JSONArray excluded_medias = jsonObject.getJSONArray("excluded_medias");
        System.out.println(excluded_medias);

        JSONArray excluded_slots = jsonObject.getJSONArray("excluded_slots");
        System.out.println(excluded_slots);
    }

    @Test
    public void testJsonArray() {
        Integer a = 1;
        JSONArray b = new JSONArray();
        b.add(a);
        System.out.println(b.get(0));
    }

    @Test
    public void test3333() {
        String a = "{\n" +
                "  \"result\": {\n" +
                "    \"keys\": [\n" +
                "      \"adxid\"\n" +
                "    ],\n" +
                "    \"attributes\": [\n" +
                "      {\n" +
                "        \"name\": \"adxid\",\n" +
                "        \"type\": \"STRING\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"req_count\",\n" +
                "        \"type\": \"NUMBER\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"bid_count\",\n" +
                "        \"type\": \"NUMBER\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"req_count\": 3838006,\n" +
                "        \"bid_count\": 0,\n" +
                "        \"adxid\": \"u-2c2mffwc797pnc4f21\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"req_count\": 131470166,\n" +
                "        \"bid_count\": 15513227,\n" +
                "        \"adxid\": \"u-2c3ttpj193v43nkddh1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"req_count\": 259231189,\n" +
                "        \"bid_count\": 117884985,\n" +
                "        \"adxid\": \"u-2cbc34ryk3v43nkdq9g\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"req_count\": 17920386,\n" +
                "        \"bid_count\": 10942,\n" +
                "        \"adxid\": \"u-2chmuehmd3v43nkdq9g\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"req_count\": 595174117,\n" +
                "        \"bid_count\": 1062552786,\n" +
                "        \"adxid\": \"u-Lx4Y3m-2J9tMcKG\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(a);
        if (jsonObject != null) {
            JSONObject result = jsonObject.getJSONObject("result");
            if (result != null) {
                System.out.println(result);
                JSONArray data = result.getJSONArray("data");
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        Long bid_count = obj.getLong("bid_count");
                        Long req_count = obj.getLong("req_count");
                        String adxid = obj.getString("adxid");
                        System.out.println(bid_count);
                        System.out.println(req_count);
                        System.out.println(adxid);

                    }
                }
            }
        }

    }

    @Test
    public void countTest() {
        String a = "{\n" +
                "  \"result\": {\n" +
                "    \"data\": [\n" +
                "      {\n" +
                "        \"data\": {\n" +
                "          \"data\": [\n" +
                "            {\n" +
                "              \"adxid\": \"u-2c2mffwc797pnc4f21\",\n" +
                "              \"req_count\": 1188174\n" +
                "            },\n" +
                "            {\n" +
                "              \"adxid\": \"u-2c3ttpj193v43nkddh1\",\n" +
                "              \"req_count\": 62909150\n" +
                "            },\n" +
                "            {\n" +
                "              \"adxid\": \"u-2cbc34ryk3v43nkdq9g\",\n" +
                "              \"req_count\": 147510548\n" +
                "            },\n" +
                "            {\n" +
                "              \"adxid\": \"u-2chmuehmd3v43nkdq9g\",\n" +
                "              \"req_count\": 5986774\n" +
                "            },\n" +
                "            {\n" +
                "              \"adxid\": \"u-Lx4Y3m-2J9tMcKG\",\n" +
                "              \"req_count\": 258741527\n" +
                "            }\n" +
                "          ],\n" +
                "          \"keys\": [\n" +
                "            \"adxid\"\n" +
                "          ],\n" +
                "          \"attributes\": [\n" +
                "            {\n" +
                "              \"name\": \"adxid\",\n" +
                "              \"type\": \"STRING\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"req_count\",\n" +
                "              \"type\": \"NUMBER\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        \"count\": {\n" +
                "          \"op\": \"literal\",\n" +
                "          \"value\": 5\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"attributes\": [\n" +
                "      {\n" +
                "        \"name\": \"data\",\n" +
                "        \"type\": \"DATASET\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"count\",\n" +
                "        \"type\": \"NUMBER\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(a);
        if (jsonObject != null) {
            JSONObject result = jsonObject.getJSONObject("result");
            if (result != null) {
                System.out.println(result);
                JSONArray data = result.getJSONArray("data");
                JSONObject jsonObject1 = data.getJSONObject(0);
                JSONObject count = jsonObject1.getJSONObject("count");
                System.out.println(count);

                Integer value = count.getInteger("value");
                System.out.println(value);
            }
        }
    }

    @Test
    public void test22222() {
        String a = "sss\njljkljkl\n";
        System.out.println(a);
        JSONObject object = new JSONObject();
        object.put("sql", object);
        System.out.println(object.toJSONString());
    }

    @Test
    public void testJsonIsEmpty() {
        String s = "{\"result\":{\"data\":[]}}";
        JSONObject object = JSON.parseObject(s);
        JSONObject result = object.getJSONObject("result");
        if (result != null) {
            JSONArray data = result.getJSONArray("data");
            System.out.println(data);
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    String a = jsonObject.getString("a");
                    System.out.println(a);
                }
            }
        }
    }

    @Test
    public void test111111() {
        String s = "{\"data\":[{}]}";
        JSONObject object = JSON.parseObject(s);
        System.out.println(object);
        JSONArray data = object.getJSONArray("data");
        System.out.println(data);
        System.out.println(data.isEmpty());
        System.out.println(data == null);
        System.out.println(data.get(0));
        System.out.println(data.get(0).toString().equals("{}"));
        System.out.println();

    }

}
