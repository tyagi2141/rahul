package in.rahultyagi.mytestapp.viewmodels;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import in.rahultyagi.mytestapp.models.Results;
import in.rahultyagi.mytestapp.networkcalls.CompanyRepository;
import in.rahultyagi.mytestapp.roomdb.CompanyEntity;


public class CompanyViewModel extends ViewModel {
	
	private MutableLiveData<List<Results>> mutableLiveData;
	private CompanyRepository objRepository;
	private LiveData<List<CompanyEntity>> listMutableLiveData;
	
	public void init() {
		if (mutableLiveData != null) {
			return;
		}
		objRepository = CompanyRepository.getInstance();
		mutableLiveData = objRepository.getApiDatas();
		
		if (listMutableLiveData != null) {
			return;
		}
		listMutableLiveData = objRepository.getAllWords();
		
	}
	
	public LiveData<List<Results>> getobjRepository() {
		
		return mutableLiveData;
	}
	
	public LiveData<List<CompanyEntity>> getOfflineData() {
		return listMutableLiveData;
	}
	
	public void insert(CompanyEntity companyEntity) {
		objRepository.insert(companyEntity);
	}
	
	public void deleteById(CompanyEntity companyEntity) {
		objRepository.deleteById(companyEntity);
	}
	public void delete() {
		objRepository.delete();
	}
}
