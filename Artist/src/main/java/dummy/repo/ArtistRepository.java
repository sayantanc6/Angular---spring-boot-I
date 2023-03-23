package dummy.repo;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import dummy.entity.ArtistEntity;

@Repository
@Transactional
public interface ArtistRepository extends PagingAndSortingRepository<ArtistEntity, Long> {

	
	public Page<ArtistEntity> findAll(Pageable pageable);
}
