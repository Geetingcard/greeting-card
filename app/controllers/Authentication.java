package controllers;

import java.util.List;

import models.Card;
import models.Staff;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.authentication.index;

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

			List<Card> cardList = Card.find.where().orderBy("helped_date desc").findList();
			List<Staff> staffList = Staff.find.all();
			List<Card> daihyo_cardList = Card.find.where().eq("point", 3).findList();

			Login login = form.get();
			Staff staff_log = Staff.find.where().eq("staff_code",login.usercode).findUnique();
			String username = staff_log.staff_name;

			List<Card> mycardList = Card.find.where().eq("get_staff_id",staff_log.staff_id).orderBy("helped_date desc").findList();
			session("login", login.usercode);
//			return ok("ようこそ " + username + " さん!!");
			return ok(views.html.mypage.render(daihyo_cardList,cardList,staffList,username,mycardList));
		}
	}

	public static Result mypage() {
		if (session("login") != null) {

			List<Card> daihyo_cardList = Card.find.where().eq("point", 3).findList();List<Card> cardList = Card.find.where().orderBy("helped_date desc").findList();
			List<Staff> staffList = Staff.find.all();
			Staff staff_log2 = Staff.find.where().eq("staff_code",session("login")).findUnique();
			String username2 = staff_log2.staff_name;
			List<Card> mycardList2 = Card.find.where().eq("get_staff_id",staff_log2.staff_id).orderBy("helped_date desc").findList();
			return ok(views.html.mypage.render(daihyo_cardList,cardList,staffList,username2,mycardList2));
		}
		return ok(index.render(loginForm));
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Authentication.index());
	}
}