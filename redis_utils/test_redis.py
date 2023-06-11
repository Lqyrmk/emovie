import redis

from redis_config import redis_pool

redis_conn = redis.Redis(connection_pool=redis_pool)

redis_conn.set('time_threshold', '300')
val = redis_conn.get('time_threshold').decode(encoding='utf-8')
val = int(val)
print(val)
print(val + 1)