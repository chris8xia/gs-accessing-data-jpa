package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ValueRecordRepository extends CrudRepository<ValueRecord, Long> {
}
