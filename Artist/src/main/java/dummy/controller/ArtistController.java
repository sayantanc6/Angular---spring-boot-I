package dummy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import dummy.entity.ArtistEntity;
import dummy.model.ArtistModel;
import dummy.repo.ArtistRepository;

@RestController
@RequestMapping("/artists")
@CrossOrigin("http://localhost:4200")
public class ArtistController {
	
	@Autowired
	ArtistRepository repository;
	
	@Autowired
	Gson gson;
	
	int pageCount =0;
	
	@GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public List<ArtistEntity> findall() {
		return repository.findAll(PageRequest.of(pageCount, 100)).toList();
	}
	 
	@PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public void save(@Valid @RequestBody ArtistModel model) { 
		System.out.println(model);
		 repository.save(gson.fromJson(gson.toJson(model,ArtistModel.class), ArtistEntity.class));
	} 
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> addArtistExceptions(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
	}
 
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public ArtistEntity findArtist(@PathVariable("id") Long id) {
		return repository.findById(id).get();
	}
	
	@GetMapping(value = "/isnext",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public boolean isNext() {
		if ((repository.count() - (pageCount *100)) > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	@GetMapping(value = "/isprev",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public boolean isPrevious() {
		if (pageCount < 2) {
			return false;
		}else {
			return true;
		}
	}
	
	@GetMapping(value = "getnext",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public List<ArtistEntity> getNext() {
		System.out.println("getnext");
		pageCount++;
		return repository.findAll(PageRequest.of(pageCount, 100)).toList();
	}
	
	@GetMapping(value = "getPrev",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public List<ArtistEntity> getPrevious() {
		System.out.println("getprev");
		pageCount--;
		return repository.findAll(PageRequest.of(pageCount, 100)).toList();
	}
	
	@DeleteMapping(value = "delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public void deleteItem(@PathVariable("id") Long i) {
		System.out.println("delete");

		repository.deleteById(i);
	}
	
	@PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public void update(@RequestBody ArtistModel model) { 
		System.out.println(model);
		System.out.println("inside update");
		repository.updateArtist(model.getName(),model.getAge(), model.getCatchphrase(), model.getDescription(),model.getArtistId());
	}
}
