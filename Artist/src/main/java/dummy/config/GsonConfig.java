package dummy.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.GsonBuilder;

import dummy.model.Animal;
import dummy.model.Cow;
import dummy.model.Dog;

@Configuration
public class GsonConfig{
	
	@Bean
	public GsonBuilder gsonBuilder(List<GsonBuilderCustomizer> customizers) {
		
		// this is used for SerDe with polymorphic objects
		RuntimeTypeAdapterFactory<Animal> adapter = RuntimeTypeAdapterFactory.of(Animal.class, "type")
			      .registerSubtype(Dog.class)
			      .registerSubtype(Cow.class);
		
		GsonBuilder builder = new GsonBuilder();
		 customizers.forEach(c -> c.customize(builder));
				return builder.registerTypeHierarchyAdapter(LocalDateTime.class, new LocalDateTimeSerDe())
					  .registerTypeHierarchyAdapter(LocalDate.class, new LocalDateSerDe())
					  .setObjectToNumberStrategy(new MyNumberStrategy())
					  .registerTypeAdapterFactory(adapter)
					  .addSerializationExclusionStrategy(new ExcludeNullStrategy());
	}
}
