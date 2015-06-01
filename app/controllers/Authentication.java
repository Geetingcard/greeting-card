package controllers;

import models.*;

import play.data.Form;
import play.mvc.*;
import views.html.authentication.*;

import java.util.List;

public class Authentication extends Controller {
	public static class Login {
		public String usercode;
		public String password;

		public String validate() {
			if (authenticate(usercode, password)) {
				return null;
			}
			return "Invalid usercode and password";
		}

		private Boolean authenticate(String usercode, String password) {
			return Staff.authenticate(usercode, password);
		}
	}

	public static Form<Login> loginForm = Form.form(Login.class);

	public static Result index() {
		if (session("login") != null) {
			return ok("あなたは既に " + session("login") + "としてログインしています");
		}
		return ok(index.render(loginForm));
	}


	public static Result authenticate() {
		Form<Login> form = loginForm.bindFromRequest();

		if (form.hasErrors()) {
			return badRequest(index.render(form));
		} else {

			List<Card> cardList = Card.find.all();
			List<Staff> staffList = Staff.find.all();


			Login login = form.get();
			Staff staff_log = Staff.find.where().eq("staff_code",login.usercode).findUnique();
			String username = staff_log.staff_name;
			List<Card> mycardList = Card.find.where().eq("get_staff_id",staff_log.staff_id).findList();
			session("login", login.usercode);
//			return ok("ようこそ " + username + " さん!!");
			return ok(views.html.mypage.render(cardList,staffList,username,mycardList));
		}
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Authentication.index());
	}
}