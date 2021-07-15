package dummy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public void save(@RequestBody ArtistModel model) { 
		 repository.save(gson.fromJson(gson.toJson(model,ArtistModel.class), ArtistEntity.class));
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
		pageCount++;
		return repository.findAll(PageRequest.of(pageCount, 100)).toList();
	}
	
	@GetMapping(value = "getPrev",produces = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public List<ArtistEntity> getPrevious() {
		pageCount--;
		return repository.findAll(PageRequest.of(pageCount, 100)).toList();
	}
}
