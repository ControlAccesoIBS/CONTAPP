package EncriptadoPass;

import org.apache.commons.codec.digest.DigestUtils;

public class MiCipher {
	
	public String Encriptar(String pass){
		String encriptado = DigestUtils.md5Hex(pass);		
		return encriptado;
	}

	public static void main(String [] args){
		String textoSinEncriptar="malitos15"; 
		String textoEncriptadoConMD5=DigestUtils.md5Hex(textoSinEncriptar);
		System.out.println("Texto Encriptado con MD5 : "+textoEncriptadoConMD5);
		System.out.println(textoEncriptadoConMD5.length());
		
		String SinEncriptar="malitos15"; 
		String textoEncriptadoConSHA=DigestUtils.sha1Hex(SinEncriptar); 
		System.out.println("\nTexto Encriptado con SHA : "+textoEncriptadoConSHA);
		System.out.println(textoEncriptadoConSHA.length());
		


	}
}