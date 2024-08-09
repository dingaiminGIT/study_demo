package leetcode;

/**
 * 猿辅导 2 面
 */
public class CodeTest {


    /*private Cache cache = new Cache();
    private DB db;
    private Lock lock;
    private int retryCount;
    *//**
     * 从缓存中查询 key
     * @param key
     * @return
     *//*
    public String getValue(String key) {
        String value = cache.get(key);
        if(Objects.isNull(value)) {
            boolean lockReslut = lock.setNx(key, "1", expireTime);
            if(lockReslut) {
                // 查询db
                String dbValue = db.query(key);
                cache.put(key, dbValue, expireTime);
                return dbValue;
            } else {

                for(int i=0; i<= retryCount;i++) {
                    String value = cache.get(key);
                    if (Objects.isNull(value)) {
                        Thread.sleep(200);
                        continue;
                    }
                    return value;
                }


            }

        }
        return value;
    }*/
}
