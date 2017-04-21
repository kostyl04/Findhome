package by.kostyl.findhome.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.Entity;



@MappedSuperclass
public abstract class AbstractEntity implements java.io.Serializable{
	@Id
	@Column
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
