package models;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import play.db.ebean.Model;

@Entity
public class Departmant extends Model {
	 @Id
	 public int departent_id;
	 @NotNull
	 public String department_name;


}