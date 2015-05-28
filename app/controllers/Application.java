package controllers;
import play.*;
import play.mvc.*;
import play.data.*;

import java.text.ParseException;
import java.util.Arrays;
import java.text.SimpleDateFormat;

import models.Card;
import models.Staff;

import java.util.Date;
import java.util.List;
import java.util.Map;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import models.*;
public class Application extends Controller {

	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(index.render());
	}

	public static Result login() {
		return ok(login.render(Form.form(User.class)));
	}

	public static Result authenticate() {
		Form<User> loginForm = Form.form(User.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		}
		session().clear();
		session("name", loginForm.get().name);
		return redirect(routes.Application.mypage());
	}


	public static Result mypage(){

		return ok(mypage.render(""));
	}


	public static Result addUser() {
		User user = User.find.where().eq("name", "admin").findUnique();
		if (user == null) {
			User.create("admin", "password123");
		}
		return redirect(routes.Application.login());
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Application.login());

	}

	public static Result input() {
		return ok(views.html.input.render());
	}

	public static Result createCard() {
		try{
			Staff newStaff = new Staff();
			Staff newStaff2 = new Staff();
			Category newCategory = new Category();
			Card newCard = new Card();
			Map<String, String[]> params = request().body().asFormUrlEncoded();
			newStaff.staff_id=Integer.parseInt(params.get("get_staff_id")[0]);
			newStaff2.staff_id=Integer.parseInt(params.get("send_staff_id")[0]);
			newCategory.category_id=Integer.parseInt(params.get("category_id")[0]);
			System.out.println(params.get("helped_date")[0]);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(params.get("helped_date")[0]);

			newCard.card_id=Card.find.where().orderBy("card_id").findRowCount()+1;
			newCard.get_staff_id=newStaff;
			newCard.send_staff_id=newStaff2;
			newCard.point=Integer.parseInt(params.get("point")[0]);
			newCard.help_detail=params.get("help_detail")[0];
			newCard.thanks_word=params.get("thanks_word")[0];
			newCard.helped_date=date;

			newCard.category_id=newCategory;
			//		Card newCard = Form.form(Card.class).bindFromRequest().get();
			newCard.save();
			return redirect(routes.Application.input());
		} catch (ParseException e) {
			return null;
		}

	}
}