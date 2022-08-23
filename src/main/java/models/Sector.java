package models;

public class Sector {
	 private int sector;
	 private String nombre;
	 private String keyA;
	 private String keyB;
	 private String keyAold;
	 private String keyBold;
	 private String accessBits;
	 DataAccess dataAccess;


	 // Getter Methods 

	 public int getSector() {
	  return sector;
	 }

	 public String getNombre() {
	  return nombre;
	 }

	 public String getKeyA() {
	  return keyA;
	 }

	 public String getKeyB() {
	  return keyB;
	 }

	 public String getKeyAold() {
	  return keyAold;
	 }

	 public String getKeyBold() {
	  return keyBold;
	 }

	 public String getAccessBits() {
	  return accessBits;
	 }

	 public DataAccess getDataAccess() {
	  return dataAccess;
	 }

	 // Setter Methods 

	 public void setSector(int sector) {
	  this.sector = sector;
	 }

	 public void setNombre(String nombre) {
	  this.nombre = nombre;
	 }

	 public void setKeyA(String keyA) {
	  this.keyA = keyA;
	 }

	 public void setKeyB(String keyB) {
	  this.keyB = keyB;
	 }

	 public void setKeyAold(String keyAold) {
	  this.keyAold = keyAold;
	 }

	 public void setKeyBold(String keyBold) {
	  this.keyBold = keyBold;
	 }

	 public void setAccessBits(String accessBits) {
	  this.accessBits = accessBits;
	 }

	 public void setDataAccess(DataAccess dataAccessObject) {
	  this.dataAccess = dataAccessObject;
	 }
	}