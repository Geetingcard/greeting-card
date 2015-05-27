package controllers;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import models.Card;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result login() {
		List<String> taskList = Arrays.asList("foo", "bar", "baz");
		return ok(views.html.login.render());
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
			newCard.card_id=Integer.parseInt(params.get("card_id")[0]);
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