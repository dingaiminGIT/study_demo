package function;

import dsp.Promotion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HolderTest {

    public static void main(String[] args) {
        Holder<List<Promotion>> holder = new Holder<>();

        ArrayList<Promotion> list = new ArrayList<>();
        list.add(new Promotion(1, "1"));
        list.add(new Promotion(2, "2"));
        list.add(new Promotion(3, "3"));
        list.add(new Promotion(4, "4"));
        holder.setT(list);

        Iterator<Promotion> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getPromotionId().equals(2)) {
                iterator.remove();
            }
        }
        /*list.stream().filter(promotion -> !promotion.getPromotionId().equals(2))
                .forEach(System.out::println);*/

        System.out.println("===============");

        List<Promotion> promotionList = holder.getT();
        promotionList.stream().forEach(System.out::println);
    }

    @Test
    public void test() {
        ArrayList<Promotion> list = new ArrayList<>();
        list.add(new Promotion(1, "1"));
        list.add(new Promotion(2, "2"));
        list.add(new Promotion(3, "3"));
        list.add(new Promotion(4, "4"));

        Holder<List<Promotion>> originPromotionListHolder = new Holder<>();
        List<Promotion> originPromotionList = new LinkedList<>();

        for (Promotion promotion : list) {
            originPromotionList.add(promotion);
        }
        originPromotionListHolder.setT(originPromotionList);

        Iterator<Promotion> iterator = list.iterator();
        while (iterator.hasNext()) {
            Promotion next = iterator.next();
            if (next.getPromotionId().equals(2)) {
                next.setPromotionName("过滤");
                iterator.remove();
            }
        }

        System.out.println("=========");

        List<Promotion> t = originPromotionListHolder.getT();
        t.stream().forEach(System.out::println);
    }
}
