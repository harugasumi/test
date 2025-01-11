package outflow.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAO implements AutoCloseable{

	//メンバ変数
	private Connection connection = null;
	
	//コンストラクタ
	public DAO() {};

	
	//DB接続部
	public Connection getConnection() throws Exception{
		try {
			if(connection == null || connection.isClosed()) {
				InitialContext initCtx = new InitialContext();//DBリソースを検索
				DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/localDB");//接続したいDBリソースを取得
				connection = ds.getConnection();//メンバ変数でDB接続状態を保持
			}
		}catch(NamingException | SQLException e) {
			//並列で例外を監視（ショートサーキットにしない）
			e.printStackTrace();
			connection = null;
			throw e;
		}
		return connection;
	}
	
	//SQL実行部
	public PreparedStatement getPreparedStatement(String sql) throws Exception {
		return getConnection().prepareStatement(sql);
	}
	
	//コミット
	public void commit() throws SQLException{
		connection.commit();
	}
	
	//ロールバック
	public void rollback() throws SQLException{
		connection.rollback();
	}
	
	//DBクローズ
	@Override
	public void close(){
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection = null;
		}
	}
}
