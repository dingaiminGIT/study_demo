package date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import utils.RegexHelper;
import utils.StringBase64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

public class Base64Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String srcPrice = "27";
        byte[] encode = Base64.getEncoder().encode(srcPrice.getBytes());
        String encode1 = URLEncoder.encode(new String(encode), "UTF-8");
        System.out.println(encode1);

        String price = new String(Base64.getDecoder().decode(URLDecoder.decode(encode1, "UTF-8")));
        System.out.println(price);
        long l = Long.parseLong(price.trim());
        System.out.println(l);

    }

    @Test
    public void activityTest() {
        String url = "https://jumpluna.58.com/adclk?target=pZwY0Zn-nYD-nbm-nbu1pAqzIa3draOWUvY-nbulu7qVFHFAP1TvmWb1m1NVPWbdPBYYn1IbsH9YPW0VnHT1Pj03mhcLnyFBFHFAFHPAIA7zuvRYFHPDug-OIvm-nY7xgvOvUvYVnHmVXAIogvGfpyIxrjmLnWDYn1nvP1cOP1D-nWu-UhE-nYw-UhEKTyndsvOJRMIcNy3vmWnYpW-anYIdEy0q5EDQnWn8nHcdsWcdna3QPW0KnT7Bn10zmW7-nhwWrj6-nHF-nW6BmHFbujubmH--m10OPTDQn9DQTNORHDQxNdKPTHN3Iy3KnHcvrHcdPjnznWTYnWmdrjcQnW0QPj0drHNYTHnKnTDQnHnkPWNdnWNvnH0krHbOrjT3THn3nHbdnjczrjDOPWDdTHTKnTDQsWTKnE7_URq_pgPYTgKW07tQrjT1gvn_UA-1I7qbpgPxug6YgYc_UZR8mRq6uZ6xmyFxXyR_UAqLgYD_UARMUdq6u7qKsAuWgv-8uhqxmywxmyFxEBQ_pgPYgLPf0MwxmyFxEiQCIy78uL--gvQG0LwxnWTQrjTdnWFxEiQ_pgPYgL-3gv7BgYc_UA-1I7qlu7q-XZwxEBQbugw6pyQxXg6xmyFxEBQ_pgPYgvPCpyQbgvR3I7qasAI6UhGGmLKWg1cknH0kPjDzgYcK&-15=20&plat=m&psid=126925432204265821271475954&entinfo=38195022819615_0";

        String base64Target = RegexHelper.getTargetFromUrl(url);
        String target = StringUtils.defaultString(StringBase64.bc_base64_dec(base64Target));
        System.out.println(target);
        String[] param = target.split("\001", -1);
        String userId = param[4];
        String dispcateId =  param[6];
        String displocalId = param[7];
        String price = param[16];

        System.out.println(userId);
        System.out.println(dispcateId);
        System.out.println(displocalId);
        System.out.println(price);
    }

    @Test
    public void listTset() {
        //String url = "https://jumpluna.58.com/adclk?target=pZwY0Zn-nYD-nbm-nbu1pAqzIa3draOWUvY-nbulu7qVFHFAPHEdmHFbuH0VrAcQniYYrjK-syDvmHcVuWEQn1b1njw6uyDLFHFAFHPAIA7zuvRYFHPDug-OIvm-nY7xgvOvUvYVnHmVXAIogvGfpyIxrjmLnjTYP1DvnjD1P1N-nWu-UhE-nYw-UhEKTyndsvOJRMIcNy3vmWnYpW-anYIdEy0q5EDQnWn8nHcdsWcdna3QPW0KnT7Bn10zmW7-nhwWrj6-nHF-nW6BmHFbujubmH--m10OPTDQn9DQTNORHDQxNdKPTNORHDQxRRwPTHD1Pj0knHnYrHckPjcvPHbQP10drHEOrHNOnkD1THTKnHD1njEdnjEOPjb3rHc3n1nzrTD1rjDLP1TQPH9LPHcQPTDkTHTKni3kTHDKUAdxUA-1IT7bUgKxuvRfg1c_uhPxUhR60hFOgvQG0LwxEBQhmyOMmv66U-q_pgPYg1c_0APkg1D3njPxmzQ_IyO6gv7bX7q6m-qOuyQ_ULIxEiQ_uyIfgv7bgYD_uhPxpyOhUdq6u7q6m-qasA6dmyOMXyRxUA-1I7qjsAQG0LwxXg6xmyFxEBQ-0MPCULRWpARxIh-kgvQG0LwxEiQlpA7f0A-8gvQG0LwxEiQ_IyO6gv7bX7q6m-qCULR1uRqjsAQG0LwxuA-1gvR3I7qasAQG0Lwx0vqzI7q6m-qKsA6dmyOMXyRxUA-1I7tznjD3njNzn-qKsAQG0LwxXhwxug6YgYc_uARYmy-_gL-3gv7BgYc_pZR6UhIOuRqhmyOMXA-8uMRLIRq_pgPYgYc_UA-1I7qWpA-_u7q-XZwxEBQMmyOJpyPkmdtznjDLnjEQn-qaTE&-15=20&plat=m&psid=134701349204265917759499593&entinfo=38177015875214_0";
        String url = "https://jumpluna.58.com/click?target=pZwY0Zn-nYD-nbm-nbuJpyOMsWN3shPfUiNzwh7biMRV0aN1wh7bRZ-kuiN1wjn-nWuYmgFMugE-nYwkyMIun7G8UZPl0RqFXNtv0dIrnLPCNAuRpg7ZRy6duMnQUWP8iAFnNACkHLFJnj7EiDRv0vGDHdKcmbqERvdbND6WpdKJmLuERYE1NACknv3QUH7EnyFbNZGoNyOcUhVEnNE10hGWyRKcRDqEnNOwU-IBIMPpNAVRiAwzNbOwUhIbNDRciAV2X7GoiWRPN--RNMDQRNQiXhdvHM7csRF8ijIQN-FDyRqAp7Kx0Z-EULKOwg78nidgUM-7uZRgm-u8pZNvUMGuyyOJPbF1iAF5URI7RhYQmMGEpbOlNZ-8NyOJwWuApZIZnDQsXAdvPd-di7-wUW7BpdKaIyFkudKouLuwuhdvPdtdiDwCRN7QHgRvsH6MH7cQIyIAXZKOwg78mgRB0AIEpvIvNjuFENOQUb68HvOJUy6dEiYQnjIQgdRvNjuRp--wwh6EgLKONW6FPL7bnZuiXhIvsycdiDw5UhCkHLFcnZuAp7c3iNDVyR6OsycdiA31Ub6BH7KJnDqzpWTQND67IbuPNW7duYuZIyGuNgFcRDQziAFnNDFdsRRPIYIFyBduyAIssHRcRA6dXNqu0AILHvIvNjuFENOQUb6DXh3Qmh6dXNqu0AILHvIvNyuVIWIxPN6rH7TQmh6dEiYQnjIQygKOuaYdiDwbND9OyyOgnj78iAOlUhGNpjKK0H7kuLIZRgm10yO6IyVVuYmvRN6upjKKNHuFEgRhnA6u0yO6IRqkudKuyA670RRKuZ6REiYQiRGZmBNzPhR8uaN1wAR8uTDKm1NfUh3QXbtYU1-KIbIhUAGlpjIKu1YqTHDksWcdnB3QrjT8PTDkTE7UrHczPak9nHnOnjuITR_QgE7rRNQngdPEHE7rRNQngdRNHEDQrHNOrHmvPHNznjEzPWmQrjELrjnvn10OPH0KnkDQrHTLrHbLP9DQnHnkP1D3rjcYPHTOP1NQnWbvTHn3nHbLPj0Orj01PHEvTHcKnTDQsWTKnE7_URq_pgPYTywV07qMuyqxnBQCUvd-gLPYXyQ-gvq_uaQlpA7f0A-8gvQG0LwxEiQ_IyO6gv7bX7q6m-qJUvFxEiQ6IgwfgvR30ARzpyd-UMwxmgRYUdqYmgFMugw-u7q-XZKxmyFxrHczP7tQn-tQnjn_ugF1pAqdmv6-gLuG07q_pgPYgYc_pZR6UhIOuRq_pgPYg1cknH9kPHczgYD_pZR6UhIOuRq_pgPYgYn_0gR6UMGCpRqfUAwxIhRz0v-fUBQCIy78uL--gvu6UhI3pyOhIgIdgvQG0LwxE9DKsE78IyQ_TiYKsEDVTHcKnEDV&-15=20&plat=m&psid=195996655204266184783637957&entinfo=38197479873546_0";

        String base64Target = RegexHelper.getTargetFromUrl(url);
        String target = StringUtils.defaultString(StringBase64.bc_base64_dec(base64Target));
        String[] param = target.split("\001", -1);
        String userId = param[4];
        String dispcateId =  param[6];
        String displocalId = param[7];
        String price = param[16];

        JSONArray displocalJsonArray = JSON.parseArray(displocalId);
        JSONArray dispcateJsonArray = JSON.parseArray(dispcateId);
        String localString = displocalJsonArray.getString(displocalJsonArray.size() - 1);
        String dispacateString = dispcateJsonArray.getString(dispcateJsonArray.size() - 1);
        System.out.println("localString:" + localString);
        System.out.println("dispacateString:" + dispacateString);

        System.out.println(userId);
        System.out.println(dispcateId);
        System.out.println(displocalId);
        System.out.println(price);
    }
}
