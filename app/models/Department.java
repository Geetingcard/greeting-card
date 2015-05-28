package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

@Entity
public class Department extends Model {
	 @Id
	 public int department_id;
	 @NotNull
	 public String department_name;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	 public List<Staff> staffs = new ArrayList<Staff>();
}