import java.util.ArrayList;
import java.util.List;

import javax.websocket.RemoteEndpoint.Async;

public class Vache {
	public static List<Vache> vaches = new ArrayList<Vache>();
	private int id;
	private String nom;
	private int rythmeCardiaque;
	private int globuleBlanc;
	private int glucose;
	private int frequenceRespiratoire;
	
	Vache(int i, String n){
		id = i;
		nom = n;
		rythmeCardiaque = 70;//60-80bpm
		globuleBlanc = 8000;//4000-12000/mm3
		glucose = 500;//400-700mg/L
		frequenceRespiratoire = 20;//15-35ppm
		vaches.add(this);
	}
	
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public int getRythmeCardiaque() {
		return rythmeCardiaque;
	}

	public void setRythmeCardiaque(int rythmeCardiaque) {
		this.rythmeCardiaque = rythmeCardiaque;
		if(alerteCardiaque())
			sendMessage(id, 0, rythmeCardiaque, true);
		else
			sendMessage(id, 0, rythmeCardiaque, false);
	}

	public int getGlobuleBlanc() {
		return globuleBlanc;
	}

	public void setGlobuleBlanc(int globuleBlanc) {
		this.globuleBlanc = globuleBlanc;
		if(alerteGlobuleBlanc())
			sendMessage(id, 1, globuleBlanc, true);
		else
			sendMessage(id, 1, globuleBlanc, false);
	}

	public int getGlucose() {
		return glucose;
	}

	public void setGlucose(int glucose) {
		this.glucose = glucose;
		if(alerteGlucose())
			sendMessage(id, 2, glucose, true);
		else
			sendMessage(id, 2, glucose, false);
	}

	public int getFrequenceRespiratoire() {
		return frequenceRespiratoire;
	}

	public void setFrequenceRespiratoire(int frequenceRespiratoire) {
		this.frequenceRespiratoire = frequenceRespiratoire;
		if(alerteRespiration())
			sendMessage(id, 3, frequenceRespiratoire, true);
		else
			sendMessage(id, 3, frequenceRespiratoire, false);
	}

	public boolean alerteCardiaque() {
		if(rythmeCardiaque<70 || rythmeCardiaque>80)
			return true;
		return false;
	}

	public boolean alerteGlobuleBlanc() {
		if(globuleBlanc<4000 || globuleBlanc>12000)
			return true;
		return false;
	}

	public boolean alerteGlucose() {
		if(glucose<400 || glucose>700)
			return true;
		return false;
	}

	public boolean alerteRespiration() {
		if(frequenceRespiratoire<15 || frequenceRespiratoire>35)
			return true;
		return false;
	}
	
	private void sendMessage(int id, int demande, int valeur, boolean alerte) {
		String message = id+","+(demande+1)+","+valeur+","+alerte;
		Async a = ConnexionAdmin.s;
		a.sendText(message);
		System.out.println(nom + " a envoyée : " + message);
	}
}
