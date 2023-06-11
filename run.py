import uvicorn
from fastapi import FastAPI
from app_recommend import recommend_app

app = FastAPI(
    title='推荐算法接口后端',
    description='使用Fastapi构建算法接口，此为接口文档',
    version='1.0.0',
    docs_url='/docs',
    redoc_url='/redocs',
)

app.include_router(recommend_app, prefix='/recommend', tags=['应用1'])


@app.get("/")
def index():
    return "this is home page"


if __name__ == '__main__':
    uvicorn.run('run:app', reload=True, workers=1, port=9000)
