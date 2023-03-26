package dummy.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.GsonBuilder;

@EnableWebMvc
@Configuration
public class GsonConfig implements WebMvcConfigurer {
	
	@Bean
	public GsonBuilder gsonBuilder(List<GsonBuilderCustomizer> customizers) {
		
		GsonBuilder builder = new GsonBuilder();
		 customizers.forEach(c -> c.customize(builder));
				return builder.registerTypeHierarchyAdapter(LocalDateTime.class, new LocalDateTimeSerDe())
					  .registerTypeHierarchyAdapter(LocalDate.class, new LocalDateSerDe())
					  .setObjectToNumberStrategy(new MyNumberStrategy())
					  .addSerializationExclusionStrategy(new ExcludeNullStrategy());
	}

       // it'll help to correct JSON format response in REST APIs
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) { 
	    StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
	    stringConverter.setWriteAcceptCharset(false);
	    stringConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
	    converters.add(stringConverter);
	    converters.add(new ByteArrayHttpMessageConverter());
	    converters.add(new SourceHttpMessageConverter<>());
	    GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
	    gsonHttpMessageConverter.setGson(new GsonBuilder().create());
	    gsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
	    converters.add(gsonHttpMessageConverter);
	}



}
