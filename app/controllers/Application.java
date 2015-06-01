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

	public static Result index() {
		return ok(index.render());
	}

	public static Result control() {
		return ok(control.render());
	}


//	public static Result mypage(){
//		List<Card> cardList = Card.find.all();
//		List<Staff> staffList = Staff.find.all();
//	    return ok(mypage.render(cardList, staffList));
//	}
}