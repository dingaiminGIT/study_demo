package javabase;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MediaSlotDtoTest {

    @Test
    public void test1() {
        DspMediaDto mediaDto = new DspMediaDto();
        List<DspSlotDto> slots = new ArrayList<>();
        DspSlotDto slot = new DspSlotDto();

        for (int i=0; i < 10; i++) {
            slot.setSlotId(i);
            slot.setSlotName(" "+i);
            slots.add(slot);
        }

        for (DspSlotDto dto : slots) {
            System.out.println(dto);
        }
    }
}
