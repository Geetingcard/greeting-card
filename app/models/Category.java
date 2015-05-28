package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

@Entity
public class Category extends Model {
	 @Id
	 		public int category_id;
	 @NotNull
	        public String category_name;

	 @OneToMany
	 	public List<Card> cards= new ArrayList<Card>();

}
