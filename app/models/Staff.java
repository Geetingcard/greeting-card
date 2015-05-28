package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

@Entity
public class Staff extends Model {
	 @Id
	 	public int staff_id;

	 @NotNull
	 @JoinColumn(name = "department_id")
	 @ManyToOne
	 		public Department department;
	 @NotNull
	        public String staff_name;
	 @NotNull
	        public String password;
	 @NotNull
	        public int authority;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "send_staff_id")
	    public List<Card> send_staff = new ArrayList<Card>();

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "get_staff_id")
	    public List<Card> get_staff = new ArrayList<Card>();
}