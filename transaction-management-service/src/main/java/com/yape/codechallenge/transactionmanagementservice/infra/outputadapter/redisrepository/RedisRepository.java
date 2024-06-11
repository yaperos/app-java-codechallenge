package com.yape.codechallenge.transactionmanagementservice.infra.outputadapter.redisrepository;

import com.yape.codechallenge.transactionmanagementservice.infra.outputport.QueryRepository;
import com.yape.codechallenge.transactionmanagementservice.infra.utils.ConstantsUtils;
import com.yape.codechallenge.transactionmanagementservice.infra.utils.ConvertUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class RedisRepository implements QueryRepository {
    final
    RedisTemplate<String, Object> redisTemplate;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void  save(Map<String, Object> reg, Class<?> clazz) {
        redisTemplate.opsForHash().put(
                getHashFromClass( clazz ),
                reg.get(ConstantsUtils.TransactionMapper.TRANSACTION_EXTERNAL_ID),
                ConvertUtils.map2Jsonstring( reg )
        );
    }

    @Override
    public Map<String, Object> getById(String id, Class<?> clazz) {
        return ConvertUtils.jsonstring2Map(
                (String) redisTemplate.opsForHash().get(
                        getHashFromClass( clazz ),
                        id
                )
        );
    }

    private String getHashFromClass( Class<?> clazz ) {
        return clazz.getName().replace('.', '_');
    }
}
