package outflow.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ID検索しデータを表示画面へフォワードする
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		int id = Integer.parseInt(request.getParameter("id"));
		
		//DTO生成
		OutflowMonitor dto;
		
		//DB処理
		try(OutflowMonitorDAO dao = new OutflowMonitorDAO()){
			dto = dao.detail(id);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
		//DTOをリクエストパラメータへ格納
		request.setAttribute("SelectedId", dto);
		
		request.getRequestDispatcher("/edit_entry.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
