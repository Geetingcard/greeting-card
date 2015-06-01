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
import javax.persistence.*;

import play.db.ebean.Model;
import play.db.ebean.Model;

@Entity
public class Staff extends Model {
	@Id
	public int staff_id;

	@NotNull
	public int staff_code;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "send_staff")
	public List<Card> send_staff = new ArrayList<Card>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "get_staff")
	public List<Card> get_staff = new ArrayList<Card>();

	public static Finder<Integer, Staff> find = new Finder<Integer, Staff>(
			Integer.class, Staff.class
			);
	public static Finder<Long, Staff> find2 = new Finder<Long, Staff>(
			Long.class, Staff.class
			);
    public static Boolean authenticate(String username, String password) {
        Staff user = find.where().eq("staff_code", username).findUnique();
        return (user != null && user.password.equals(password));
    }

}
