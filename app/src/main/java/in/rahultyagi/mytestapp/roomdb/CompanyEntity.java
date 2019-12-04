package in.rahultyagi.mytestapp.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "company_table")
public class CompanyEntity {
	
	private int unique_id;
	
	@PrimaryKey
	private int id;
	private String name;
	private String userName;
	private String email;
	private String phone;
	private String website;
	private String getBs;
	private String getCatchPhrase;
	private String companyName;
	private String city;
	private String street;
	private String suits;
	private String zipcode;
	private String lat;
	private String lng;
	
	
	public CompanyEntity(int id, String name, String userName, String email, String phone, String website, String getBs, String getCatchPhrase, String companyName, String city, String street, String suits, String zipcode, String lat, String lng) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.getBs = getBs;
		this.getCatchPhrase = getCatchPhrase;
		this.companyName = companyName;
		this.city = city;
		this.street = street;
		this.suits = suits;
		this.zipcode = zipcode;
		this.lat = lat;
		this.lng = lng;
	}
	
	public int getUnique_id() {
		return unique_id;
	}
	
	public void setUnique_id(int unique_id) {
		this.unique_id = unique_id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public String getGetBs() {
		return getBs;
	}
	
	public String getGetCatchPhrase() {
		return getCatchPhrase;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getSuits() {
		return suits;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public String getLat() {
		return lat;
	}
	
	public String getLng() {
		return lng;
	}
	
	
	@Override
	public String toString() {
		return "CompanyEntity{" +
				"unique_id=" + unique_id +
				", id=" + id +
				", name='" + name + '\'' +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", website='" + website + '\'' +
				", getBs='" + getBs + '\'' +
				", getCatchPhrase='" + getCatchPhrase + '\'' +
				", companyName='" + companyName + '\'' +
				", city='" + city + '\'' +
				", street='" + street + '\'' +
				", suits='" + suits + '\'' +
				", zipcode='" + zipcode + '\'' +
				", lat='" + lat + '\'' +
				", lng='" + lng + '\'' +
				'}';
	}
}
