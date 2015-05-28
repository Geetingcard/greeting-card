package controllers;
import play.*;
import play.mvc.*;
import play.data.*;


import java.util.Arrays;
import java.text.SimpleDateFormat;
import models.Card;
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
		return redirect(routes.Application.index());
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
		//    	List<Card> cardList = Card.find.all();
		int idnum=Card.find.where().orderBy("card_id").findRowCount();
		return ok(views.html.input.render(idnum));
		//        return ok(input.render(cardList));
		//        Card card1=new Card();
		//        card1.card_id=1;
		//        card1.get_staff_id=0001;
		//        card1.send_staff_id=0002;
		//        card1.point=2;
		//        card1.help_detail="デバッグの件です！";
		//        card1.thanks_word="先日はありがとうございました。おかげで作業を進めることができます";
		//        card1.helped_date=new Date();
		//        card1.category_id=1;
		//        card1.save();
		//		return TODO;
	}

	public static Result createCard() {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Map<String, String[]> params = request().body().asFormUrlEncoded();
			Card newCard = new Card();
			newCard.card_id=Card.find.where().orderBy("card_id").findRowCount()+1;
			newCard.get_staff_id=Integer.parseInt(params.get("get_staff_id")[0]);
			newCard.send_staff_id=Integer.parseInt(params.get("send_staff_id")[0]);
			newCard.point=Integer.parseInt(params.get("point")[0]);
			newCard.help_detail=params.get("help_detail")[0];
			newCard.thanks_word=params.get("thanks_word")[0];
			newCard.helped_date=sdf.parse(params.get("helped_date")[0]);
			newCard.category_id=Integer.parseInt(params.get("category_id")[0]);
			//    	    Card newCard = Form.form(Card.class).bindFromRequest().get();
			newCard.save();
			return redirect(routes.Application.input());
		} catch ( Exception e) {
			System.out.println("エラー");
			return null;
		}
	}

}