package controllers;
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
public class Aggregate extends Controller {

	public static Result aggregate() {
		List<Card> cardList = Card.find.all();
		List<Department> departmentList = Department.find.all();
		List<Staff> staffList = Staff.find.all();
		List<Category> categoryList = Category.find.all();
		int a=0;
		return ok(views.html.aggregate.render(cardList,departmentList,staffList,categoryList,a));
	}

	public static Result indexsearch(){
	Map<String, String[]> params = request().body().asFormUrlEncoded();
	ExpressionList<Staff> sqlList= Staff.find.where();


	if (!(params.get("staff_code")[0].equals(""))){
		int staff_code=Integer.parseInt(params.get("staff_code")[0]);
		sqlList=sqlList.eq("staff_code", staff_code);
	}

	if (!(params.get("get_depart")[0].equals(""))){
		int department_id=Integer.parseInt(params.get("get_depart")[0]);
		sqlList=sqlList.eq("department_id",department_id);
	}

	if (!(params.get("staff_name")[0].equals(""))){
		int staff_name=Integer.parseInt(params.get("staff_name")[0]);
		sqlList=sqlList.eq("staff_name",staff_name);
	}


	List<Department> departmentList = Department.find.all();
	List<Staff> staffList_all = Staff.find.all();
	List<Category> categoryList = Category.find.all();
	Staff staffList = sqlList.findUnique();
	List<Card> cardList = Card.find.where().eq("get_staff_id",staffList.staff_id).findList();
	int staffcnt=Card.find.where().eq("get_staff_id",staffList.staff_id).findRowCount();

	return ok(views.html.aggregate.render(cardList,departmentList,staffList_all,categoryList,staffcnt));

	}
}
