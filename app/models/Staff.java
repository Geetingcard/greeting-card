package models;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

@Entity
public class Staff extends Model {
	 @Id
	        public int staff_id;
	 @NotNull
	 		public int department_id;
	 @NotNull
	        public String staff_name;
	 @NotNull
	        public String password;
	 @NotNull
	        public int authority;


	 public static Finder<Integer, Staff> find = new Finder<Integer, Staff>(
             Integer.class, Staff.class
             );

}