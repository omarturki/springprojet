package tekup.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.module.Met;

public interface MetBaseRepo <T extends Met> extends JpaRepository<T,Integer>{

}
