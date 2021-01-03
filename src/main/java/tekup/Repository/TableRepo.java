package tekup.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tekup.module.table;

public interface TableRepo extends JpaRepository<table, Integer> {

}
