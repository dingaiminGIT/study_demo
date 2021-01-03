package javabase;

import java.util.ArrayList;

public class DspMediaDto {

    private Integer mediaId;
    private String mediaName;
    private ArrayList<DspSlotDto> slots;

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public ArrayList<DspSlotDto> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<DspSlotDto> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "DspMediaDto{" +
                "mediaId=" + mediaId +
                ", mediaName='" + mediaName + '\'' +
                ", slots=" + slots +
                '}';
    }
}
