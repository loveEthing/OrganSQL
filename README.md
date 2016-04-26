使用Druid开源解析工具，对SQL查询语句解析，提取所有真实表名和表对应的字段名。 
＜/br＞
以下是测试例子，你可以很容易得到结果：＜/br＞
1.＜/br＞
String sql = "select a aa,b bb,c cc from x xx";＜/br＞
ZSelect select = new ZSelect();＜/br＞
ZData data = select.parseSelect(sql);＜/br＞
System.out.println(data);＜/br＞
＜/br＞
以上执行会输出如下结果＜/br＞
TABLES:＜/br＞
  @NAME:x＜/br＞
   IsSelectAll:false＜/br＞
   COLUMNS:[a][b][c]＜/br＞
其中NAME是实际表名，IsSelectAll标识是否为*，COLUMNS是该表下所有真实列名。＜/br＞
2.＜/br＞
多表连接查询语句，对于有些列名，SQL解析层面难以做到匹配列对应真实表名.＜/br＞
例： select a,b,c from x left join y on x.id=y.id;＜/br＞
对于这样的情况，＜/br＞
String sql = "select a,b,c from x left join y on x.id=y.id";＜/br＞
ZSelect select = new ZSelect();＜/br＞
ZData data = select.parseSelect(sql);＜/br＞
System.out.println(data);＜/br＞

以上执行会输出如下结果＜/br＞
TABLES:＜/br＞
  @NAME:y＜/br＞
   IsSelectAll:false＜/br＞
   COLUMNS:[id]＜/br＞
  @NAME:x＜/br＞
   IsSelectAll:false＜/br＞
   COLUMNS:[id]＜/br＞
UnknowedColumnsWithTable:＜/br＞
  @TABLENAME:y   COLUMNS:[a, b, c]＜/br＞
  @TABLENAME:x   COLUMNS:[a, b, c]＜/br＞
其中，UnknowedColumnsWithTable表示不清楚表名和列名的对应关系，但是可以＜/br＞
清楚的是，表x和表y可能含有a,b,c三个列中的几个或全部。＜/br＞
