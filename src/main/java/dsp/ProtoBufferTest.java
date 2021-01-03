package dsp;

public class ProtoBufferTest {

    public static void main(String[] args) {

        com.dsp.RTBParam.BidResponse.Bid.Creative.Builder creativeBuilder = com.dsp.RTBParam.BidResponse.Bid.Creative.newBuilder();
        creativeBuilder.setId("2333");
        creativeBuilder.setStyleId(333);
        creativeBuilder.setPromotionType(7);
        creativeBuilder.setDeepLink("ddddddddd");


        com.dsp.RTBParam.BidResponse.Bid.Builder bidBuilder = com.dsp.RTBParam.BidResponse.Bid.newBuilder();
        bidBuilder.setId("1");
        bidBuilder.setImpId("2");
        bidBuilder.setPrice(4);
        bidBuilder.setCreative(creativeBuilder);
        bidBuilder.setPromotionId(3);

        creativeBuilder.setDeepLink("ccccccccc");

        System.out.println(bidBuilder.toString());
    }
}
