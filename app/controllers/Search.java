package controllers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Card;
import models.Category;
import models.Department;
import models.Staff;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import com.avaje.ebean.ExpressionList;

@Security.Authenticated(Secured.class)
public class Search extends Controller {

	public static Result search() {
		List<Card> cardList = Card.find.all();
		List<Department> departmentList = Department.find.all();
		List<Staff> staffList = Staff.find.all();
		List<Category> categoryList = Category.find.all();
		return ok(views.html.search.render(cardList,departmentList,staffList,categoryList));
	}

	public static Result indexsearch(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String[]> params = request().body().asFormUrlEncoded();
			ExpressionList<Card> sqlList= Card.find.where();

			if (!(params.get("selectName2_2")[0].equals(""))){
				Staff staff = Staff.find.where().eq("staff_name",params.get("selectName2_2")[0]).findUnique();
				sqlList=sqlList.eq("send_staff_id", staff.staff_id);
			}

			if (!(params.get("selectName1_2")[0].equals(""))){
				String send_staff=params.get("selectName1_2")[0];
				Staff staff2 = Staff.find.where().eq("staff_name",params.get("selectName1_2")[0]).findUnique();
				sqlList=sqlList.eq("get_staff_id", staff2.staff_id);
			}

			if (!(params.get("helped_date")[0].equals(""))){
				Date date = format.parse(params.get("helped_date")[0]);
				sqlList=sqlList.eq("helped_date",date);
				System.out.println(date);
			}

			if (!(params.get("category")[0].equals(""))){
				int category=Integer.parseInt(params.get("category")[0]);
				sqlList=sqlList.eq("category_id",category);
			}

			if(!(params.get("point")[0].equals(""))){
				int point=Integer.parseInt(params.get("point")[0]);
				sqlList=sqlList.eq("point",point);
			}

			List<Department> departmentList = Department.find.all();
			List<Staff> staffList = Staff.find.all();
			List<Category> categoryList = Category.find.all();
			List<Card> cardList = sqlList.findList();
			return ok(views.html.search.render(cardList,departmentList,staffList,categoryList));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return redirect(routes.Search.search());
	}
}