# springBootDemo

#### 项目介绍
在SpringBoot框架下学习，整合其他技术
redis,mybatis,jdk8,Cache,Async,定时器，RestTemplate，thymeleaf，过滤器，
拦截器，swagger,动态代理，actuator，security，shiro，fastdfs的技术点

#### 项目打包
引入项目后，在pom.xml设置打包形式，.jar或war包。
1.在cmd下执行：mvn package完成打包
2 使用idea快捷方式，右侧Maven projects -> 顶部第6个按钮

#### 项目运行
在安装jdk1.8的环境下执行命令：java -jar jar名

#### 使用说明
在浏览器输入：http://ip:8888/swagger-ui.html并可以查看项目提供接口

#### 项目说明
源码地址：http://git.tuandai888.com/xiaowen/springboot-demo.git

master分支：主分支，学习分支

dev分支：基于2018年5月4号主分支创建

性能优化笔记：
1 尽量不使用String.split()截取分组(基于正则表达式,性能比较低)，使用apache的StringUtils.split(value,xx)代替
  使用StringUtils.isBlank(str)判断字符是否null或空白字符
2 当单个字符时使用''取代""：String str="hello"+"d" 改为 String str="hello"+'d'
  如果只是查找单个字符的话，用charat(0)代替startswith()  
3 避免在循环条件中使用复杂表达式for(int i=0;i<list.size();
每次执行都会执行list.size()，建议：for(int i=0,count=list.size();i<count;i++)
4 使用集合时明确集合大小的时候，初始化集合大小（不设初始大小会有默认初始值）
eg:List<String> list=new ArrayList(5)；ArrayList默认初始值10，超过这个值1.5扩容
   Map<Integer,String> map=new HashMap<>(10);hashMap未设置初始容量时容量为16，在集合存储超过16*0.75时扩容
   如果设置了初始值，hashMap在超过初始值时扩容。都是2陪扩容
5 根据数值确定执行方法性能对比：反射 < if else < switch case
  switch case:使用二分法查找，在switch case语句编译时，根据二分法判断初始case
  反射：通过class对象获取对象属性、方法，存在查询过程，性能低下 
6 计算的时候尽量使用移位操作：4*2  改为：4<<1   8/2 改为：8>>1
7 采用懒加载策略即在需要的时候才开始创建的策略:创建对象在靠近使用处定义
9 循环体内不要使用Try/Catch语句，应把Try/Catch放在循环最外层