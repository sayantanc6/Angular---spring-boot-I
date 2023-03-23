package dummy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ARTIST")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistEntity { 

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SerializedName("Artistid")
	@Column(name = "Artistid",unique = true)
    private Long ArtistId;
	
	@SerializedName("name")
	@Column(name = "name")
	private String name;
	
	@SerializedName("age")
	@Column(name = "age")
	private int age;
	
	@SerializedName("catchphrase")
	@Column(name = "catchphrase")
	private String catchphrase;
	
	@SerializedName("description")
	@Column(name = "description")
	private String description;
}
