package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository<Class, id Type>
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByName(String name);
	
	Optional<User> findByAge(int age);
	
	Optional <User> findById(Long id);
}
