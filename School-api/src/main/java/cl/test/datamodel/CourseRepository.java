package cl.test.datamodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

	@Modifying
	@Transactional
	@Query("delete from Course c where c.code = :code")
	void deleteByCode(@Param("code") String code);
}
