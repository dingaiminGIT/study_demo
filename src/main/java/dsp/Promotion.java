package dsp;

public class Promotion {

    private Integer promotionId;
    private String promotionName;

    public Promotion(Integer promotionId, String promotionName) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promotionId=" + promotionId +
                ", promotionName='" + promotionName + '\'' +
                '}';
    }
}
