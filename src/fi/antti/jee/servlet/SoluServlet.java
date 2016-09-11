package fi.antti.jee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.antti.jee.bean.Lasku;
import fi.antti.jee.dao.Dao;

/**
 * Servlet implementation class SoluServlet
 */
@WebServlet("/SoluServlet")
public class SoluServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SoluServlet() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("SoluServlet.enclosing_method()");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SoluServlet.doGet()");
		PrintWriter wout = response.getWriter();

		Dao db = new Dao();
		ArrayList<Lasku> laskut = new ArrayList<Lasku>();

		try {

			laskut = db.haeKaikkiTiedot();
			
			// Jos resultset = tyhj‰, tehd‰‰n dummy olio joka n‰ytet‰‰n sivulla
			if (laskut.size() == 0) {
				Lasku lasku = new Lasku();
				lasku.setTapahtuma("Tietokannassa ei laskuja");
				lasku.setHuone_1_maksettu(true);
				lasku.setHuone_2_maksettu(true);
				lasku.setHuone_3_maksettu(true);
				lasku.setHuone_4_maksettu(true);
				laskut.add(lasku);
			}

			request.setAttribute("lasku_data", laskut);
			String jsp = "/laskut.jsp";

			RequestDispatcher dp = getServletContext()
					.getRequestDispatcher(jsp);
			dp.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			wout.print("Virhe " + e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SoluServlet.doPost()");
	}

}
