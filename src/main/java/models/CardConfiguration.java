package models;

import com.google.gson.annotations.SerializedName;

public class CardConfiguration {
	 
	 @SerializedName("empresa")
	 private String empresa;
	 
	 @SerializedName("config")
	 Config config;


	 // Getter Methods 

	 public String getEmpresa() {
	  return empresa;
	 }

	 public Config getConfig() {
	  return config;
	 }

	 // Setter Methods 

	 public void setEmpresa(String empresa) {
	  this.empresa = empresa;
	 }

	 public void setConfig(Config configObject) {
		this.config = configObject;
	 }
	}

	