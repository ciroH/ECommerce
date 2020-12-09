package logic;

import java.security.*;

import data.DataUser;
import entities.User;

public class LogicUser{
  DataUser du = new DataUser();
	public User processLogin(User loginUser) {	
		//recibe usuario mapeado(solo con mail y password cargados)
		//reemplaza el password por el hash del password
		//le pasa el usuario a DataUser.getOnLogin()
		//recibe el user completo (o en null), lo devuelve --el Servlet tiene que verificar si el User recibido es null
		
	/*	hashedPass = doHash(loginUser.getPassword())
		loginUser.setPassword(hashedPass);
	*/ 
		return du.getOnLogin(loginUser);
	}
	
	
	public boolean processSignIn(User signInUser) {
		
		
		return du.signIn(signInUser);
	}
		
}
