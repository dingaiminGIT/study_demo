package javabase;

public class DspSlotDto {

    private Integer slotId;
    private String slotName;

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    @Override
    public String toString() {
        return "DspSlotDto{" +
                "slotId=" + slotId +
                ", slotName='" + slotName + '\'' +
                '}';
    }
}
