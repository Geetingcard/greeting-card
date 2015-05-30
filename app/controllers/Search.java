package controllers;
import models.Card;
import models.Department;
import java.util.List;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
public class Search extends Controller {

	public static Result search() {
		List<Card> cardList = Card.find.all();
		List<Department> departmentList = Department.find.all();
	    return ok(search.render(cardList,departmentList));
	}


}