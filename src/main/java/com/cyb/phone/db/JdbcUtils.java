package com.cyb.phone.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import com.cyb.bean.User;
public class JdbcUtils {
	public Connection conn;

	public JdbcUtils(Connection conn) {
		this.conn = conn;
	}

	public QueryRunner createQuery() {
		return new QueryRunner();
	}

	/**
	 * look for one record only;
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Map<String, Object> queryForOneMap(String sql) {
		Map<String, Object> data = null;
		try {
			data = createQuery().query(conn, sql, null, new MapHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
    /**
     * ��ѯlist �ƶ�bean����
     * @param classType
     * @param sql
     * @return
     */
	public <T> List<T> queryForList(Class<T> classType, String sql) {
		List<T> data = null;
		try {
			data = (List<T>) createQuery().query(conn, sql,
					new BeanListHandler<T>(classType));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	public void insert(String sql){
		try {
			createQuery().insert(conn, sql, new MapHandler());
			System.out.println("�������ݳɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(String sql){
		try {
			createQuery().update(conn,sql);
			System.out.println("�������ݳɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(String sql,Object[] params){
		try {
			createQuery().update(conn,sql,params);
			System.out.println("�������ݳɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(String sql){
		try {
			createQuery().update(conn, sql, new MapHandler());
			System.out.println("�������ݳɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void execute(String sql){
		try {
			createQuery().update(conn,sql);
			System.out.println("�������ݳɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String sql = "SELECT * FROM TEST ";
		JdbcUtils dbUtils = new JdbcUtils(DBEntity.getConnection());
		String sqlMax  = "select max(id)+1 as idx from test";
		Map data = dbUtils.queryForOneMap(sqlMax);
		dbUtils.queryForList(User.class, sql);
		String insert = "INSERT INTO TEST VALUES("+data.get("IDX")+", 'chenyb');";
		dbUtils.insert(insert);
		String update = "UPDATE TEST SET NAME='sdfsfd' WHERE ID=1;";
		dbUtils.update(update);
		String update_params = "UPDATE TEST SET NAME='Hi12' WHERE ID=?;";
		dbUtils.update(update_params,new Object[]{'3'});
		dbUtils.execute(update);
		String delete = "delete from test  WHERE ID=1;";
		dbUtils.execute(delete);
	}
}
