package outflow.web;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OutflowMonitor implements Serializable{
	//フィールド
	private int id;					//番号
	private Date paymentdate;		//購入日
	private int categorynum;		//分類番号
	private String category;		//分類名
	private String note = null;		//品目名
	private int paid;				//金額
	
	private String inputPaymentdate;//入力された購入日
	private String outputPaymentdate;//DBから取り出した購入日
	
	
	
	//アクセッサ
	
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return paymentdate
	 */
	public Date getPaymentdate() {
		return paymentdate;
	}
	/**
	 * @param paymentdate セットする paymentdate
	 */
	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
		//String型にキャストしoutputPaymentdateへ格納
		StringDateFormatter(paymentdate);
	}
	/**
	 * @return category
	 */
	public int getCategorynum() {
		return categorynum;
	}
	/**
	 * @param category セットする category
	 */
	public void setCategorynum(int categorynum) {
		this.categorynum = categorynum;
	}
	/**
	 * @return category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category セットする category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note セットする note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return paid
	 */
	public int getPaid() {
		return paid;
	}
	/**
	 * @param paid セットする paid
	 */
	public void setPaid(int paid) {
		this.paid = paid;
	}
	
	/**
	 * @return inputPaymentdate
	 */
	public String getInputPaymentdate() {
		return inputPaymentdate;
	}
	/**
	 * @param inputPaymentdate セットする inputPaymentdate
	 */
	public void setInputPaymentdate(String inputPaymentdate) {
		this.inputPaymentdate = inputPaymentdate;
		//sql.Date型へキャストしinputPaymentdateに格納
		DateFormatter(inputPaymentdate);
	}
	
	/**
	 * @return outputPaymentdate
	 */
	public String getOutputPaymentdate() {
		return outputPaymentdate;
	}
	/**
	 * @param outputPaymentdate セットする outputPaymentdate
	 */
	public void setOutputPaymentdate(String outputPaymentdate) {
		this.outputPaymentdate = outputPaymentdate;
	}

	
  /*-- デイトフォーマット --*/
	/**
	 * 
	 * String型の購入日をsql.Date型にキャスト
	 * setPaymentdateで格納
	 * 	java.util.Dateはjava.sql.Dateと競合するのでimportしない
	 *  メソット呼び出しはクラス内に限定する
	 * @param stringDate
	 */
	private void DateFormatter(String stringDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = null;
		java.sql.Date sqlDate = null;
		try {
			utilDate = formatter.parse(inputPaymentdate);//util.Date型へ
			sqlDate = new java.sql.Date(utilDate.getTime());//sql.Date型へ
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Data型の購入日に格納
		setPaymentdate(sqlDate);
	}
	
	
	/**
	 * sql.Date型の購入日をString型にキャスト
	 * setOutputPaymentdateで格納
	 * @param sqlDate
	 */

	private void StringDateFormatter(java.sql.Date sqlDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = null;
		
			utilDate = new java.util.Date(sqlDate.getTime());
			String strDate = formatter.format(utilDate);
			
		//String型の購入日に格納
		setOutputPaymentdate(strDate);
		
	}

}
