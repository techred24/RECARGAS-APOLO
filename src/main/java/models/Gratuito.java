package models;

public class Gratuito {
	 Conteo Conteo;
	 Boletos Boletos;
	 private String _id;
	 private String empresa;
	 private int sector;


	 // Getter Methods 

	 public Conteo getConteo() {
	  return Conteo;
	 }

	 public Boletos getBoletos() {
	  return Boletos;
	 }

	 public String get_id() {
	  return _id;
	 }

	 public String getEmpresa() {
	  return empresa;
	 }

	 public int getSector() {
	  return sector;
	 }

	 // Setter Methods 

	 public void setConteo(Conteo conteoObject) {
	  this.Conteo = conteoObject;
	 }

	 public void setBoletos(Boletos boletosObject) {
	  this.Boletos = boletosObject;
	 }

	 public void set_id(String _id) {
	  this._id = _id;
	 }

	 public void setEmpresa(String empresa) {
	  this.empresa = empresa;
	 }

	 public void setSector(int sector) {
	  this.sector = sector;
	 }
	}
