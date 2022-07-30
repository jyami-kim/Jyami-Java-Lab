package hashing;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashingTest {

    @Test
    void testLocalCacheHashing() {
        //given
        Cache<Long, String> stringLocalCache = CacheBuilder.newBuilder().maximumSize(1000).build();

        stringLocalCache.put(-2369178130L, "put1");
        stringLocalCache.put(100000000L, "put2");
        //when

        String put1 = stringLocalCache.getIfPresent(-2369178130L);
        String put2 = stringLocalCache.getIfPresent(100000000L);
        //then
        assertEquals(put1, "put1");
        assertEquals(put2, "put2");
    }

}
