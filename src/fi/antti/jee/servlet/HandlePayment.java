package fi.antti.jee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.antti.jee.dao.Dao;

/**
 * Servlet implementation class HandlePayment
 */
@WebServlet("/HandlePayment")
public class HandlePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandlePayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub'
		System.out.println("HandlePayment.doPost()");
				
		String sum = request.getParameter("sum");
		String room = request.getParameter("room");
		String lasku_id = request.getParameter("lasku_id");
		PrintWriter wout = response.getWriter();
		Dao db = new Dao();
		
		try {
			
			db.updateLasku(Integer.parseInt(lasku_id), Double.parseDouble(sum), Integer.parseInt(room));
			
		} catch (Exception e) {
			wout.print("Virhe " + e );
		}
		
		//System.out.println("Payment: " + sum + "\nRoom: " + room + "\nLasku Id:" + lasku_id);
		String jsp = "/laskut.jsp";

		RequestDispatcher dp = getServletContext().getRequestDispatcher(jsp);
		dp.forward(request, response);
		
		
		
	}

}
