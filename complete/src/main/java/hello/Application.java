package hello;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication
				.run(Application.class);
		ValueRecordRepository repository = context
				.getBean(ValueRecordRepository.class);

		// assume resulting following matrix
		/*
		 * 1 2 0 
		 * 1 2 3
		 *  0 2 0
		 * 
		 * do you need to print the headers of columns and rows
		 */

		// no need to initialize for the real code
		repository.save(new ValueRecord(2, 4, 1));
		repository.save(new ValueRecord(2, 5, 2));
		repository.save(new ValueRecord(8, 4, 1));
		repository.save(new ValueRecord(8, 5, 2));
		repository.save(new ValueRecord(8, 6, 3));
		repository.save(new ValueRecord(9, 5, 2));

		// fetch all records
		// 1. change to the real query
		// 2. may need to change ValueRecordRepository
		// 3. or, you can generate a temp table first.

		Iterable<ValueRecord> records = repository.findAll();

		// create the resulting matrix

		Map<Integer, Map<Integer, Integer>> rows = new HashMap<Integer, Map<Integer, Integer>>();
		Set<Integer> rowIndexes = new HashSet<Integer>();
		Set<Integer> colIndexes = new HashSet<Integer>();

		for (ValueRecord record : records) {
			System.out.println(record);
			// get the row
			rowIndexes.add(record.getRowIndex());
			colIndexes.add(record.getColIndex());

			Map<Integer, Integer> col = rows.get(record.getRowIndex());
			if (col == null) {
				col = new HashMap<Integer, Integer>();
				rows.put(record.getRowIndex(), col);
			}
			col.put(record.getColIndex(), record.getValue());

		}

		System.out.println();

		// write to a file;
		try {
			PrintWriter writer = new PrintWriter("matrix.txt");
			for (Integer rowIndex : rowIndexes) {
				Map<Integer, Integer> col = rows.get(rowIndex);
				for (Integer colIndex : colIndexes) {
					Integer value = col.get(colIndex);
					if (value == null) {
						writer.format("%6d", 0);
					} else {
						writer.format("%6d", value);
					}
				}
				writer.println();
			}
			writer.println();
			writer.close();

		} catch (Exception e) {
			System.out.println("io error" + e);

		}

		context.close();
	}

}
