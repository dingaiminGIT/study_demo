package javabase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import date.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.junit.jupiter.api.Test;
import utils.SeriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    private static Pattern pattern = Pattern.compile("cate_top_([0-9]+)");

    public static void main(String[] args) {
        long start = Instant.now().toEpochMilli();
        String abTest = "cate_top_14,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs,do_ctrmodel_b,cccc_1,cr_fc,ylbs";

        // 方案1 用正则表达式
        Matcher matcher = pattern.matcher(abTest);
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
        }

        // 方案2 用 split 方法取
        /*if (abTest.contains("cate_top_")) {
            String[] arr = abTest.split("cate_top_");
            String a = arr[1].split(",")[0];
            System.out.println(a);

        }*/
        long end = Instant.now().toEpochMilli();
        System.out.println("耗时：" + (end - start));
    }


    @Test
    public void replaceTest() {
        String preSql = "SELECT DT,HR,BG_ID,MAINCATE,SOURCE2,SOURCE3,ACCOUNT_NAME,PLATFORM_ID,CAMPAIGN_NAME,";
        String nextSql = "FROM lm_dws_sem_keyword_day_hour WHERE  dt>='20201031' AND dt<='20201101' AND hr>='1' AND hr<='15' AND platform_id in ('1','2','3','4','5') AND source3 in ('神马sem-二手货车','神马sem-二手工程车','神马sem-1','神马sem-销售支持','神马sem-二手车') GROUP BY DT,HR,BG_ID,MAINCATE,SOURCE2,SOURCE3,ACCOUNT_NAME,PLATFORM_ID,CAMPAIGN_NAME LIMIT 10  OFFSET 0";

        String uvSql = preSql + "COUNT(DISTINCT FLOW_USER_ID) as UV " + nextSql
                .replaceAll("lm_dws_sem_keyword_day_hour", "lm_dws_sem_in_station_hour")
                .replaceAll("lm_dws_sem_plan_local1_day", "lm_dws_sem_in_station_day")
                .replaceAll("lm_dws_sem_keyword_day", "lm_dws_sem_in_station_day");
        System.out.println(uvSql);


        /*String a = "lm_dws_sem_keyword_day_hour";
        String b = a.replaceAll("lm_dws_sem_keyword_day_hour", "lm_dws_sem_in_station_hour");
        System.out.println(b);*/
    }

    @Test
    public void toLower() {
        String s = "LM_DWS_SEM_KEYWORD_HOUR_REALTIME_LEFT";
        System.out.println(s.toLowerCase());
    }

    @Test
    public void indexOfTest() {
        String sql = "SELECT DT,BG_ID,MAINCATE,SOURCE2,SOURCE3,SUM(IMP) AS IMP,SUM(CLICK) AS CLICK,SUM(COST) AS COST,SUM(CASH) AS CASH,SUM(UV) AS UV,SUM(PV) AS PV,SUM(DISP) AS DISP,SUM(CLICK1) AS CLICK1,SUM(CLICK0) AS CLICK0,SUM(CALLS) AS CALLS,SUM(CALL_SUCCESS) AS CALL_SUCCESS,SUM(RESUME_COUNT) AS RESUME_COUNT,SUM(RESUME_NUMBER) AS RESUME_NUMBER,SUM(RESUME_POST) AS RESUME_POST,SUM(PAY_ORDER_NUM) AS PAY_ORDER_NUM,SUM(JZ_CASH) AS JZ_CASH,SUM(ZD_CASH) AS ZD_CASH,SUM(ZPJX_CASH) AS ZPJX_CASH,SUM(ZPYX_CASH) AS ZPYX_CASH,SUM(JX_CASH) AS JX_CASH,SUM(AJKJX_CASH) AS AJKJX_CASH,SUM(CARJX_CASH) AS CARJX_CASH,SUM(CARLDT_CASH) AS CARLDT_CASH,SUM(CARQGG_CASH) AS CARQGG_CASH,SUM(PPGY_CASH) AS PPGY_CASH,SUM(DJJX_CASH) AS DJJX_CASH,SUM(ZHAOPIN_CASH) AS ZHAOPIN_CASH,SUM(FANGCHAN_CASH) AS FANGCHAN_CASH,SUM(HUANGYE_CASH) AS HUANGYE_CASH,SUM(ERCHOUCHE_CASH) AS ERCHOUCHE_CASH,SUM(ERSHOUTG_CASH) AS ERSHOUTG_CASH,SUM(ERSHOUCX_CASH) AS ERSHOUCX_CASH,SUM(JZ_CLICK0) AS JZ_CLICK0,SUM(ZD_CLICK0) AS ZD_CLICK0,SUM(ZPJX_CLICK0) AS ZPJX_CLICK0,SUM(ZPYX_CLICK0) AS ZPYX_CLICK0,SUM(JX_CLICK0) AS JX_CLICK0,SUM(AJKJX_CLICK0) AS AJKJX_CLICK0,SUM(CARJX_CLICK0) AS CARJX_CLICK0,SUM(CARLDT_CLICK0) AS CARLDT_CLICK0,SUM(CARQGG_CLICK0) AS CARQGG_CLICK0,SUM(PPGY_CLICK0) AS PPGY_CLICK0,SUM(JZ_CLICK1) AS JZ_CLICK1,SUM(ZD_CLICK1) AS ZD_CLICK1,SUM(ZPJX_CLICK1) AS ZPJX_CLICK1,SUM(ZPYX_CLICK1) AS ZPYX_CLICK1,SUM(JX_CLICK1) AS JX_CLICK1,SUM(AJKJX_CLICK1) AS AJKJX_CLICK1,SUM(CARJX_CLICK1) AS CARJX_CLICK1,SUM(CARLDT_CLICK1) AS CARLDT_CLICK1,SUM(CARQGG_CLICK1) AS CARQGG_CLICK1,SUM(PPGY_CLICK1) AS PPGY_CLICK1,SUM(JZ_CALL) AS JZ_CALL,SUM(ZD_CALL) AS ZD_CALL,SUM(ZPJX_CALL) AS ZPJX_CALL,SUM(ZPYX_CALL) AS ZPYX_CALL,SUM(JX_CALL) AS JX_CALL,SUM(AJKJX_CALL) AS AJKJX_CALL,SUM(CARJX_CALL) AS CARJX_CALL,SUM(CARLDT_CALL) AS CARLDT_CALL,SUM(CARQGG_CALL) AS CARQGG_CALL,SUM(PPGY_CALL) AS PPGY_CALL,SUM(OTHER_CALL) AS OTHER_CALL,SUM(JZ_CALL_SUCCESS) AS JZ_CALL_SUCCESS,SUM(ZD_CALL_SUCCESS) AS ZD_CALL_SUCCESS,SUM(ZPJX_CALL_SUCCESS) AS ZPJX_CALL_SUCCESS,SUM(ZPYX_CALL_SUCCESS) AS ZPYX_CALL_SUCCESS,SUM(JX_CALL_SUCCESS) AS JX_CALL_SUCCESS,SUM(AJKJX_CALL_SUCCESS) AS AJKJX_CALL_SUCCESS,SUM(CARJX_CALL_SUCCESS) AS CARJX_CALL_SUCCESS,SUM(CARLDT_CALL_SUCCESS) AS CARLDT_CALL_SUCCESS,SUM(CARQGG_CALL_SUCCESS) AS CARQGG_CALL_SUCCESS,SUM(PPGY_CALL_SUCCESS) AS PPGY_CALL_SUCCESS,SUM(OTHER_CALL_SUCCESS) AS OTHER_CALL_SUCCESS FROM lm_dws_sem_keyword_day WHERE  dt>='20201012' AND dt<='20201021' AND platform_id in ('1','2','3','4','5') AND source3 in ('神马sem-二手货车','神马sem-二手工程车','神马sem-1','神马sem-销售支持','神马sem-二手车') GROUP BY DT,BG_ID,MAINCATE,SOURCE2,SOURCE3 ORDER BY  DT DESC,CASH ASC LIMIT 10  OFFSET 0";
        int sumIndex = sql.indexOf("SUM");
        String preSql = sql.substring(0, sumIndex);
        System.out.println(preSql);
    }

    @Test
    public void format() {
        String detailLocationPrefixFormat = "http://webhdfs.58corp.com/webhdfs/v1/home/hdp_lbg_ectech/warehouse/hdp_lbg_ectech_lmdb/t_lm_dsp_rt/detail/%s/%s?op=OPENDECOMPRESS&user.name=hdp_lbg_ectech";
        String date = "20200812";
        String ret = String.format(detailLocationPrefixFormat, date, "777");
        System.out.println(ret);
    }

    @Test
    public void spilit() {
        String a = "";
        String[] split = a.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }


    @Test
    public void test222() {
        //String a = "fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_7529.s_85973738930315264.m_495.t_747.o_240.x_u-2cbc34ryk3v43nkdq9g&request=bckxhKWvCdnjMbqpuvREwcoh&ab=limit_b,pass_r,ogbid,do_ctrmodel_h,LBIS_A,tt_req_applets,bid_o_c,PAY_TYPE_C,dspdl&ip=118.244.134.200&sign=bb06955dc9f03f11947993d4b2e4f37b&media=495&slot=747&creative=5627&sid=85973738930315264&wb_dsp=2&wb_dsp_creative=241152539194616&local1=1&uid=a7870fd3b5bac75b9c21ff2a4cd6f6aa&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=84a25e5c-bdc4-4165-b652-eac143e5835d&wb_dsp_sid=15b9d66f-f0b6-4564-b824-2fd9ed1111e0&style=217&did=&order=240&promotion=7529&info=33341061769406";
        //String a = "fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_7532.s_86069597677305856.m_495.t_747.o_239.x_u-2cbc34ryk3v43nkdq9g&request=ZvwxFcpByahppxHffGNKtCoP&ab=limit_b,pass_r,ogbid,do_ctrmodel_b,LBIS_A,tt_req_h5,bid_o_c,PAY_TYPE_C,dspdnl&ip=118.244.134.200&sign=b8db0df0d264ea9e1f6d940477715296&media=495&slot=747&creative=5639&sid=86069597677305856&wb_dsp=&wb_dsp_creative=&local1=1&uid=a7870fd3b5bac75b9c21ff2a4cd6f6aa&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=&wb_dsp_sid=&style=217&did=&order=239&promotion=7532&info=";
        //String a = "https://dsptrack.58v5.cn/n/d?fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_7532.s_86069597677305856.m_495.t_747.o_239.x_u-2cbc34ryk3v43nkdq9g&request=ZvwxFcpByahppxHffGNKtCoP&ab=limit_b,pass_r,ogbid,do_ctrmodel_b,LBIS_A,tt_req_h5,bid_o_c,PAY_TYPE_C,dspdnl&ip=118.244.134.200&sign=b8db0df0d264ea9e1f6d940477715296&media=495&slot=747&creative=5639&sid=86069597677305856&wb_dsp=&wb_dsp_creative=&local1=1&uid=a7870fd3b5bac75b9c21ff2a4cd6f6aa&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=&wb_dsp_sid=&style=217&did=&order=239&promotion=7532&info=&h_ip=118.244.134.200&h_cache_key=gdt_ZvwxFcpByahppxHffGNKtCoP&h_price=-nHei2nHiI3doCVzjthEyA==";
        //String a = "fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_7533.s_86073283265708032.m_495.t_747.o_240.x_u-2cbc34ryk3v43nkdq9g&request=EBAgdSDEvbRKaeoQtHOVLaWp&ab=limit_a,ogbid,do_ctrmodel_b,LBIS_A,tt_req_applets,bid_o_c,PAY_TYPE_C,dspol&ip=118.244.134.200&sign=e192b7fcc5f7a160356a9b77e9976879&media=495&slot=747&creative=5640&sid=86073283265708032&wb_dsp=2&wb_dsp_creative=283081410527520&local1=1&uid=a7870fd3b5bac75b9c21ff2a4cd6f6aa&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=3984c050-b32b-46cf-95c0-c7530579d832&wb_dsp_sid=8a2e37d2-b9cc-4a4a-a5a0-1c37ad6c42e6&style=217&did=&order=240&promotion=7533&info=38960933927966";
        //String a = "https://dsptrack.58v5.cn/n/d?fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_7533.s_86073283265708032.m_495.t_747.o_240.x_u-2cbc34ryk3v43nkdq9g&request=EBAgdSDEvbRKaeoQtHOVLaWp&ab=limit_a,ogbid,do_ctrmodel_b,LBIS_A,tt_req_applets,bid_o_c,PAY_TYPE_C,dspol&ip=118.244.134.200&sign=e192b7fcc5f7a160356a9b77e9976879&media=495&slot=747&creative=5640&sid=86073283265708032&wb_dsp=2&wb_dsp_creative=283081410527520&local1=1&uid=a7870fd3b5bac75b9c21ff2a4cd6f6aa&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=3984c050-b32b-46cf-95c0-c7530579d832&wb_dsp_sid=8a2e37d2-b9cc-4a4a-a5a0-1c37ad6c42e6&style=217&did=&order=240&promotion=7533&info=38960933927966&h_ip=118.244.134.200&h_cache_key=gdt_EBAgdSDEvbRKaeoQtHOVLaWp&h_price=-nHei2nHiI3doCVzjthEyA==";
        //String a = "fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_7533.s_86073283265708032.m_495.t_747.o_240.x_u-2cbc34ryk3v43nkdq9g&request=EBAgdSDEvbRKaeoQtHOVLaWp&ab=limit_a,ogbid,do_ctrmodel_b,LBIS_A,tt_req_applets,bid_o_c,PAY_TYPE_C,dspol&ip=118.244.134.200&sign=e192b7fcc5f7a160356a9b77e9976879&media=495&slot=747&creative=5640&sid=86073283265708032&wb_dsp=2&wb_dsp_creative=283081410527520&local1=1&uid=a7870fd3b5bac75b9c21ff2a4cd6f6aa&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=3984c050-b32b-46cf-95c0-c7530579d832&wb_dsp_sid=8a2e37d2-b9cc-4a4a-a5a0-1c37ad6c42e6&style=217&did=&order=240&promotion=7533&info=38960933927966";
        //String a = "ngbid,tt_req_applets,spm_b,PAY_TYPE_B,LBIS_B,dspdnl,fc_base,";
        //String a = "fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_9393.s_89806463735083008.m_7180.t_48271.o_3014.x_u-2cbc34ryk3v43nkdq9g&request=ncswgxk4iwpdg&ab=atspmn,limit_b,pass_o,do_ctrmodel_b,bid_o_c,nx_ct_fc&cate=13916&ip=27.193.5.228&sign=e18cd11e554174722105c1c93bc8fc0b&media=7180&slot=48271&creative=99501666&sid=89806463735083008&wb_dsp=&wb_dsp_creative=&local1=122&uid=ff575d1e2e38993cfd5b546207b57a3d&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=&wb_dsp_sid=&style=212&did=ff575d1e2e38993cfd5b546207b57a3d&order=3014&promotion=9393&info=";
        //String a = "fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_9393.s_89806463735083008.m_7180.t_48271.o_3014.x_u-2cbc34ryk3v43nkdq9g&request=ncswgxk4iwpdg&ab=atspmn,limit_b,pass_o,do_ctrmodel_b,bid_o_c,nx_ct_fc&cate=13916&ip=27.193.5.228&sign=e18cd11e554174722105c1c93bc8fc0b&media=7180&slot=48271&creative=99501666&sid=89806463735083008&wb_dsp=&wb_dsp_creative=&local1=122&uid=ff575d1e2e38993cfd5b546207b57a3d&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=&wb_dsp_sid=&style=212&did=ff575d1e2e38993cfd5b546207b57a3d&order=3014&promotion=9393&info=";
        String a = "fid=u-2cbc34ryk3v43nkdq9g.mfc_gdt.a_9393.s_89806463735083008.m_7180.t_48271.o_3014.x_u-2cbc34ryk3v43nkdq9g&request=ncswgxk4iwpdg&ab=atspmn,limit_b,pass_o,do_ctrmodel_b,bid_o_c&cate=13916&ip=27.193.5.228&sign=e18cd11e554174722105c1c93bc8fc0b&media=7180&slot=48271&creative=99501666&sid=89806463735083008&wb_dsp=&wb_dsp_creative=&local1=122&uid=ff575d1e2e38993cfd5b546207b57a3d&adx=u-2cbc34ryk3v43nkdq9g&wb_dsp_tid=&wb_dsp_sid=&style=212&did=ff575d1e2e38993cfd5b546207b57a3d&order=3014&promotion=9393&info=";
        int length = a.getBytes().length;
        System.out.println(length);
        System.out.println(a.length());

        /*String m = "m";
        String[] strings = {"add_" + m + "_ccc", "kkk"};
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }*/
    }

    @Test
    public void equalsTest() {
        System.out.println("e".equals(null));
    }

    @Test
    public void splitT() {
        // 配置中心中的
        String ips = "10.126.107.138,10.126.107.130,10.126.107.131,10.126.107.134,10.126.107.135,10.126.107.136,10.126.107.137,10.126.107.103,10.126.107.102,10.135.13.85,10.135.13.224,10.135.13.7,10.136.200.137,10.135.13.33,10.136.200.138,10.135.13.229,10.135.11.207,10.135.12.98,10.135.11.138,10.126.90.11,10.126.102.141,10.126.102.142,10.126.102.144,10.126.102.145,10.126.86.82,10.126.90.230,10.126.90.3,10.126.90.226,10.126.102.69,10.126.102.147,10.126.101.141,10.126.102.146,10.126.102.74,10.126.86.81,10.126.91.14";
        String[] split = ips.split(",");
        HashSet<String> ipSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            ipSet.add(split[i]);
            sb.append(split[i]).append(":9200,");
        }

        System.out.println(sb.toString());
        System.out.println(ipSet.size());
        ipSet.stream().forEach((ip) -> System.out.println(ip));
        // cmdb shangye_elastic_index
        /*String ip2 = "10.126.102.141,10.135.12.98,10.126.90.3,10.135.13.224,10.135.13.7,10.136.200.137,10.126.83.100,10.126.107.134,10.126.107.135,10.135.11.207,10.126.90.230,10.126.107.103,10.126.102.145,10.126.101.141,10.126.90.226,10.126.83.156,10.126.102.144,10.136.199.149,10.126.86.82,10.126.107.102,10.126.91.14,10.135.13.85,10.135.13.33,10.126.90.11,10.126.107.130,10.135.11.138,10.126.107.138,10.126.107.136,10.126.102.142,10.136.200.138,10.126.107.131,10.126.107.137,10.135.13.229";
        String[] split2 = ip2.split(",");
        HashSet<String> ipSet2 = new HashSet<>();
        for (int i = 0; i < split2.length; i++) {
            ipSet2.add(split2[i]);
        }

        System.out.println(ipSet.size());
        System.out.println(ipSet2.size());
        ipSet.removeAll(ipSet2);
        System.out.println(ipSet.size());
        ipSet.stream().forEach((ip) -> System.out.println(ip));*/

    }

    @Test
    public void ttt() {
        System.out.println(1<<30);
    }

    @Test
    public void test454() {
        String a = null;
        System.out.println("ip:" + a);
        try {
            getStr();
        } catch (NullPointerException e) {
            System.out.println("ddd");
            throw e;
        }
    }

    private String getStr() throws NullPointerException{
        throw new NullPointerException();
    }

    @Test
    public void internTest(){
        String str1 = new String("abc");
        String str2 = new String("abc");
        System.out.println(str1 == str2);
        System.out.println(str1 == str2.intern());
        System.out.println(str1.intern() == str2.intern());
        System.out.println("abc" == str2.intern());
    }

    @Test
    public void test343() {
        int minute = DateUtils.getMinute();
        System.out.println(minute);
    }

    @Test
    public void test232323() {
        long l = System.currentTimeMillis();
        int hour = 0;
        String dspDate = DateUtils.getDspDate();
        int week = DateUtils.getDayOfWeek();

        String timeScale = "[\"10\",\"11\",\"12\",\"13\",\"14\",\"15\",\"16\",\"17\",\"18\",\"19\",\"110\",\"111\",\"112\",\"113\",\"114\",\"115\",\"116\",\"117\",\"118\",\"119\",\"120\",\"121\",\"122\",\"123\",\"20\",\"21\",\"22\",\"23\",\"24\",\"25\",\"26\",\"27\",\"28\",\"29\",\"210\",\"211\",\"212\",\"213\",\"214\",\"215\",\"216\",\"217\",\"218\",\"219\",\"220\",\"221\",\"222\",\"223\",\"30\",\"31\",\"32\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"310\",\"311\",\"312\",\"313\",\"314\",\"315\",\"316\",\"317\",\"318\",\"319\",\"320\",\"321\",\"322\",\"323\",\"40\",\"41\",\"42\",\"43\",\"44\",\"45\",\"46\",\"47\",\"48\",\"49\",\"410\",\"411\",\"412\",\"413\",\"414\",\"415\",\"416\",\"417\",\"418\",\"419\",\"420\",\"421\",\"422\",\"423\",\"50\",\"51\",\"52\",\"53\",\"54\",\"55\",\"56\",\"57\",\"58\",\"59\",\"510\",\"511\",\"512\",\"513\",\"514\",\"515\",\"516\",\"517\",\"518\",\"519\",\"520\",\"521\",\"522\",\"523\",\"60\",\"61\",\"62\",\"63\",\"64\",\"65\",\"66\",\"67\",\"68\",\"69\",\"610\",\"611\",\"612\",\"613\",\"614\",\"615\",\"616\",\"617\",\"618\",\"619\",\"620\",\"621\",\"622\",\"623\",\"00\",\"01\",\"02\",\"03\",\"04\",\"05\",\"06\",\"07\",\"08\",\"09\",\"010\",\"011\",\"012\",\"013\",\"014\",\"015\",\"016\",\"017\",\"018\",\"019\",\"020\",\"021\",\"022\",\"023\"]";


        String[] split = timeScale.split(",");

        for (int i = 0; i < split.length; i++) {

        }

        // 计算剩余小时数
        long l2 = System.currentTimeMillis();
        List<String> list = JSON.parseArray(timeScale, String.class);
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
        int startIndex = list.indexOf(dspDate);
        for (int i = startIndex; i < list.size(); i++) {
            if (list.get(i).startsWith(String.valueOf(week))) {
                hour++;
            } else {
                break;
            }
        }

        long l1 = System.currentTimeMillis();
        long t = l1 - l;
        System.out.println(t);
        System.out.println(hour);
    }


    @Test
    public void test55() {
        Long a = 45566L;
        Long b = 41222L;
        Long c = 797454654L;
        String ids = "4556641222,797454654";
        String[] split = ids.split(",");
        Set<String> set = new HashSet<>(split.length);
        set.addAll(Arrays.asList(split));
        System.out.println(set.contains(a + "" + b));
        System.out.println(set.contains(c.toString()));
    }

    @Test
    public void stringAddTest() {
        String a = "1234";
        Long b = 1234L;
        System.out.println(a.equals(b.toString()));
    }

    @Test
    public void test2222222() throws Exception {
        String preselectedSizeStr = "{}";
        HashMap<String, Integer> preselectedSizeMap = SeriUtils.readValue(preselectedSizeStr, new TypeReference<HashMap<String, Integer>>() {
        });
        if (preselectedSizeMap != null) {
            for (Map.Entry<String, Integer> entry : preselectedSizeMap.entrySet()) {
                if (!(entry.getKey() instanceof String) || !(entry.getValue() instanceof Integer)) {
                    System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
                    return;
                }
            }
        }
    }

    @Test
    public void testsss() {
        System.err.println("dddd");
    }

    /**
     *
     * 宏替换（Macro Replace）：发生在用 final 修饰的变量，定义该变量的时候就指定了初始值，
     * 初始值在编译的时候就能确定了下来。
     * 等于三个条件：
     * 		1：用 final 修饰。
     *		2：在定义的时候指定初始值。
     *		3：并且初始值在编译的时候就能确定。
     */
    @Test
    public void test1() {
        //下面两个局部变量由于在编译的时候就能确定，并且会被放在 constant pool 中
        //管理，所以 name 和 name2 是同一个对象。并且在使用的时候会使用宏替换。在
        //使用的地方，编译的时候 name 和 name2 直接会被替换为直接量："sanny wang"。
        String sanny="sanny";
        String wang="wang";
        final String name="sanny wang";
        final String name2="sanny "+"wang";

        //由于这里的 wang 是普通变量，所以编译器在编译的时候不能确定其值，所以 name
        //和上面的不是同一个对象。而如果 wang 被 final 修饰的话，就是同一个对象了。
        //因为 wang 会被宏替换。
        final String name3="sanny "+wang;
        System.out.println(name==name2);
        System.out.println(name==name3);
    }

    @Test
    public void test2() {
        String s1="java";
        String s2="ja"+"va";
        System.out.println(s1==s2);
        final String str1="ja";
        final String str2="va";
        String s3=str1+str2;
        System.out.println(s1==s3);
    }

    @Test
    public void test() {
        String a = "adddd\001fdfdfdf";
        System.out.println(a);
    }

    @Test
    public void test22() {
        int pageSize = 20;
        int size = 21;
        int maxPage = size/pageSize <= 0 ? 1 : (size%pageSize == 0 ? size/pageSize : size/pageSize + 1);
        System.out.println(maxPage);
    }

    @Test
    public void test3() {
        String a = "广点通测试一键投递test";
        String b = "广点通测试一键投递test";
        System.out.println(a.contains(b));
    }

    @Test
    public void splitTest() {
        String s = "10.48.230.39,10.48.224.4,10.48.176.188";
        String[] split = s.split(",");
        List<String> clusterIpList = new ArrayList<>(split.length);
        clusterIpList.addAll(Arrays.asList(split));
        for (String ip : clusterIpList) {
            System.out.println(ip);
        }
    }

    @Test
    public void splitTest2() {
        String s = "SELECT  sum(bid_count)                     bid_count,  sum(bid_sum)                       bid_sum,  sum(win_count)                     win_count,  sum(req_count)                     req_count,  sum(show_count)                    show_count,  sum(click_count)                   click_count,  sum(lm_uv)                         lm_uv,  sum(lm_pv)                         lm_pv,  sum(lm_adpv)                       lm_adpv,  sum(lm_imp)                        lm_imp,  sum(lm_clk0)                       lm_clk0,  sum(lm_clk1)                       lm_clk1,  sum(cost)                          cost,  sum(lm_cash)                       lm_cash,  sum(lm_gift)                       lm_gift,  sum(lm_price)                      lm_price,  sum(lm_cash) + sum(lm_gift)        lm_income,  sum(lm_cash) - sum(cost)           lm_profit,  sum(lm_tele)                       lm_tele,  sum(lm_message)                    lm_message,  sum(lm_resume)                     lm_resume,  sum(lm_resume_count)               lm_resume_count,  sum(lm_resume_num)                 lm_resume_num,  sum(lm_tele_car)                   lm_tele_car,  sum(lm_tele_car_suss)              lm_tele_car_suss,  sum(lm_tele_huangye)               lm_tele_huangye,  sum(lm_tele_huangye_suss)          lm_tele_huangye_suss,  sum(lm_jzpro)                      lm_jzpro,  sum(lm_zdpro)                      lm_zdpro,  sum(lm_jxpro)                      lm_jxpro,  sum(lm_anjuke_jxpro)               lm_anjuke_jxpro,  sum(lm_ganjipro)                   lm_ganjipro,  sum(lm_jxcarpro)                   lm_jxcarpro,  sum(lm_gjjxpro)                    lm_gjjxpro,  sum(lm_carldtpro)                  lm_carldtpro,case when sum(win_count)=0 then 0 else sum(cost) / sum(win_count) end win_average_bid,case when sum(bid_count)=0 then 0 else sum(bid_sum) / sum(bid_count) end average_bid,case when sum(bid_count)=0 then 0 else sum(win_count) / sum(bid_count) end win_rate,case when sum(show_count)=0 then 0 else sum(click_count) / sum(show_count) end dsp_ctr,case when sum(lm_adpv)=0 then 0 else sum(lm_clk1) / sum(lm_adpv) end lm_ctr,case when sum(cost)=0 then 0 else sum(lm_cash) / sum(cost) end roi,hour, campaignid, adid, adxid FROM hdp_lbg_ectech_dsp_data_table_analysis_new where __time between '2018-09-11T19:52+08:00' and '2018-09-11T20:07+08:00' AND adxid IN ('1','2','3','4','5','6','7','8') AND adid IN ('3023') GROUP BY hour, campaignid, adid, adxid LIMIT 20,20";
        String[] sqlSplitByLimit=s.split("LIMIT");
        System.out.println(sqlSplitByLimit[1]);
    }

    @Test
    public void substringTest() {
        String s = "\"2018-09-05 17:1\"";
        System.out.println(s);
        /*String[] split = s.split(":");
        System.out.println(split[0]);
        System.out.println(split[1]);*/
        s = s.replaceAll(":", " ");
        System.out.println(s);

    }

    @Test
    public void testdddd() {
        /*String a = "";
        String b = null;
        System.out.println(a.isEmpty());
        System.out.println(b.isEmpty());*/

        HashMap<String, Boolean> map = new HashMap<>();
        map.put("1", Boolean.TRUE);
        System.out.println(map.containsKey(1));
    }

    @Test
    public void testSubString() {
        String a = "2020110201";
        System.out.println(a.substring(a.length()-2,a.length()));
    }

    @Test
    public void StringLength() throws UnsupportedEncodingException {
        String a = "https://jumpluna.58.com/adxclk?target=pZwY0Zn-nYD-nbm-nbuJIydkUZR8mi3draOWUvY-nbu6uAP_pzN1wMw60hI-IaN1wZKpIdbkmN3Qwy-rXMIaHMGLpDIbRyINr76CPbIRp7c3IgC1uZF6HdIRI-bVUhFdib-OuAVAi7KKiNDLXMRvN--Ai7KDnjIZHZ-ciYG7IMNQiidBnYODrNqzEywvn7FdnN-BIHckHZRKNadiELIuiRukuYRwRidFwZNLivIVRdK7N--LIdRCwYFmsNVk0b9OpY-gIgRiuzdfpywsihOKI168pyw6RN3vw7FPNbqLpDub07cVINOKwv-1udKWUNddrZKKHL-FXNI1NhwsudKvPMGmHMIdRN9LrDOiIvPLp-Kl0A6AUdRgPYFcuZIWIYwyrAOONZIrwjuyiDQAi-RiP16GPL0dRidFPv-vHvI8ERm3NbwLIdNViNOrNMIWUNddHh-DILPRmWuzXRFsmLFr0NRiI--wHbEOHd6CnYqLpWIry7cLXgRnIR7dwadkHbwFuDRrHLPRm-FDH-FLHY-vINdFsNVdUbDvmMRPNbVrsg7PiRIsX7FrNH6MnNV8pywdHN-CNZ6iHWIGi7FimWKcIywEHjI1NbEvHh-bIvPiw7m3NbwL0Y-OrNQMnNVviAFyRhOcURuRHidG0jINNyYVIgGkRYVfigbVIg-OHYVLwZDQyDOdmRRgPYwipAwjUHDLrDRrILurwju70vIAibOKwbOGENqBRycvRMRKnd7LuYIrpN7EUjKCwdudEHPwIvGEXMKCnd7rPY-aHMb1HLIDRMGGEyw1RyFiwDOMNAIiw7urIYOLmWTVwv-zPLIPiaddrZTLiLNkEH-nIH0vHdFauZGGwadfNMbvrZ-iidKLyyEQN--LINOKId-OuYu2IdGZwRFuILIRpDP50AwsmLIKILGGEyPwHhGBHDRusgKFI-ulwNdLpD-iwYRkH7KMi7IEngTLiv6iXiduNAGsUh-bUywdyNqORy9vwg-MiYPEIMIVy7FVRWKBPbuku7FjRiYVwN6riRTkuD-DXRFsmbbLsgGkmMI_HWIFwDOOHYGzXRu7pNwLmWKBPbF8NbV6ig-LwbROI1P8yMRwIyGsEYO6yywGwjnQpyIBny-bIvPLyb03y7IEIdNVigFzENqMiA6ynRFuILurw7FD0AwLmdFDRW7LHMIfNbDVIy-vHYOLP1030adLU1KCEvV8NMIWNbwyngIrIvFRpDIA0jIsmvdgP16FmbtdRRIDpd6PNbGRRvOsRDRDNyOgUW68iAPb0dIWuAO6nd7ERYOsU-wDiLFcUNV8wHIb0Y6ARY-Kw1uFuzdfIN6BHjKKHdIEpMR_IgC1NRKJig6EpW-wU-IVNyOcRjP8pbOwND6BHv3QUNVEijubRH-DyRTQHW7zXNmvUh-bsydCIzd1iDRuNZ-rRMFcUgGVXhwBUb78IhOOUNqViZIgUW7Div3OwAVNiAObUb6VuA3QwgGNiAOlUb6rIMFcrgu8iDwo0b6WyRwcmYQ8p-wsUb6WpvO7w771RdwsUh_LgdRi0yuFuL0Q0Z-LsRwOIvuMI-KunA6buMRKN-qMIWKxRNQ8g1KniduMIhPxRN7iHRRb0HudPL7sFHcv0ZPGuaN1wjELPHnOmhDQsyRBuANVPjEduiYOPWFWsyEkm1mQuW-6PAn1niNzPhR8IA-8uht-nYE1nWDdPWb3PWDQnjbzP7tkTHD1PHELnjNYP193njbOrHnLPHEOTHDzP1bduAP6uWndrjbdmHIWPAc3rH--mWnQP1NYrHcvTHDzP1bduAP6uWndrjbdmHIWPAc3rH--mWnQP1NYrHcvTgNVnhPYph7dXyV-rHIkUhnYPMGMTHDzPEDQPj0KnH9knTDksWTznjEQPjTdnHm1nWEOnW9QPTDzPHDkP97dsHFWIAG6Ig-ouHbL0AOWPjuluz3QPjIxPj9QnWmQnHT3njNQPHbOn1mKnHNYPH9OPWm1PH9OPT7bUdqWIZFVUvw-U7qMsZPkURqBsAq1THE3nHcvnHDkrjTdnHNOrHnvTgwzIyNKn1cQPHmOrjmQnHTOnWEKnEDOP9DQnW0OPywWmym1PH9OPyDLm1wBrjbOuyc1nH0dPjbzP9DQTEDKnT";
        System.out.println(a.length());
        int length = a.getBytes("UTF-8").length;
        System.out.println(length);
    }

    @Test
    public void StringEmpty() {
        String a = "";
        System.out.println(a.isEmpty());
    }

    @Test
    public void tttt() {
        String[] REAL_TIME_DIMENSION_FIELD = new String[]{null, "hour", "campaignid", "adid", "adxid", "mediaid", "slotid", null, "cityid", "bgid", "cateid"};
        String s = REAL_TIME_DIMENSION_FIELD[7];
        System.out.println(s == null);

    }

    @Test
    public void isBlankTest() {
        String a = "";
        System.out.println(StringUtils.isBlank(a));
    }

    @Test
    public void indexTest() throws UnsupportedEncodingException {
        String deepLink = "wbmain://jump/job/fullTimeCate?params=%7B%22title%22%3A%22%E5%85%A8%E8%81%8C%E6%8B%9B%E8%81%98%22%2C%22showsift%22%3A%22true%22%2C%22list_name%22%3A%22job%22%2C%22autojump%22%3A%22false%22%2C%22url%22%3A%22https%3A%2F%2Fzprecommend.58.com%2Fapi%2Fjobindex%22%7D&pid=1461";
        int index1 = deepLink.indexOf("params=") + 7;
        int index2 = deepLink.indexOf("&", index1);

        String prefix = deepLink.substring(0, index1);
        String params = deepLink.substring(index1, index2);
        String postfix = deepLink.substring(index2, deepLink.length());

        System.out.println("prefix="+prefix);
        System.out.println("params="+params);
        System.out.println("postfix="+postfix);

        System.out.println(index1);
        System.out.println(index2);

        String flowidentify = "sdfsfsfdsf.sdfdsf.sdf";
        Object[] UTM_SOUCCE = new Object[]{"58un", 1};

        String decodeParams = URLDecoder.decode(params, "UTF-8");
        System.out.println("decodeParams="+decodeParams);
        JSONObject json = JSON.parseObject(decodeParams);
        System.out.println("json="+json);
        json.put("spm", new Object[]{flowidentify, 1});
        json.put("utm_source", UTM_SOUCCE);
        String newParams = json.toString();
        System.out.println("newParams="+newParams);
        String newDeepLink = prefix + URLEncoder.encode(newParams, "UTF-8") + postfix;
        System.out.println("newDeepLink="+newDeepLink);

    }

    @Test
    public void split2Test() {
        String price = "1567.23";
        String[] split = price.split("\\.");
        System.out.println(split.length);
        System.out.println(split[0]);
    }

    @Test
    public void byteTest() throws UnsupportedEncodingException {
        String name = "张三";
        String format = String.format("%-40s", name);
        System.out.println(format);
        System.out.println(format.getBytes("utf-8").length);
        System.out.println(name.length());

        System.setProperty("scf.serializer.basepackage","com.bj58");

        String a = "true";
        System.out.println(Boolean.valueOf(a));
        if (Boolean.valueOf(a)) {
            System.out.println("dddd");
        }

    }


}
