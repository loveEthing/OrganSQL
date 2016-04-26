import com.zl.sql.ZData;
import com.zl.sql.ZSelect;
public class Test {
	public static void main(String[] args) throws Exception {
		String sql = "select x.a,y.b,c from x left join y on x.id=y.id";
		ZSelect select = new ZSelect();
		long start = System.currentTimeMillis();
		ZData data = select.parseSelect(sql);
		System.out.println(data);
		long end = System.currentTimeMillis();
		System.out.println("解析用时:"+(end-start)+"ms");
	}
}
