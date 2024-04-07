# 超市管理系统  

## 0. pom文件
总pom文件中规定了springboot、mybatis、redis、swagger、mybatis-plus、fastjson、log4j2的所有版本号

后续的模块中pom文件直接继承总pom文件，引用依赖只需要引入依赖即可，不用再写版本号。

需要新增的依赖，需要先在总pom文件中规定版本号，然后再在需要使用该依赖的模块中的pom文件中引入。


## 1.模块介绍
- server:触发器模块（controller）
- common：公共模块（例如一些工具类，枚举常量）
- model：实体模块（各种po，domain，vo，dto）
- service：业务模块（service）
- repository：数据访问模块（包含对数据库和缓存之类的数据操作）


