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
public class Aggregate extends Controller {

	public static Result aggregate() {
		List<Card> cardList = Card.find.all();
		List<Department> departmentList = Department.find.all();
		List<Staff> staffList = Staff.find.all();
		List<Category> categoryList = Category.find.all();
		int a=0;
		return ok(views.html.aggregate.render(cardList,departmentList,staffList,categoryList,a,a));
	}

	public static Result indexsearch(){
		try{
			Map<String, String[]> params = request().body().asFormUrlEncoded();
			ExpressionList<Staff> sqlList= Staff.find.where();
			ExpressionList<Card> card_sqlList= Card.find.where();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			List<Department> departmentList = Department.find.all();
			List<Staff> staffList_all = Staff.find.all();
			List<Category> categoryList = Category.find.all();

			int staffcnt=0;
			int point_sum=0;

			String staff_name=params.get("selectName2")[0];
			sqlList=sqlList.eq("staff_name",staff_name);

			if (!(params.get("start_date")[0].equals(""))){
				Date date = format.parse(params.get("start_date")[0]);
				card_sqlList=card_sqlList.ge("helped_date",date);
			}

			if (!(params.get("end_date")[0].equals(""))){
				Date date = format.parse(params.get("end_date")[0]);
				card_sqlList=card_sqlList.le("helped_date",date);
			}


			Staff staffList = sqlList.findUnique();
			card_sqlList=card_sqlList.eq("get_staff_id",staffList.staff_id);
			staffcnt=Card.find.where().eq("get_staff_id",staffList.staff_id).findRowCount();


			List<Card> cardList = card_sqlList.findList();

			for(int a =0; a<cardList.size(); a++ ){
				point_sum=point_sum+cardList.get(a).point;
			}

			return ok(views.html.aggregate.render(cardList,departmentList,staffList_all,categoryList,staffcnt,point_sum));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return redirect(routes.Aggregate.aggregate());
	}
}
