package models;
import javax.persistence.Entity;

import com.avaje.ebean.annotation.CreatedTimestamp;

import javax.persistence.Id;

import play.db.ebean.Model;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Card extends Model {
	 @Id
	        public int card_id;
	 @NotNull
			public int get_staff_id;
	 @NotNull
		    public int send_staff_id;
	 @NotNull
	        public int point;
	        public String help_detail;
	        public String thanks_word;
	 @NotNull
	        public Date helped_date;
	 @NotNull
	        public int category_id;
	        @CreatedTimestamp


	        public static Finder<Integer, Card> find = new Finder<Integer, Card>(
	                Integer.class, Card.class
	        );

}
