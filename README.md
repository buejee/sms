# sms
java9 module program demo

本项目代码按照慕课网视频教程《Java9之模块系统》而来，代码亦根据课程视频敲下来。虽然达不到课程效果，但是基本思路是对的。
本项目代码对于课程视频有些许改动，比如这里是在eclipse下编码，有些设置有些变化：

1、如pom.xml中加入了其他模块的依赖，就不需要再在项目属性中的buildpath下再将项目作为Modulepath加入到工程里面。

2、另外，我这里对于实体保存这里，会因为没有实现Serializable接口而报错，故实体Entity做了implements Serialiazable的处理。
```
Caused by: java.io.NotSerializableException
```
3、在eclipse下进行调试，数据保存的时候，默认会因为没有.\data\Student\目录而报错，所以需要在runtime目录下新建data\Student这个两级目录。我这里对代码进行了修改，
如果查找实体类发现没有对应的目录，就创建:
```java
private Path getEntitiesPath(Class<?> type) {
		Path path= dataPath.resolve(type.getSimpleName());
		if(Files.notExists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
}
```
4、runtime工程在运行的时候，会报一个错误:
```
class sms.runtime.CommandRunner (in module sms.runtime) cannot access class sms.service.impl.StudentServiceImpl (in module sms.service) because module sms.service does not export sms.service.impl to module sms.runtime
```
根据提示也可以看出，service工程没有exports sms.service.impl这个路径，解决办法就是在service工程的module-info.java配置中增加一行配置:
```
exports sms.service.impl;
```

构建镜像环境：linux+jdk9
```
git clone https://github.com/buejee/sms.git
cd sms
mvn package
cd runtime/target/runtime-1.0-dist-dir/modules
jlink -p .:/usr/java/jdk-9/jmods --add-modules sms.runtime --output /data/sms-output --launcher sms=sms.runtime/sms.runtime.CommandRunner
```

运行截图：
![runtime](https://github.com/buejee/sms/blob/master/src/main/resources/runtime.png?raw=true "运行截图")
