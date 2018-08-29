package com.jd.maintain.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jd.common.MD5Encrypt;
import com.jd.maintain.dao.UserInfoDao;
import com.jd.maintain.dto.UserInfoDto;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/maintain/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(userName + "\t" + password);
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfoDto userInfoDto = userInfoDao.queryByUserName(userName);
		if(userInfoDto != null) {
			if (MD5Encrypt.validatePassword(password, userInfoDto.getPassword())) {
				RequestDispatcher rd = request.getRequestDispatcher("../loginSuccess.jsp");
				rd.forward(request, response);
			} else {
				System.out.println("√‹¬Î¥ÌŒÛ");
				response.sendRedirect("../login.html?flag=loginError");
			}
		} else {
			System.out.println("’À∫≈¥ÌŒÛ");
			response.sendRedirect("../login.html?flag=loginError");
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
