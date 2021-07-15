package dummy.model;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistModel {

	@SerializedName("ArtistId")
	private Long ArtistId;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("age")
	private int age;
	
	@SerializedName("catchphrase")
	private String catchphrase;
	
	@SerializedName("description")
	private String description;

}
