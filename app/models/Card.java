package models;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Card extends Model {
	@Id
	public int card_id;

	@ManyToOne
	@JoinColumn(name = "get_staff_id")
	@NotNull
	public Staff get_staff_id;

	@ManyToOne
	@JoinColumn(name = "send_staff_id")
	@NotNull
	public Staff send_staff_id;

	@NotNull
	public int point;

	public String help_detail;
	public String thanks_word;

	@NotNull
	public Date helped_date;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@NotNull
	public Category category_id;

	@CreatedTimestamp
	public Date post_date;

	public static Finder<Long, Card> find = new Finder<Long, Card>(
			Long.class, Card.class
			);

}
