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
		return ok(login.render(Form.form(Staff.class)));
	}

	public static Result authenticate() {
		Form<Staff> loginForm = Form.form(Staff.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		}
		session().clear();
		session("username", loginForm.get().username);
		return redirect(routes.Application.mypage());
	}


	public static Result mypage(){
		List<Card> cardList = Card.find.all();
		List<Staff> staffList = Staff.find.all();
	    return ok(mypage.render(cardList, staffList));
	}


	public static Result logout() {
		session().clear();
		return redirect(routes.Application.login());

	}

}