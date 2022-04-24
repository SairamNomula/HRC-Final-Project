package com.Highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddInvoice
 */
@WebServlet("/AddInvoice")
public class AddInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String businessCode =  request.getParameter("business_code");
			String customerNumber = request.getParameter("cust_number");
			String clearDate = request.getParameter("clear_date");
			String businessYear = request.getParameter("business_year");
			String documentId = request.getParameter("doc_id");
			String postingDate = request.getParameter("posting_date");
			String documentCreateDate =  request.getParameter("document_create_date");
			String dueDate = request.getParameter("due_in_date");
			String invoiceCurrency = request.getParameter("invoice_currency");
			String documentType = request.getParameter("document_type");
			String postingId = request.getParameter("posting_id");
			String totalOpenAmount = request.getParameter("total_open_amount");
			String baselineCreateDate = request.getParameter("baseline_create_date");
			String custPaymentTerms = request.getParameter("cust_payment_terms");
			String invoiceId = request.getParameter("invoice_id");
			
			Connection con = DBConnection.createConnect();
			String query = "INSERT INTO winter_internship(business_code,cust_number,clear_date,business_year,doc_id,posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, businessCode);
			st.setString(2, customerNumber);
			st.setString(3, clearDate);
			st.setString(4, businessYear);
			st.setString(5,  documentId);
			st.setString(6, postingDate);
			st.setString(7, documentCreateDate);
			st.setString(8, dueDate);
			st.setString(9, invoiceCurrency);
			st.setString(10, documentType);
			st.setString(11, postingId);
			st.setString(12, totalOpenAmount);
			st.setString(13, baselineCreateDate);
			st.setString(14, custPaymentTerms);
			st.setString(15, invoiceId);
			

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

