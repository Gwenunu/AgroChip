import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ConnexionAdmin")
public class ConnexionAdmin {
	public static Async s;
	@OnOpen
	public void onOpen(Session session) {
		s = session.getAsyncRemote();
		System.out.println("Accueil ouverte");
	}

	@OnMessage
	public void onMessage(String message, Session session) {
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("Accueil fermée");
	}

	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println("Problème à l'accueil");
	}
}
