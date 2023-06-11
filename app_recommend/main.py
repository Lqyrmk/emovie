"""
:Description: 
:Author: lym
:Date: 2023/04/18/20:49
:Version: 1.0
"""
import pandas as pd
import redis
import jwt
import base64

from redis_utils.redis_config import redis_pool

from surprise import Dataset, Reader
from surprise import SVD
from surprise.model_selection import train_test_split

from fastapi import APIRouter, Body, Request
from mysql_utils.database import engine, Base

recommend_app = APIRouter()

Base.metadata.create_all(bind=engine)

redis_conn = redis.Redis(connection_pool=redis_pool)

@recommend_app.get("/")
async def index():
    return '垃圾实训不写了'

@recommend_app.post("/")
async def recommend_by_userid(request: Request, recommend_data=Body(None)):
    jwt_token = request.headers["token"]
    jwt_key = 'bHlt'
    decoded_str = base64.b64decode(jwt_key).decode('utf-8')
    user_id = '1'
    try:
        payload = jwt.decode(jwt_token, decoded_str, algorithms="HS256")
        user_id = '1'
        user_id = payload['sub']
    except jwt.exceptions.InvalidTokenError as e:
        print("Invalid token:", e)

    recommend_num = recommend_data["recommendNum"]
    # 写sql语句查询评分情况
    # 将查询出来的数据转换为pandas的数据格式
    sql = "select * from ratings"
    data = pd.read_sql(sql, engine)

    # 设置reader
    reader = Reader(rating_scale=(0, recommend_num))

    # 加载数据集
    dataset = Dataset.load_from_df(data[['user_id', 'movie_id', 'rating']], reader)

    # 划分数据集
    trainset, testset = train_test_split(dataset, test_size=0.2)

    # 训练模型
    algo = SVD()
    algo.fit(trainset)

    # 预测评分
    predictions = algo.test(testset)

    # 为用户推荐电影
    user_movies = data[data['user_id'] == user_id]['movie_id']
    user_unrated_movies = data[~data['movie_id'].isin(user_movies)]['movie_id']
    user_unrated_movies = list(set(user_unrated_movies))[:163949]
    user_unrated_movies_with_pred = [(movieId, algo.predict(user_id, movieId).est) for movieId in user_unrated_movies]
    user_unrated_movies_with_pred.sort(key=lambda x: x[1], reverse=True)
    top_n = user_unrated_movies_with_pred[:recommend_num]
    # print(f"向用户id为{user_id}的用户推荐的Top 5 电影：{top_n}")

    # for item in top_n:
    #     print(f"id为{item[0]}的电影，分数为{item[1]}")

    # 清空之前的推荐结果
    redis_conn.delete(f'recommend:top_n_movie:user:{user_id}')
    redis_conn.delete(f'recommend:top_n_score:user:{user_id}')
    for movie_item in top_n:
        redis_conn.rpush(f'recommend:top_n_movie:user:{user_id}', movie_item[0])
        redis_conn.rpush(f'recommend:top_n_score:user:{user_id}', movie_item[1])

    return {"data": "ok", "msg": f"用户id为{user_id}的用户推荐完毕！", "code": 200}
