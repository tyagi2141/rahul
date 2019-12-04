package in.rahultyagi.mytestapp.roomdb;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CompanyDao {
	
	//getAllData from table
	@Query("SELECT * FROM company_table ORDER BY ID")
	LiveData<List<CompanyEntity>> getAllData();
	
	//save data to table
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	void insert(CompanyEntity companyEntity);
	
	//delete all data from table
	@Query("DELETE FROM company_table")
	void delete();
	
	//delete data from table by Entity
	@Delete
	void deleteByUserId(CompanyEntity companyEntity);
	
}