package in.rahultyagi.mytestapp.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;

import java.util.ArrayList;
import java.util.List;

import in.rahultyagi.mytestapp.R;
import in.rahultyagi.mytestapp.adapters.CompanyAdapter;
import in.rahultyagi.mytestapp.models.Results;
import in.rahultyagi.mytestapp.roomdb.CompanyEntity;
import in.rahultyagi.mytestapp.viewmodels.CompanyViewModel;

public class MainActivity extends AppCompatActivity {
	ArrayList<CompanyEntity> dataArrayList = new ArrayList<>();
	CompanyAdapter companyAdapter;
	RecyclerView mrecyclearView;
	CompanyViewModel dataViewModel;
	boolean networkStatus=true;
	ConstraintLayout homeScreenView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mrecyclearView = findViewById(R.id.rvNews);
		homeScreenView = findViewById(R.id.container);
		dataViewModel = ViewModelProviders.of(this).get(CompanyViewModel.class);
		dataViewModel.init();
		
		/** set the recyclview swap function**/
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
		itemTouchHelper.attachToRecyclerView(mrecyclearView);


		Tovuti.from(this).monitor(new Monitor.ConnectivityListener() {
			@Override
			public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast) {
				if (isConnected == true) {
					networkStatus=true;
					getServerData();
				} else {
					networkStatus=false;
					Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_SHORT).show();
				}
			}
		});
		getDbData();
	}
	
	public void getDbData() {
		dataViewModel.getOfflineData().observe(this, new Observer<List<CompanyEntity>>() {
			@Override
			public void onChanged(List<CompanyEntity> results) {
				
				if (dataArrayList.size() > 0) {
					dataArrayList.clear();
				}
				if (results != null) {
					for (int i = 0; i < results.size(); i++) {
						CompanyEntity results1 = new CompanyEntity(
								results.get(i).getId(),
								results.get(i).getName(),
								results.get(i).getUserName(),
								results.get(i).getEmail(),
								results.get(i).getPhone(),
								results.get(i).getWebsite(),
								results.get(i).getGetBs(),
								results.get(i).getGetCatchPhrase(),
								results.get(i).getName(),
								results.get(i).getCity(),
								results.get(i).getStreet(),
								results.get(i).getSuits(),
								results.get(i).getZipcode(),
								results.get(i).getLat(),
								results.get(i).getLng()
						);
						
						dataArrayList.add(results1);
					}
				}
				setupRecyclerView();
			}
		});
		
	}
	
	private void setupRecyclerView() {
		if (companyAdapter == null) {
			companyAdapter = new CompanyAdapter(MainActivity.this, dataArrayList);
			mrecyclearView.setLayoutManager(new LinearLayoutManager(this));
			mrecyclearView.setAdapter(companyAdapter);
			mrecyclearView.setItemAnimator(new DefaultItemAnimator());
			mrecyclearView.setNestedScrollingEnabled(true);
			
		} else {
			companyAdapter.notifyDataSetChanged();
		}
	}
	
	public void getServerData() {
		dataViewModel.getobjRepository().observe(this, new Observer<List<Results>>() {
			@Override
			public void onChanged(List<Results> results) {
				if (results != null) {
					dataViewModel.delete();
					for (int i = 0; i < results.size(); i++) {
						CompanyEntity results1 = new CompanyEntity(
								results.get(i).getId(),
								results.get(i).getName(),
								results.get(i).getUsername(),
								results.get(i).getEmail(),
								results.get(i).getPhone(),
								results.get(i).getWebsite(),
								results.get(i).getCompany().getBs(),
								results.get(i).getCompany().getCatchPhrase(),
								results.get(i).getCompany().getName(),
								results.get(i).getAddress().getCity(),
								results.get(i).getAddress().getStreet(),
								results.get(i).getAddress().getSuite(),
								results.get(i).getAddress().getZipcode(),
								results.get(i).getAddress().getGeo().getLat(),
								results.get(i).getAddress().getGeo().getLng()
						);
						dataViewModel.insert(results1);
					}
				}
				companyAdapter.notifyDataSetChanged();
			}
		});
	}
	
	ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
		@Override
		public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
			return false;
		}
		
		@Override
		public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
			final int position = viewHolder.getAdapterPosition(); //get position which is swipe
			
			if (direction == ItemTouchHelper.LEFT) {    //if swipe left
				
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //alert for confirm to delete
				builder.setMessage("Are you sure to delete?");    //set message
				builder.setCancelable(false);
				builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
					@Override
					public void onClick(DialogInterface dialog, int which) {
						companyAdapter.notifyItemRemoved(position);    //item removed from recylcerview
						CompanyEntity companyEntity = dataArrayList.get(position);
						dataViewModel.deleteById(companyEntity);
						
						dataArrayList.remove(position);  //then remove item
						
						return;
					}
				}).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
					@Override
					public void onClick(DialogInterface dialog, int which) {
						companyAdapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
						companyAdapter.notifyItemRangeChanged(position, companyAdapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
						return;
					}
				}).show();  //show alert dialog
			}
		}
	};
	
	@Override
	protected void onStop() {
		Tovuti.from(this).stop();
		super.onStop();
	}
}
