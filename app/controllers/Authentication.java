package controllers;

import models.*;

import play.data.Form;
import play.mvc.*;
import views.html.authentication.*;

public class Authentication extends Controller {
	public static class Login {
        public String username;
        public String password;

        public String validate() {
            if (authenticate(username, password)) {
                return null;
            }
            return "Invalid username and password";
        }

        private Boolean authenticate(String username, String password) {
            return Staff.authenticate(username, password);
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
            Login login = form.get();
            session("login", login.username);
            return ok("ようこそ " + login.username + " さん!!");
        }
    }

    public static Result logout() {
    session().clear();
    return redirect(routes.Authentication.index());
	}
}