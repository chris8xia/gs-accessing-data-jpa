package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ValueRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Integer rowIndex;
    private Integer colIndex;
    private Integer value;
    
    
    public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Integer getColIndex() {
		return colIndex;
	}

	public void setColIndex(Integer colIndex) {
		this.colIndex = colIndex;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	

    protected ValueRecord() {}

    public ValueRecord(Integer rowIndex, Integer colIndex, Integer value) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format(
                "ValueRecord [id=%d, rowIndex='%d', colIndex='%d', value='%d']",
                id, rowIndex,colIndex,value);
    }

}

