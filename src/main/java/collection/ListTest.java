package collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTest {

    private List<Long> dmpZPTagIdList = Stream.of(2L,3L,4L,5L).collect(Collectors.toList());

    @Test
    public void sortTest() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag(1L, 1, 0.1d));
        tagList.add(new Tag(2L, 1, 0.2d));
        tagList.add(new Tag(3L, 1, 0.3d));
        tagList.add(new Tag(4L, 1, 0.4d));
        tagList.add(new Tag(5L, 1, 0.5d));

        List<Long> idList = Stream.of(1L,2L,3L,4L,5L,6L).collect(Collectors.toList());


        List<Long> list = tagList.stream().filter(tag -> dmpZPTagIdList.contains(tag.getId()))
                .sorted(Comparator.comparing(Tag::getWeight).reversed())
                .map(tag -> tag.getId())
                .collect(Collectors.toList());

        System.out.println("排序后");
        list.forEach(System.out::println);

        System.out.println("========");
        List<Long> list1 = list.subList(3, list.size());
        list1.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> sizeTable = new ArrayList<Integer>();
        /*for (int i = 16; i < 512; i += 16) {
            sizeTable.add(i);
        }

        for (int i = 512; i > 0; i <<= 1) {
            sizeTable.add(i);
        }*/
        sizeTable.add(1);
        sizeTable.add(2);
        sizeTable.add(3);

        sizeTable.stream().forEach(System.out::println);

        prosess(sizeTable);
        System.out.println(".................");

        sizeTable.stream().forEach(System.out::println);
        System.out.println("大小：" + sizeTable.size());


    }

    private static void prosess(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        newList.add(3);
        newList.add(2);

        list.clear();

        List<Integer> a = new ArrayList<>();
        a.add(4);

        list.addAll(newList);
        list.addAll(a);
        List<Integer> c = new ArrayList<>(6);
        list.addAll(c);

    }

    class Tag {
        private Long id;
        private Integer type;
        private Double weight;

        public Tag(Long id, Integer type, Double weight) {
            this.id = id;
            this.type = type;
            this.weight = weight;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Tag{" +
                    "id=" + id +
                    ", type=" + type +
                    ", weight=" + weight +
                    '}';
        }
    }

}
