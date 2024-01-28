import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static boolean init = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        if(!init) {
        	new Vache(0, "Gwen");
        	new Vache(1, "Rose");
        	new Vache(2, "Julie");
        	new Vache(3, "Jade");
        }
        init = true;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vache v;
		PrintWriter pr;
		pr = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		pr.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta charset=\"UTF-8\">\n"
				+ "<title>Accueil</title>\n"
				+ "<style> td{text-align:center;}"
				+ " .alerte{background-color:red;} "
				+ "</style>"
				+ "</head>\n"
				+ "<body>\n"
				+ "<h2><table border=1px>\n"
				+ "	<caption><h1 align-items:\"center\">Liste des vaches</h1></caption>");
		pr.println("<thead><tr><th>Nom de la vache</th><th>Rythme cardiaque (bpm)</th><th>Globules blancs (/mm3)</th><th>Glycémie (mg/L)</th><th>Fréquence respiratoire (ppm)</th></tr></thead>");
		for(int i=0;i<4;i++) {
			v = Vache.vaches.get(i);
			pr.println("<tr id="+i+"><td>"+v.getNom()+"</td>");
			if(v.alerteCardiaque())
				pr.println("<td class=\"alerte\">"+v.getRythmeCardiaque()+"</td>");
			else
				pr.println("<td>"+v.getRythmeCardiaque()+"</td>");
			if(v.alerteGlobuleBlanc())
				pr.println("<td class=\"alerte\">"+v.getGlobuleBlanc()+"</td>");
			else
				pr.println("<td>"+v.getGlobuleBlanc()+"</td>");
			if(v.alerteGlucose())
				pr.println("<td class=\"alerte\">"+v.getGlucose()+"</td>");
			else
				pr.println("<td>"+v.getGlucose()+"</td>");
			if(v.alerteRespiration())
				pr.println("<td class=\"alerte\">"+v.getFrequenceRespiratoire()+"</td></tr>");
			else
				pr.println("<td>"+v.getFrequenceRespiratoire()+"</td></tr>");
		}
		pr.println("</table></h2>\n"
				+ "<script>"
				+ "let websocket = new WebSocket(\"ws://localhost:8081/AgroChip/ConnexionAdmin\");"
				+ "websocket.addEventListener(\"open\", (event) => {\n"
				+ "console.log(\"ouverte connexion\");"
				+ "});"
				+ "websocket.addEventListener(\"message\", (event) => {\n"
				+ "console.log(event.data);let liste = event.data.split(\",\");"
				+ "let elem = document.getElementById(liste[0]).children[liste[1]];"
				+ "elem.innerHTML=liste[2];"
				+ "console.log(typeof liste[3]);"
				+ "if(liste[3]=='true'){elem.style.backgroundColor = \"red\"} "
				+ "else{elem.style.backgroundColor = \"\"}"
				+ "});"
				+ "</script>"
				+ "</body>\n"
				+ "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
