package collection;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MapTest {

    public static void main(String[] args) {


    }

    @Test
    public void test2334343() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag(1L, 1, 0.1d));
        tagList.add(new Tag(2L, 1, 0.1d));
        tagList.add(new Tag(3L, 1, 0.1d));
        tagList.add(new Tag(3L, 1, 0.1d));
        tagList.add(new Tag(3L, 1, 0.1d));

        Map<Long, Long> map = new HashMap<>();
        map.put(1L, 11L);
        map.put(2L, 12L);
        map.put(3L, 13L);


        Map<Long, Double> tagMap = new HashMap<>();
        for (Tag tag : tagList) {
            Long id = tag.getId();
            Long tagId = map.get(id);
            if (tagId != null) {
                tagMap.computeIfPresent(tagId, (k,v) -> v + tag.getWeight());
                tagMap.putIfAbsent(tagId, tag.getWeight());
            }
        }

        System.out.println(tagMap.size());
        tagMap.forEach((k, v) -> System.out.println(k + ":" + v));

        List<Long> tagIdList = tagMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        System.out.println("=========");
        tagIdList.forEach(i -> System.out.println(i));
    }

    @Test
    public void testMapSize() {
        Map<String, Map<String, Object>> topMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        /*map.put("1", Double.valueOf("1111"));
        map.put("3", Long.valueOf("333"));
        map.put("4", "333");*/
        topMap.put("0", map);
        Map<String, Object> result = topMap.get("0");

        System.out.println(result.get("333") == null);
        System.out.println(result.size());
    }

    @Test
    public void test1() {
        Map map = new ConcurrentHashMap<>();
        map.put("id", 1L);
        map.put("name", "ddded");

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
