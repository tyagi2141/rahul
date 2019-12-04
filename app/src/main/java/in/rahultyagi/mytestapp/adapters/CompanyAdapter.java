package in.rahultyagi.mytestapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.rahultyagi.mytestapp.R;
import in.rahultyagi.mytestapp.roomdb.CompanyEntity;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ComapanyViewHolder> {
	
	Context context;
	ArrayList<CompanyEntity> companyArrayList;

	public CompanyAdapter(Context context, ArrayList<CompanyEntity> companyArrayList) {
		this.context = context;
		this.companyArrayList = companyArrayList;
	}
	
	@NonNull
	@Override
	public CompanyAdapter.ComapanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);




		return new ComapanyViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull CompanyAdapter.ComapanyViewHolder holder, int position) {
		
		
		holder.tvID.setText(String.valueOf(companyArrayList.get(position).getId()));
		holder.tvName.setText(companyArrayList.get(position).getName());
		holder.tvUserName.setText(companyArrayList.get(position).getUserName());
		holder.tvPhone.setText(companyArrayList.get(position).getPhone());
		holder.tvEmail.setText(companyArrayList.get(position).getEmail());
		holder.tvwebsite.setText(companyArrayList.get(position).getWebsite());
		holder.tvgetBs.setText(companyArrayList.get(position).getGetBs());
		holder.tvgetCatchPhrase.setText(companyArrayList.get(position).getGetCatchPhrase());
		holder.tvCompanyName.setText(companyArrayList.get(position).getCompanyName());
		holder.tvcity.setText(companyArrayList.get(position).getCity());
		holder.tvstreet.setText(companyArrayList.get(position).getStreet());
		holder.tvsuits.setText(companyArrayList.get(position).getSuits());
		holder.tvzipcode.setText(companyArrayList.get(position).getZipcode());
		holder.tvlat.setText(companyArrayList.get(position).getLat());
		holder.tvlng.setText(companyArrayList.get(position).getLng());
		
	}
	
	@Override
	public int getItemCount() {
		return companyArrayList.size();
	}
	
	public class ComapanyViewHolder extends RecyclerView.ViewHolder {
		
		TextView tvID;
		TextView tvName;
		TextView tvUserName;
		TextView tvPhone;
		TextView tvEmail;
		TextView tvwebsite;
		TextView tvgetBs;
		TextView tvgetCatchPhrase;
		TextView tvCompanyName;
		TextView tvcity;
		TextView tvstreet;
		TextView tvsuits;
		TextView tvzipcode;
		TextView tvlat;
		TextView tvlng;
		
		
		public ComapanyViewHolder(@NonNull View itemView) {
			super(itemView);
			
			tvID = itemView.findViewById(R.id.tv_id);
			tvName = itemView.findViewById(R.id.tv_name);
			tvUserName = itemView.findViewById(R.id.tv_userName);
			tvPhone = itemView.findViewById(R.id.tv_phone);
			tvEmail = itemView.findViewById(R.id.tv_email);
			tvwebsite = itemView.findViewById(R.id.tv_website);
			tvgetBs = itemView.findViewById(R.id.tv_getBs);
			tvgetCatchPhrase = itemView.findViewById(R.id.tv_getCatchPhrase);
			tvCompanyName = itemView.findViewById(R.id.tv_companyName);
			tvcity = itemView.findViewById(R.id.tv_city);
			tvstreet = itemView.findViewById(R.id.tv_street);
			tvsuits = itemView.findViewById(R.id.tv_suits);
			tvzipcode = itemView.findViewById(R.id.tv_zipcode);
			tvlat = itemView.findViewById(R.id.tv_lat);
			tvlng = itemView.findViewById(R.id.tv_lng);
			
			
		}
	}
	

}
