package outflow.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 新規登録・履歴の更新
 * 
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータ受け取り
		int id = Integer.parseInt(request.getParameter("id"));
		String paymentdate = request.getParameter("paymetnDate");
		int categorynum = Integer.parseInt(request.getParameter("categorynum"));
		String note = request.getParameter("note");
		int paid = Integer.parseInt(request.getParameter("paid"));
		
		//DTOへ格納
		OutflowMonitor dto = new OutflowMonitor();
		dto.setId(id);
		dto.setInputPaymentdate(paymentdate);
		dto.setCategorynum(categorynum);
		dto.setNote(note);
		dto.setPaid(paid);
		
		//DB処理
		try(OutflowMonitorDAO dao = new OutflowMonitorDAO()){
			if(id == 0) {
				dao.registerInsert(dto);
			}else {
				dao.registerUpdate(dto);
			}
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
		//ホーム画面へフォワード
		if(id == 0) {
			request.getRequestDispatcher("/entry_home.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/SearchServlet").forward(request, response);
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
