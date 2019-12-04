package in.rahultyagi.mytestapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
	
	@SerializedName("street")
	@Expose
	private String street;
	@SerializedName("suite")
	@Expose
	private String suite;
	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("zipcode")
	@Expose
	private String zipcode;
	@SerializedName("geo")
	@Expose
	private Geo geo;
	
	public String getStreet() {
		return street;
	}
	public String getSuite() {
		return suite;
	}
	public String getCity() {
		return city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public Geo getGeo() {
		return geo;
	}
	
	
	@Override
	public String toString() {
		return "Address{" +
				"street='" + street + '\'' +
				", suite='" + suite + '\'' +
				", city='" + city + '\'' +
				", zipcode='" + zipcode + '\'' +
				", geo=" + geo +
				'}';
	}
}