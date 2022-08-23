package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Subsidio {
	 private String clave;
	 private String nombre;
	 private int diasUtiles;


	 // Getter Methods 

	 @SuppressWarnings("deprecation")
	public String getFechaVencimiento() {
		 
		 if (diasUtiles == 0) {
			 return "00000000";
		 }
		 
		final TimeZone tz = TimeZone.getTimeZone("UTC");
     	final DateFormat df = new SimpleDateFormat("yyyyMMdd");
     	df.setTimeZone(tz);
     	
     	final Date auxDate = new Date();
     	auxDate.setHours(diasUtiles*24);
     	return df.format(auxDate);
	 }
	 
	 public String getClave() {
	  return clave;
	 }

	 public String getNombre() {
	  return nombre;
	 }

	 // Setter Methods 

	 public void setClave(String clave) {
	  this.clave = clave;
	 }

	 public void setNombre(String nombre) {
	  this.nombre = nombre;
	 }

	public int getDiasUtiles() {
		return diasUtiles;
	}

	public void setDiasUtiles(int diasUtilesO) {
		this.diasUtiles = diasUtilesO;
	}
	}
