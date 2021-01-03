package spring;

import org.apache.lucene.queryparser.flexible.standard.QueryParserUtil;
import org.springframework.beans.BeanUtils;

public class Query {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Query query = new Query();
        query.setId("1");
        query.setName("ddd");


        Query newQuery = new Query();
        BeanUtils.copyProperties(query, newQuery);
        newQuery.setName("ccc");
        System.out.println(newQuery);
        System.out.println(query);
    }
}
