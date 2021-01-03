package exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.elasticsearch.ElasticsearchException;

public class Test {

    public static void main(String[] args) {
        ElasticsearchException e =  new ElasticsearchException("sdsda");
        //NoSuchMethodException e = new NoSuchMethodException();
        //NullPointerException e = new NullPointerException();
        String fullStackTrace = ExceptionUtils.getFullStackTrace(e);
        System.out.println(fullStackTrace);
    }
}
