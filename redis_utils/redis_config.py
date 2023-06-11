import redis

redis_pool = redis.ConnectionPool(host='', port=6379, password='', db=1)
