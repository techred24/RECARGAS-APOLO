/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cardtest.exception;

/**
 *
 * @author frafa
 */

public class CardDataException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CardDataException(String message){
		super(message);
	}
	
}