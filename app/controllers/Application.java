package controllers;
import play.mvc.*;
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

}