package com.Highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class FetchServlet
 */
@WebServlet("/FetchData")
public class FetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		int rowGrabber = 10; // PAGINATION
		
		try {
			String pageInURL = request.getParameter("page");
			int page = Integer.parseInt(pageInURL) * rowGrabber; 
			Connection con = DBConnection.createConnect();
			Statement st = con.createStatement();
			//////Query to fetch multiple columns from multiple tables
			String query = "SELECT * FROM winter_internship wi JOIN business bs ON wi.business_code = bs.business_code JOIN customer ct ON wi.cust_number = ct.cust_number ORDER BY sl_no DESC LIMIT "+((page-1)*10+1)+","+10;
			ResultSet rs = st.executeQuery(query);
			
			ArrayList<POJO> data = new ArrayList<>();
			while(rs.next())
			{
				POJO inv = new POJO();
				inv.setSl_no(rs.getInt("sl_no"));
				inv.setBusiness_code(rs.getString("business_code"));
				inv.setBusiness_name(rs.getString("business_name"));
				inv.setCust_number(rs.getInt("cust_number"));
				inv.setName_customer(rs.getString("name_customer"));
				inv.setClear_date(rs.getString("clear_date"));
				inv.setBusiness_year(rs.getString("business_year"));
				inv.setDoc_id(rs.getString("doc_id"));
				inv.setPosting_date(rs.getString("posting_date"));
				inv.setDocument_create_date(rs.getString("document_create_date"));
				inv.setDocument_create_date1(rs.getString("document_create_date1"));
				inv.setDue_in_date(rs.getString("due_in_date"));
				inv.setInvoice_currency(rs.getString("invoice_currency"));
				inv.setDocument_type(rs.getString("documnet_type"));
				inv.setPosting_id(rs.getInt("posting_id"));
				inv.setArea_business(rs.getString("area_business"));
				inv.setTotal_open_amount(rs.getDouble("total_open_amount"));
				inv.setBaseline_create_date(rs.getString("baseline_create_date"));
				inv.setCust_payment_terms(rs.getString("cust_payment_terms"));
				inv.setInvoice_id(rs.getFloat("invoice_id"));
				inv.setIsOpen(rs.getInt("isOpen"));
				data.add(inv);
			}
			Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices  = gson.toJson(data);
			response.setContentType("application/json");
			try {
				response.getWriter().write(invoices);//getWriter() returns a PrintWriter object that can send character text to the client. 
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			rs.close();
			st.close();
			con.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
