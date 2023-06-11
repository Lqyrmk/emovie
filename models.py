from sqlalchemy import Column, String, Integer, DateTime, ForeignKey, func
from mysql_utils.database import Base


class User(Base):
    # 表名
    __tablename__ = 'user'
    # 表结构
    user_id = Column(Integer, primary_key=True, index=True, autoincrement=True, comment='工号')
    username = Column(String(45), unique=True, nullable=False, comment='用户名/账号')
    password = Column(String(45), nullable=False, default='12345', comment='密码')
    gender = Column(String(45), nullable=False, comment='性别')
    email = Column(String(45), unique=True, comment='邮箱')

    def __init__(self, user_id, username, password, gender, email):
        self.user_id = user_id
        self.username = username
        self.password = password
        self.gender = gender
        self.email = email

