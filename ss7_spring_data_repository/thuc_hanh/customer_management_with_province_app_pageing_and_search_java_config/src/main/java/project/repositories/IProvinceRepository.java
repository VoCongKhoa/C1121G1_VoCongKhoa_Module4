package project.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import project.models.Province;

@Repository
public interface IProvinceRepository extends PagingAndSortingRepository<Province, Long> {
}
