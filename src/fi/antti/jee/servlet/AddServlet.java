package fi.antti.jee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.antti.jee.bean.Lasku;
import fi.antti.jee.dao.Dao;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("AddServlet.AddServlet()");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AddServlet.doGet()");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AddServlet.doPost()");
		
		Dao db = new Dao();
		Lasku lasku = new Lasku();
		
		
		String tapahtuma = request.getParameter("tapahtuma");
		String summa = request.getParameter("summa");
		System.out.println(tapahtuma + " " + summa);
		PrintWriter wout = response.getWriter();
		
		lasku.setTapahtuma(tapahtuma);
		
		double temp, perHuone, room_Numbers = 4;
		
		temp = Double.parseDouble(summa);
		
		perHuone = temp / room_Numbers;
		
		lasku.setHuone_1_velka(perHuone);
		lasku.setHuone_2_velka(perHuone);
		lasku.setHuone_3_velka(perHuone);
		lasku.setHuone_4_velka(perHuone);
		lasku.setTotal(temp);
		lasku.setVelkaa_jaljella(temp);
		
		try {
			
			if (tapahtuma.length() > 1 && temp > 0){
				
				db.lisaaLasku(lasku);
			}
			
		} catch (Exception e) {
			wout.print("Virhe " + e );
		}
		

		String jsp = "/laskut.jsp";
		RequestDispatcher dp = getServletContext().getRequestDispatcher(jsp);
		dp.forward(request, response);

	}

}
