package models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Config {
	@SerializedName("saldoMaximo")
	private float saldoMaximo;
	
	@SerializedName("promocionTarjetasNuevas")
	private int promocionTarjetasNuevas;
	
	@SerializedName("_id")
	private String _id;
	
	@SerializedName("sectores")
	public List<Sector> sectores;
	
	@SerializedName("gratuito") 
	public List<Gratuito>  gratuito;

	@SerializedName("subsidios") 
	public List<Subsidio>  subsidios;
	
	@SerializedName("__v")
	private float __v;


	 // Getter Methods 
	public int getSubsidioSelect (final String tipo) {
		for (int i=0; i < this.subsidios.size(); i++) {
			if (this.subsidios.get(i).getClave().equals(tipo)) {
				return i;
			} 
		}
		 
		return 1;
	 }
	
	public Subsidio getSubsidio (final int index) {
		 return this.subsidios.get(index);
	 }
	
	public String[] getSubsidios () {
		String [] dato = new String[this.subsidios.size()];
		for (int i=0; i < this.subsidios.size(); i++)
			 dato[i] = this.subsidios.get(i).getNombre();
		
		 return dato;
	 }
	
	public Gratuito getGratuitoEmpresa (final String empresa) {
		 for (int i=0; i < this.gratuito.size(); i++) {
			 if (this.gratuito.get(i).getEmpresa().equals(empresa))
				 return this.gratuito.get(i);
		 }
		 
		 return null;
	 }
	 
	public String getBlocKeyRead (int bloc) {
		 final int sector = bloc/4;
		 for (int i=0; i < this.sectores.size(); i++) {
			 if (this.sectores.get(i).getSector() == sector) {
				 String keySelect = "";
				 switch (bloc%4) {
				 	case 0: keySelect = this.sectores.get(i).getDataAccess().bloc0.getRead(); break;
				 	case 1: keySelect = this.sectores.get(i).getDataAccess().bloc1.getRead(); break;
				 	case 2: keySelect = this.sectores.get(i).getDataAccess().bloc2.getRead(); break;
				 	case 3: keySelect = this.sectores.get(i).getDataAccess().security.getRead(); break;
				 }
				 if (keySelect.equals("keyA")) {
					 return this.sectores.get(i).getKeyA();
				 } else {
					 return this.sectores.get(i).getKeyB();
				 }
			 }
		 }
		 return "";
	 }
	 
	public String getBlocKeyReadType (int bloc) {
		 final int sector = bloc/4;
		 for (int i=0; i < this.sectores.size(); i++) {
			 if (this.sectores.get(i).getSector() == sector) {
				 String keySelect = "";
				 switch (bloc%4) {
				 	case 0: keySelect = this.sectores.get(i).getDataAccess().bloc0.getRead(); break;
				 	case 1: keySelect = this.sectores.get(i).getDataAccess().bloc1.getRead(); break;
				 	case 2: keySelect = this.sectores.get(i).getDataAccess().bloc2.getRead(); break;
				 	case 3: keySelect = this.sectores.get(i).getDataAccess().security.getRead(); break;
				 }
				 return keySelect;
			 }
		 }
		 return "";
	 }
 
	public String getBlocKeyWrite (int bloc) {
		 final int sector = bloc/4;
		 for (int i=0; i < this.sectores.size(); i++) {
			 if (this.sectores.get(i).getSector() == sector) {
				 String keySelect = "";
				 switch (bloc%4) {
				 	case 0: keySelect = this.sectores.get(i).getDataAccess().bloc0.getWrite(); break;
				 	case 1: keySelect = this.sectores.get(i).getDataAccess().bloc1.getWrite(); break;
				 	case 2: keySelect = this.sectores.get(i).getDataAccess().bloc2.getWrite(); break;
				 	case 3: keySelect = this.sectores.get(i).getDataAccess().security.getWrite(); break;
				 }
				 
				 if (keySelect.equals("keyA")) {
					 return this.sectores.get(i).getKeyA();
				 } else {
					 return this.sectores.get(i).getKeyB();
				 }
			 }
		 }
		 return "";
	 }
	 
	public String getBlocKeyWriteType (int bloc) {
		 int sector = bloc/4;
		 for (int i=0; i < this.sectores.size(); i++) {
			 if (this.sectores.get(i).getSector() == sector) {
				 String keySelect = "";
				 switch (bloc%4) {
				 	case 0: keySelect = this.sectores.get(i).getDataAccess().bloc0.getWrite(); break;
				 	case 1: keySelect = this.sectores.get(i).getDataAccess().bloc1.getWrite(); break;
				 	case 2: keySelect = this.sectores.get(i).getDataAccess().bloc2.getWrite(); break;
				 	case 3: keySelect = this.sectores.get(i).getDataAccess().security.getWrite(); break;
				 }
				 return keySelect;
			 }
		 }
		 return "";
	 }
	  
	public Sector getSector (int sector) {
		 for (int i=0; i < this.sectores.size(); i++) {
			 if (this.sectores.get(i).getSector() == sector) {
				 return this.sectores.get(i);
			 }
		 }
		 return null;
	 }

	 public float getSaldoMaximo() {
	  return saldoMaximo;
	 }

	 public String get_id() {
	  return _id;
	 }

	 public float get__v() {
	  return __v;
	 }

	 // Setter Methods 

	 public void subsidios(List<Subsidio>  subsidios) {
		  this.subsidios = subsidios;
	 }
	 
	 
	 public void setSectores(ArrayList < Sector > sectores) {
		  this.sectores = sectores;
	 }
	 
	 public void setSaldoMaximo(float saldoMaximo) {
	  this.saldoMaximo = saldoMaximo;
	 }

	 public void set_id(String _id) {
	  this._id = _id;
	 }

	 public void set__v(float __v) {
	  this.__v = __v;
	 }

	public int getPromocionTarjetasNuevas() {
		return promocionTarjetasNuevas;
	}

	public void setPromocionTarjetasNuevas(int promocionTarjetasNuevas) {
		this.promocionTarjetasNuevas = promocionTarjetasNuevas;
	}
	}

