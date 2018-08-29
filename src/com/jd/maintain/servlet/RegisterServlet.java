package com.jd.maintain.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jd.common.MD5Encrypt;
import com.jd.maintain.dao.UserInfoDao;
import com.jd.maintain.dto.UserInfoDto;

//import com.jd.maintain.dao.UserInfoDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/maintain/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		//用户输入的验证码
		String validateCode = request.getParameter("validateCode");
		//从Session中获取验证码
		HttpSession session = request.getSession();
		String validateCodeInsession = (String)session.getAttribute("validateCodeInSession");
		if(validateCode.equalsIgnoreCase(validateCodeInsession)) {
			UserInfoDao userInfoDao = new UserInfoDao();
			RequestDispatcher rd = null;
			if (userInfoDao.queryByUserName(userName) != null) {
				System.out.println("用户已存在");
				rd = request.getRequestDispatcher("../alreadyRegister.jsp");
			} else {
				UserInfoDto userInfoDto = new UserInfoDto();
				userInfoDto.setUserName(userName);
				userInfoDto.setPassword(MD5Encrypt.encryptByMD5(password));
				userInfoDto.setPhone(phone);
				userInfoDao.saveUserInfo(userInfoDto);
				System.out.println("用户信息成功保存到数据库");
				rd = request.getRequestDispatcher("../registerSuccess.jsp");
			}
			rd.forward(request, response);
		} else {
			response.sendRedirect("../validateCodeError.html");
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