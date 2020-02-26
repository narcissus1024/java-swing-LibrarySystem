package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Admin;
import model.AdminCode;
import model.Register;
import model.User;
import utils.JDBCUtil;

public class RegistDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
//	public RegistDao() {
//		try {
//			conn = JDBCUtil.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * - 检查密钥是否存在
	 * @param adminCode
	 * @return
	 */
	public AdminCode checkAdminCode(String adminCode) {
		AdminCode adminCodeMold = new AdminCode();
		try {
			String sql = "select * from admin_code where code = ?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminCode);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				adminCodeMold.setId(rs.getInt("id"));
				adminCodeMold.setCode(rs.getString("code"));
				adminCodeMold.setCount(rs.getInt("count"));
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return adminCodeMold;
	}

	/**
	 * - 修改管理员密钥使用次数
	 * @param adminCodeMold
	 */
	public void updateAdminCode(AdminCode adminCodeMold) {
		try {
			String sql = "update admin_code set count = ? where code = ?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adminCodeMold.getCount()-1);
			ps.setString(2, adminCodeMold.getCode());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release( ps, conn);
		}
	}

	/**
	 *  - 普通用户注册
	 * @param register
	 */
	public String userRegist(Register register) {
		String msg = "";
		try {
			String sql = "insert into t_user (username,password,name) values (?,?,?)";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, register.getCode());
			ps.setString(2, register.getPassword());
			ps.setString(3, register.getName());
			
			int flag = ps.executeUpdate();
			if (flag == 1) {
				msg = "注册成功！";
			}else {
				msg = "注册失败！";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(ps, conn);
		}
		return msg;
	}

	/**
	 * - 管理员注册
	 * @param register
	 * @return
	 */
	public String adminRegist(Register register) {
		String msg = "";
		try {
			String sql = "insert into t_admin (username,password,name) values (?,?,?)";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, register.getCode());
			ps.setString(2, register.getPassword());
			ps.setString(3, register.getName());
			
			int flag = ps.executeUpdate();
			if (flag == 1) {
				msg = "注册成功！";
			}else {
				msg = "注册失败！";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(ps, conn);
		}
		return msg;
	}

	/**
	 * 	通过账号查找普通用户
	 */
	public User findUserByCode(String code) {
		User user = null;
		try {
			String sql = "select * from t_user where username = ?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String name = rs.getString("name");
				user = new User(uid, username, password, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return user;
	}

	/**
	 * 	通过账号查找管理员
	 * @param code
	 * @return
	 */
	public Admin findAdminByCode(String code) {
		Admin admin = null;
		try {
			String sql = "select * from t_admin where username = ?";
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Integer aid = rs.getInt("aid");
				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				admin = new Admin(aid, username, password, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return admin;
	}

}
