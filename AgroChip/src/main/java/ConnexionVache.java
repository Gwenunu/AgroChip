import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ConnexionVache")
public class ConnexionVache {
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Vache connectée");
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		if(!Accueil.init)
			return;
		String liste[] = message.split(",");
		int id = Integer.valueOf(liste[0]);
		int demande = Integer.valueOf(liste[1]);
		int valeur = Integer.valueOf(liste[2]);
		switch(demande) {
		case 0:
			Vache.vaches.get(id).setRythmeCardiaque(valeur);
			break;
		case 1:
			Vache.vaches.get(id).setGlobuleBlanc(valeur);
			break;
		case 2:
			Vache.vaches.get(id).setGlucose(valeur);
			break;
			default:
			Vache.vaches.get(id).setFrequenceRespiratoire(valeur);
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("Vache déconnectée");
	}

	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("Vache hors ligne");
	}
}
