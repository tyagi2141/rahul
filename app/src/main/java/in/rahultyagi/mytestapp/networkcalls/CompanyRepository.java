package in.rahultyagi.mytestapp.networkcalls;


import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import in.rahultyagi.mytestapp.MyApplication;
import in.rahultyagi.mytestapp.models.Results;
import in.rahultyagi.mytestapp.roomdb.AppDatabase;
import in.rahultyagi.mytestapp.roomdb.CompanyDao;
import in.rahultyagi.mytestapp.roomdb.CompanyEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyRepository {
	private static long unique_id;
	private static CompanyRepository newsRepository;
	private static CompanyDao mCompanyDao = null;
	private static LiveData<List<CompanyEntity>> mAllWords;
	
	public static CompanyRepository getInstance() {
		if (newsRepository == null) {
			newsRepository = new CompanyRepository();
		}
		return newsRepository;
	}
	
	private CompanyApi apiService;
	
	public CompanyRepository() {
		apiService = RetrofitService.cteateService(CompanyApi.class);
		AppDatabase mDb = MyApplication.getDB();
		mCompanyDao = mDb.companyDao();
		mAllWords = mCompanyDao.getAllData();
	}
	
	public MutableLiveData<List<Results>> getApiDatas() {
		final MutableLiveData<List<Results>> newData = new MutableLiveData<>();
		
		
			apiService.getApiList().enqueue(new Callback<List<Results>>() {
				@Override
				public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
					if (response.isSuccessful()) {
						newData.setValue(response.body());
					}
				}
				@Override
				public void onFailure(Call<List<Results>> call, Throwable t) {
					newData.setValue(null);
				}
			});
		
		return newData;
	}
	
	
	public LiveData<List<CompanyEntity>> getAllWords() {
		return mAllWords;
	}
	
	public void insert(CompanyEntity word) {
		new CompanyRepository.insertAsyncTask(mCompanyDao).execute(word);
	}
	
	public void deleteById(CompanyEntity companyEntity) {
		
		new CompanyRepository.DeleteByIdAsyncTask(mCompanyDao).execute(companyEntity);
		
	}
	
	public void delete() {
		new CompanyRepository.deleteAsyncTask(mCompanyDao).execute();
	}
	
	//save data to table
	private static class insertAsyncTask extends AsyncTask<CompanyEntity, Void, Void> {
		private static CompanyDao mAsyncTaskDao;
		
		insertAsyncTask(CompanyDao dao) {
			mAsyncTaskDao = dao;
		}
		
		@Override
		protected Void doInBackground(final CompanyEntity... params) {
			mAsyncTaskDao.insert(params[0]);
			return null;
		}
	}
	
	//Delete All data
	private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {
		private static CompanyDao mAsyncTaskDao;
		
		deleteAsyncTask(CompanyDao dao) {
			mAsyncTaskDao = dao;
		}
		
		@Override
		protected Void doInBackground(Void... voids) {
			mAsyncTaskDao.delete();
			return null;
		}
	}
	
	/**
	 * Delete the data by ID
	 **/
	private static class DeleteByIdAsyncTask extends AsyncTask<CompanyEntity, Void, Void> {
		private static CompanyDao mAsyncTaskDao;
		
		DeleteByIdAsyncTask(CompanyDao dao) {
			mAsyncTaskDao = dao;
		}
		
		@Override
		protected Void doInBackground(CompanyEntity... longs) {
			mAsyncTaskDao.deleteByUserId(longs[0]);
			return null;
		}
	}
}
