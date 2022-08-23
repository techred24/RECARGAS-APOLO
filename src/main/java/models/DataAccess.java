package models;

public class DataAccess {
	 Block bloc0;
	 Block bloc1;
	 Block bloc2;
	 Block security;


	 // Getter Methods 

	 public Block getBloc0() {
	  return bloc0;
	 }

	 public Block getBloc1() {
	  return bloc1;
	 }

	 public Block getBloc2() {
	  return bloc2;
	 }

	 public Block getSecurity() {
	  return security;
	 }

	 // Setter Methods 

	 public void setBloc0(Block bloc0Object) {
	  this.bloc0 = bloc0Object;
	 }

	 public void setBloc1(Block bloc1Object) {
	  this.bloc1 = bloc1Object;
	 }

	 public void setBloc2(Block bloc2Object) {
	  this.bloc2 = bloc2Object;
	 }

	 public void setSecurity(Block securityObject) {
	  this.security = securityObject;
	 }
	}