package db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBAction {
	
	private static DBAction instance = new DBAction();
	private static Connection conn;
	private DataSource ds;
	private Context ctx;
	
	private DBAction() {
		try {
			InitialContext initctx = new InitialContext();
			ctx = (Context) initctx.lookup("java:/comp/env");
			ds = (DataSource) ctx.lookup("jdbc/oracle");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static DBAction getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
