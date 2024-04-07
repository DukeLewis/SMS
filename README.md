# 超市管理系统  

## 0. pom文件
总pom文件中规定了springboot、mybatis、redis、swagger、mybatis-plus、fastjson、log4j2的所有版本号

后续的模块中pom文件直接继承总pom文件，引用依赖只需要引入依赖即可，不用再写版本号。

需要新增的依赖，需要先在总pom文件中规定版本号，然后再在需要使用该依赖的模块中的pom文件中引入。


## 1.模块介绍
- server:触发器模块（controller）
- common：公共模块（例如一些工具类，枚举常量）
- model：实体模块（各种po，domain，vo，dto），domain是完整映射数据库数据的实体，entity是根据需求封装的部分数据库数据的实体，vo是返回给前端的视图对象，dto是前端传递给后端的视图对象
- service：业务模块（service）
- repository：数据访问模块（包含对数据库和缓存之类的数据操作）


