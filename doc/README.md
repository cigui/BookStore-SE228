# BookStore Iteration 4
## 部署相关
- 平台：tomcat v8.5 Server & MySQL Server 5.7 & MongoDB Server 3.4
- 在src\main\resources\applicationContext.xml处设置mongoDB的数据库名字、mysql的数据库名字以及用户、密码。
- 数据库初始化文件为bookstore.sql，请据此建立数据库。
- 将某个用户的role设置为1用以表示这个用户为管理员。
- 请将src/main/webapp/css/images/404.png提前存入mongoDB的GridFS系统以获得更好的浏览体验（获取图书详细信息时若该图书暂无封面，则会从mongoDB中获取404.png作为代替）。

## 项目结构
- 采用java开发，使用Struts作为MVC框架，Spring作为IoC框架，Hibernate作为ORM框架。

## 功能实现
1. 注册、登录、登出、图书列表及搜索图书
    - 注册、登录、登出均对session进行操作，在每个jsp页面中均使用struts提供的标签库获取session中的'logined'变量判断用户是否已经登录
    - 另外，也在session中存入userName, userId, isAdmin, 方便页面为用户提供合适的内容
    - 首页访问链接为/index（不过在struts.xml中配置了默认action为index，所以访问未定义的action时也可以默认转到首页。），并经过IndexAction在request中设置属性books（图书列表），在jsp页面中获取books并使用BootStrap提供的dataTables插件呈现书目概要信息
    - BootStrap提供的dataTables插件中已经包括了搜索功能，但姑且还是做了一个搜索图书的action，支持通过isbn、书名或作者进行搜索。
2. 管理员对各种信息的CRUD
    - 基本和前三个迭代差不多，不过数据库结构有所修改。
    - 前端页面沿用了第一次迭代的代码，使用jquery EasyUI的edatagrid呈现数据
    - edatagrid要求后端传输json数据到前端，所以引入了struts的json扩展包，在struts.xml中配置extends="json-default"以及result type="json"实现将action中的map或list转换成json传到前端
    - Book相关信息存储在MongoDB中，封面信息通过MongoDB提供的GridFS文件系统进行存储（引入了Spring data mongodb的jar包，通过其定义的MongoTemplate和GridFsTemplate进行数据操作）
3. 添加书本到购物车中并对特定书本下单
    - 在首页的书目列表提供了加入购物车和立即购买的按钮
    - 立即购买的功能只是对一本书进行下单，对书本指定数量后生成订单条目并生成只含一个条目的订单存入数据库。
    - 关于购物车功能：
        - 在数据库中用ShoppingCartItems表示购物车中的物品，其属性包括用户id、书本isbn（前两个属性作为复合主键）、数量
        - 在首页点击添加购物车按钮，弹出提示框输入数量点击确定后生成ShoppingCartItem存入数据库。（提示框使用bootbox插件呈现）
        - 存入过购物车的书本再次添加会提示已经在购物车中
        - 首页提供链接到ListShoppingCartAction的按钮，这个action在request中存入购物车条目信息以及相关图书信息
        - 购物车页面用表格呈现购物车条目信息，，每个条目提供修改条目数量和删除条目的按钮以及复选框（只有选中的条目在点击下单时会被存入订单）
        - 购物车页面对指定书目下单的功能的实现：点击下单时会检查页面中每个复选框，然后把被选中的条目存入一个列表中，把列表转成json传到action中，再用gson把json还原成列表获得条目并下单。
    - 关于查看订单页面：通过ListOrdersAction向页面提供必要的信息，在页面中使用bootstrap提供的带列表的面板类呈现订单信息。
4. 销售数据统计：
    - 通过StaticsInfoAction向页面提供必要的信息，在页面中用三个表格表示三种查询数据的方式。
    - 每种方式最终数据都呈现在staticsresults.jsp中，包括了订单数、卖出书本总数以及总金额。
    - 对指定用户、种类查询数据的功能的表单提供了添加选择数据的按钮，并使用jquery的clone和before方法动态添加选择框。
5. 使用ajax和json获取书籍详细信息
    - 在首页书目列表中，每个书目标题都作为一个按钮，绑定了一个事件，并通过jquery的getJSON方法从后台获取数据呈现在pre和img标签中
    - 顺便使用了jquery提供的fadeIn、fadeOut方法做小小的美化