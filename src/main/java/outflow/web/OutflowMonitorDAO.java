package outflow.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OutflowMonitorDAO extends DAO{

	
  /*-- 全テーブル取得 --*/
	public List<OutflowMonitor> paymentList() throws Exception{
		List<OutflowMonitor> returnList = new ArrayList<OutflowMonitor>();
		
		//SQLクエリ
		String sql ="SELECT id, payment_date, cl.category, note, paid"
				  + " FROM payment_list AS pl LEFT OUTER JOIN category_list AS cl"
				  + " ON pl.categorynum = cl.categorynum"
				  + " ORDER BY payment_date";
		
		
		//SQLクエリの発行・結果の取得
		PreparedStatement ps = getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		//結果をDTOインスタンスに格納
		while(rs.next()) {
			OutflowMonitor dto = new OutflowMonitor();
			dto.setId(rs.getInt("id"));
			dto.setPaymentdate(rs.getDate("payment_date"));
			dto.setCategory(rs.getString("category"));
			dto.setNote(rs.getString("note"));
			dto.setPaid(rs.getInt("paid"));
			returnList.add(dto);
		}
		return returnList;
	}
	
  /*-- ID別レコード取得 --*/
	public OutflowMonitor detail(int id) throws Exception{
		//SQL文
		String sql = "SELECT id, payment_date, categorynum, note, paid"
					+" FROM payment_list WHERE id = ?";
		
		//SQLクエリ発行・結果の取得
		PreparedStatement ps = getPreparedStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		//結果を格納
		OutflowMonitor dto = new OutflowMonitor();
		if(rs.next()) {
		dto.setId(rs.getInt("id"));
		dto.setPaymentdate(rs.getDate("payment_date"));
		dto.setCategorynum(rs.getInt("categorynum"));
		dto.setNote(rs.getString("note"));
		dto.setPaid(rs.getInt("paid"));
		}
		return dto;
	}
	
  /*-- 分類別レコード取得 --*/
	public List<OutflowMonitor> categoryList(int categorynum) throws Exception {
		List<OutflowMonitor> returnList = new ArrayList<>();
		
		//SQL文
		String sql = "SELECT id, payment_date, cl.category, note, paid"
					+" FROM payment_list AS pl LEFT OUTER JOIN category_list AS cl"
					+" ON pl.categorynum = cl.categorynum WHERE pl.categorynum = ?";
		
		//SQLクエリ発行・結果の取得
		PreparedStatement ps = getPreparedStatement(sql);
		ps.setInt(1, categorynum);
		ResultSet rs = ps.executeQuery();
		
		//結果の格納
		while(rs.next()){
			OutflowMonitor dto = new OutflowMonitor();
			dto.setId(rs.getInt("id"));
			dto.setPaymentdate(rs.getDate("payment_date"));
			dto.setCategory(rs.getString("category"));
			dto.setNote(rs.getString("note"));
			dto.setPaid(rs.getInt("paid"));
			returnList.add(dto);
		}
		return returnList;
	}
	
  /*-- 要素別レコード取得 --*/
//要素：購入日（限定・範囲指定）、分類番号（限定・複数）、金額（降順・昇順・以上・以下）
//各要素別のリストを日付順で表示。日付別の場合は分類番号順に表示。
//	public void filtereList() {}
	
  /*-- 新規登録 --*/
	public int registerInsert(OutflowMonitor dto) throws Exception{
		//SQL文
		String sql = "INSERT INTO payment_list (payment_date, categorynum, note, paid)"
					+" VALUES (?, ?, ?, ?)";
		int result = 0;
		
		//SQLクエリ発行・結果の受取
		try {
			PreparedStatement ps = getPreparedStatement(sql);
			ps.setDate(1, dto.getPaymentdate());
			ps.setInt(2, dto.getCategorynum());
			ps.setString(3, dto.getNote());
			ps.setInt(4, dto.getPaid());
			result = ps.executeUpdate();//登録レコード数
		
			//コミット
			commit();
		}catch(Exception e) {
			//ロールバック
			rollback();
			throw e;
		}
		return result;
	}
	
  /*-- 履歴の修正 --*/
	public int registerUpdate(OutflowMonitor dto) throws Exception{
		//SQL文
		String sql = "UPDATE payment_list SET payment_date = ?, categorynum = ?,"
					+" note = ?, paid = ? WHERE id = ?";
		int result = 0;
		
		//SQLクエリ発行・結果の受取
		try {
			PreparedStatement ps = getPreparedStatement(sql);
			ps.setDate(1, dto.getPaymentdate());
			ps.setInt(2, dto.getCategorynum());
			ps.setString(3, dto.getNote());
			ps.setInt(4, dto.getPaid());
			ps.setInt(5, dto.getId());
			result = ps.executeUpdate();//修正レコード数
		
			//コミット
			commit();
		}catch(Exception e) {
			//ロールバック
			rollback();
			throw e;
		}
		return result;
	}
	
  /*-- 1件削除 --*/
	public int delete(int id)throws Exception{
		//SQL文
		String sql = "DELETE FROM payment_list WHERE id = ?";
		
		int result = 0;
		
		//SQLクエリ発行・結果の受け取り
		try {
			PreparedStatement ps = getPreparedStatement(sql);
			ps.setInt(1, id);
			result = ps.executeUpdate();//削除レコード数
			//コミット
			commit();
		}catch(Exception e) {
			//ロールバック
			rollback();
			throw e;
		}
		return result;
	}

  /*-- 全件削除 --*/
  /*-- 分類別集計 --*/
}
