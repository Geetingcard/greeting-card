package controllers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLSession;

import models.Card;
import models.Category;
import models.Department;
import models.Staff;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Input extends Controller {

	public static Result input() {
		List<Department> departmentList = Department.find.all();
		List<Category> categoryList =Category.find.all();
		return ok(views.html.input.render(departmentList,categoryList));
	}

	public static Result createCard() {
		try{
			Staff newStaff = new Staff();
			Staff newStaff2 = new Staff();
			Category newCategory = new Category();
			Card newCard = new Card();
			Map<String, String[]> params = request().body().asFormUrlEncoded();
			newStaff.staff_id=Integer.parseInt(params.get("get_staff_id")[0]);
			newStaff2=Staff.find.where().eq("staff_code",session().get("login")).findUnique();
			newCategory.category_id=Integer.parseInt(params.get("category_id")[0]);
			System.out.println(params.get("helped_date")[0]);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(params.get("helped_date")[0]);

			newCard.card_id=Card.find.where().orderBy("card_id").findRowCount()+1;
			newCard.get_staff=newStaff;
			newCard.send_staff=newStaff2;
			newCard.point=Integer.parseInt(params.get("point")[0]);
			newCard.help_detail=params.get("help_detail")[0];
			newCard.thanks_word=params.get("thanks_word")[0];
			newCard.helped_date=date;

			newCard.category=newCategory;
			//		Card newCard = Form.form(Card.class).bindFromRequest().get();
			newCard.save();
			return redirect(routes.Input.input());
		} catch (ParseException e) {
			return null;
		}

	}
}