使用Druid开源解析工具，对SQL查询语句解析，提取所有真实表名和表对应的字段名。    
    
以下是测试例子，你可以很容易得到结果：   
1.   
String sql = "select a aa,b bb,c cc from x xx";    
ZSelect select = new ZSelect();     
ZData data = select.parseSelect(sql);    
System.out.println(data);    
    
以上执行会输出如下结果   
TABLES:      
  @NAME:x      
   IsSelectAll:false   
   COLUMNS:[a][b][c]   
其中NAME是实际表名，IsSelectAll标识是否为*，COLUMNS是该表下所有真实列名。    
2.    
多表连接查询语句，对于有些列名，SQL解析层面难以做到匹配列对应真实表名.   
例： select a,b,c from x left join y on x.id=y.id;    
对于这样的情况，   
String sql = "select a,b,c from x left join y on x.id=y.id";   
ZSelect select = new ZSelect();    
ZData data = select.parseSelect(sql);    
System.out.println(data);   

以上执行会输出如下结果    
TABLES:    
  @NAME:y    
   IsSelectAll:false    
   COLUMNS:[id]    
  @NAME:x    
   IsSelectAll:false    
   COLUMNS:[id]    
UnknowedColumnsWithTable:    
  @TABLENAME:y   COLUMNS:[a, b, c]    
  @TABLENAME:x   COLUMNS:[a, b, c]    
其中，UnknowedColumnsWithTable表示不清楚表名和列名的对应关系，但是可以    
清楚的是，表x和表y可能含有a,b,c三个列中的几个或全部。   
